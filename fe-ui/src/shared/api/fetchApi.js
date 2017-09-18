import 'whatwg-fetch';
import { stringify } from 'query-string';
import _ from 'lodash';
import userStorage from '../storage/user';

const SERVER_URL = _.get(process.env.config, 'server.url');

// handle empty response body. eg: 204 status
// ref: https://github.com/github/fetch/issues/268#issuecomment-176544728
const parseJSON = response => response.text()
  .then(text => (text ? JSON.parse(text) : undefined));


async function fetchApi(serviceUrl, config = {}) {
  const { ignoreDomain, query, ...others } = config;

  const token = userStorage.getToken();

  const headers = new Headers({
    Accept: 'application/json',
    'Content-Type': 'application/json',
    ...(token ? { 'Auth-Token': token } : {}),
  });

  const finalConfig = {
    headers,
    ...others,
  };

  // TODO: ignore DOMAIN when the url string contains protocol like 'http://'
  const baseUrl = ignoreDomain ? serviceUrl : `${SERVER_URL}${serviceUrl}`;
  const queryPrefix = baseUrl.includes('?') ? '&' : '?';

  const response = await fetch(`${baseUrl}${queryPrefix}${stringify(query)}`, finalConfig);
  const responseBody = await parseJSON(response);

  if (response.status >= 200 && response.status < 300) {
    return responseBody;
  }
  const error = new Error(response.statusText);
  error.response = response;
  error.responseBody = responseBody;
  throw error;
}

export default fetchApi;

