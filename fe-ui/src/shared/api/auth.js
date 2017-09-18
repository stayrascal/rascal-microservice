import fetchApi from './fetchApi';

export default {
  signIn(user) {
    return fetchApi('/sign-in', {
      method: 'POST',
      body: JSON.stringify(user),
    });
  },
};

