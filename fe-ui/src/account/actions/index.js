import { createAction, createAsyncAction } from 'redux-action-tools';
import { SubmissionError } from 'redux-form';

import auth from '../../shared/api/auth';
import userStorage from '../../shared/storage/user';
import { push } from '../../shared/router';
import actionTypes from '../constants/actionTypes';

const signInAction = createAsyncAction(
  actionTypes.SIGN_IN,
  (signInUser, dispatch) => auth.signIn(signInUser)
    .then(user => userStorage.setUser(user))
    .then(async (data) => {
      dispatch(push('/hello'));
      return data;
    }, e => Promise.reject(new SubmissionError({ _error: e.message })))
);

const ensureUser = createAction(actionTypes.ENSURE_USER);

const signOutAction = createAsyncAction(
  actionTypes.SIGN_OUT,
  (payload, dispatch) => userStorage.removeUser()
    .then(() => dispatch(push('/sign-in'))),
);

export default {
  signInAction,
  ensureUser,
  signOutAction
};
