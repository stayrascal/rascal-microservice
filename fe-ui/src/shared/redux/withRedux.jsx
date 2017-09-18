import React, { Component } from 'react';
import { Provider } from 'react-redux';
import { applyMiddleware, combineReducers, createStore } from 'redux';

import commonMiddlewares from './commonMiddlewares';
import commonReducers from './commonReducers';
import commonInitPage from './commonInitPage';

const defaultConfig = {
  pageReducers: {},
  reducers: commonReducers,
  middlewares: commonMiddlewares,
  initPage: commonInitPage
};

const withRedux = config => (Comp) => {
  const finalConfig = {
    ...defaultConfig,
    ...config,
  };

  const { middlewares, reducers, pageReducers, initPage } = finalConfig;

  return class WithRedux extends Component {
    constructor(props) {
      super(props);

      const reducerFn = combineReducers({
        ...pageReducers,
        ...reducers
      });

      this.store = applyMiddleware(...middlewares)(createStore)(reducerFn);

      initPage && initPage(this.store, props);
    }

    render() {
      return (
        <Provider store={this.store}>
          <Comp {...this.props} />
        </Provider>
      );
    }
  };
};

export default withRedux;