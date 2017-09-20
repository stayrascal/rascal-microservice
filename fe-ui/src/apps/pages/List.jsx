import React, { Component } from 'react';
import { flowRight } from 'lodash';
import { parse } from 'query-string';
import { withRedux } from '../../shared/redux';
import { layoutWrapper } from '../../shared/dashboard';
import ListTable from '../components/ListTable';
import SearchBar from '../components/SearchBar';
import listApi from '../../shared/api/list';
import makePath from '../../shared/utils/makePath';
import history from '../../history';


const DEFAULT_CONFIG = {
  mapLocationToRequest(location) {
    const { pageIndex = 1, ...searchParams } = parse(location.search);

    return {
      pageIndex,
      ...searchParams,
    };
  },
  mapLocationToSearch(location) {
    const { pageIndex, ...searchParams } = parse(location.search);

    return searchParams;
  },
};

const connectListPage = config => (Page) => {
  const {
    getListData,
    mapLocationToRequest,
    mapLocationToSearch,
  } = {
    ...DEFAULT_CONFIG,
    ...config,
  };
  class ListPage extends Component {
    constructor() {
      super();

      this.state = {
        list: {
          totalCount: 0,
          pageIndex: 1,
          pageSize: 10,
          items: [],
        },
      };
    }

    componentDidMount() {
      this.getList(this.props.location);
    }

    componentWillReceiveProps(nextProps) {
      this.getList(nextProps.location);
    }

    getList(location) {
      const finalRequest = mapLocationToRequest(location);

      getListData(finalRequest).then(list => this.setState({ list }));
    }

    changeUrlQuery(nextQuery, isOverride) {
      const { location: { pathname, search } } = this.props;
      const currentQuery = parse(search);
      const nextPath = makePath(
        pathname,
        undefined,
        isOverride ? nextQuery : { ...currentQuery, ...nextQuery },
      );
      history.push(nextPath);
    }

    handleSelectPage = (nextPageIndex) => {
      this.changeUrlQuery({
        pageIndex: nextPageIndex,
      });
    }
    handleSearch = (search) => {
      this.changeUrlQuery({
        ...search,
        pageIndex: 1,
      }, true);
    }

    render() {
      const { list } = this.state;
      const searchParams = mapLocationToSearch(this.props.location);
      return (
        <Page
          list={list}
          searchParams={searchParams}
          searchListPage={this.handleSearch}
          selectPage={this.handleSelectPage}
        />
      );
    }
  }
  return ListPage;
};

const columns = [
  {
    key: 'name',
  },
  {
    key: 'role',
  },
  {
    key: 'address',
  },
];

const List = (props) => {
  const { list, searchParams, searchListPage, selectPage } = props;
  return (
    <div className="content">
      <SearchBar searchParams={searchParams} onSearch={searchListPage}/>
      <ListTable
        columns={columns}
        list={list}
        onSelectPage={selectPage}
      />
    </div>
  );
};

const decorator = flowRight([
  withRedux(),
  layoutWrapper,
  connectListPage({
    getListData: listApi.getList,
    mapLocationToRequest(location) {
      const defaultRequest = DEFAULT_CONFIG.mapLocationToRequest(location);

      return {
        role: 'ADMIN',
        ...defaultRequest,
      };
    },
    mapLocationToSearch(location) {
      const defaultSearch = DEFAULT_CONFIG.mapLocationToSearch(location);

      return {
        role: 'ADMIN',
        ...defaultSearch,
      };
    },
  }),
  // connect(state => state),
]);

export default decorator(List);
