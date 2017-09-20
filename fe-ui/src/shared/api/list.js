import fetchApi from './fetchApi';

export default {
  getList(query) {
    return fetchApi('/list', {
      method: 'GET',
      query,
    });
  },
};

