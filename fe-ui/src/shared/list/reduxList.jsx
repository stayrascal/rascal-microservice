import React, { Component, PropTypes } from 'react';
import _ from 'lodash';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import { parse } from 'query-string';
import { createAction, createAsyncAction, createReducer } from 'redux-action-tools';
import { propTypes as routerProps, push } from '../../shared/router';
import { getPages } from '../utils/pagination';


const DEFAULT_LIST_DATA = {
  pageIndex: 0,
  pageSize: 10,
  totalCount: 0,
  items: [],
  loading: false,
};

const propTypes = {
  listData: PropTypes.shape({
    pageIndex: PropTypes.number,
    pageSize: PropTypes.number,
    totalCount: PropTypes.number,
    items: PropTypes.array,
    loading: PropTypes.bool,
  }),
  searchList: PropTypes.func.isRequired,
  goToPage: PropTypes.func.isRequired,
  sortParams: PropTypes.shape({
    sortBy: PropTypes.string,
    sortType: PropTypes.string,
  }),
};

const listReducer = createReducer()
  .when('INIT_LIST', (state, { payload: { name, initData } }) => {
    /* eslint-disable no-param-reassign */
    state[name] = initData; // return the original reference to avoid unnecessary UI update;
    return state;
  })
  .when('LOAD_LIST', (state, { meta: { list } }) => ({
    [list]: {
      ...state[list],
      loading: true,
    },
  }))
  .done((state, { payload, meta: { list } }) => ({
    [list]: {
      ...state[list],
      ...payload,
      pageItems: getPages(payload),
      loading: false,
    },
  }))
  .failed((state, { meta: { list } }) => ({
    [list]: {
      loading: false,
    },
  }))
  .build({});

function transformListQuery(query) {
  return _.chain(query)
    .mapKeys((v, k) => _.snakeCase(k))
    .mapValues((v, k) => (k === 'page_index' ? v - 1 : v))
    .value();
}

const DEFAULT_CONFIG = {
  name: _.uniq(),
  autoLoad: true,
  mapPropsToRequest(props) {
    const DEFAULT_REQUEST = {
      pageIndex: 1,
      pageSize: 10,
    };
    const queryObject = parse(props.location.search);
    return transformListQuery({
      ...DEFAULT_REQUEST,
      ...queryObject,
    });
  },
  mapPropsToSearch(props) {
    return parse(props.location.search);
  },
  mapSearchToQuery(search) {
    const emptyValues = ['', undefined];
    return _.omitBy(search, v => emptyValues.indexOf(v) !== -1);
  },
  dataLoader() {
    return Promise.resolve(DEFAULT_LIST_DATA);
  },
};

function connectListView(config, ListComponent) {
  const finalConfig = {
    ...DEFAULT_CONFIG,
    ...config,
  };

  const actions = {
    initList: createAction('INIT_LIST'),
    loadList: createAsyncAction('LOAD_LIST', finalConfig.dataLoader, (payload, defaultMeta) => ({
      list: finalConfig.name,
      ...defaultMeta,
    })),
    loadListQuiet: createAsyncAction('LOAD_LIST', finalConfig.dataLoader, (payload, defaultMeta) => ({
      list: finalConfig.name,
      omitLoading: true,
      ...defaultMeta,
    })),
  };

  class ListView extends Component {
    static propTypes = {
      dispatch: PropTypes.func.isRequired,
      location: routerProps.location.isRequired,
      /* eslint-disable react/forbid-prop-types */
      listView: PropTypes.object.isRequired,
    };

    constructor(props) {
      super(props);
      const { dispatch } = this.props;
      dispatch(actions.initList({
        name: finalConfig.name,
        initData: DEFAULT_LIST_DATA,
      }));

      this.search = this.search.bind(this);
      this.goToPage = this.goToPage.bind(this);
      this.sort = this.sort.bind(this);
      this.actionMethods = finalConfig.actions
        ? bindActionCreators(finalConfig.actions, dispatch)
        : undefined;
    }

    componentWillMount() {
      finalConfig.autoLoad && this.load(this.props);
    }

    componentWillReceiveProps(nextProps) {
      const shouldReload = this.props.location.search !== nextProps.location.search;
      shouldReload && finalConfig.autoLoad && this.load(nextProps);
    }

    load = (props = this.props, quiet) => {
      const { dispatch } = props;
      const request = finalConfig.mapPropsToRequest(props);
      const loadAction = quiet ? actions.loadListQuiet : actions.loadList;
      return dispatch(loadAction(request));
    }

    goToPage(pageIndex) {
      const { dispatch, location: { pathname: path, search } } = this.props;
      const targetQuery = {
        ...parse(search),
        pageIndex,
      };
      dispatch(push({
        path,
        query: targetQuery,
      }));
    }

    search(params) {
      const { dispatch, location: { pathname: path, search } } = this.props;
      const targetQuery = {
        ...finalConfig.mapSearchToQuery(params, parse(search)),
        pageIndex: 1,
      };
      dispatch(push({
        path,
        query: targetQuery,
      }));
    }

    sort(sortData) {
      const { dispatch, location: { pathname: path, search } } = this.props;
      const targetQuery = {
        ...parse(search),
        ...sortData,
        pageIndex: 1,
      };
      dispatch(push({
        path,
        query: targetQuery,
      }));
    }

    render() {
      const { listView } = this.props;
      const listData = listView[finalConfig.name] || DEFAULT_LIST_DATA;
      const searchParams = finalConfig.mapPropsToSearch(this.props);
      const sortParams = _.pick(searchParams, ['sortBy', 'sortType']);
      return (
        <ListComponent
          listData={listData}
          loadList={this.load}
          searchParams={searchParams}
          searchList={this.search}
          sortParams={sortParams}
          sortList={this.sort}
          goToPage={this.goToPage}
          {...this.actionMethods}
          {...this.props}
        />
      );
    }
  }

  return connect(state => state)(ListView);
}

const curriedConnectListView = _.curry(connectListView);

export { connectListView, curriedConnectListView, DEFAULT_CONFIG, listReducer, propTypes };
