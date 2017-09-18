import _, { curry } from 'lodash';
import { connectListView, DEFAULT_LIST_CONFIG as defaultListConfig, listReducer, propTypes } from 'redux-listview';

const curriedConnectListView = curry(connectListView);

function transformListQuery(query) {
  return _.chain(query)
    .mapKeys((v, k) => _.snakeCase(k))
    .mapValues((v, k) => (k === 'page_index' ? v - 1 : v))
    .value();
}

const DEFAULT_CONFIG = {
  ...defaultListConfig,
  mapLocationToRequest: (location, props) => transformListQuery(defaultListConfig.mapLocationToRequest(location, props))
};

export { curriedConnectListView, DEFAULT_CONFIG, listReducer, propTypes };