import _ from 'lodash';
import { ASYNC_PHASES } from 'redux-action-tools';

import { routerActions } from '../router';
import userStorage from '../storage/user';

export const getErrorMessage = error => (
  error.responseBody
    ? error.responseBody.message
    : (error.message || 'Unkown error')
);

export const commonErrorHandler = function (dispatch, error) {
  if (error.response && error.response.status === 401) {
    userStorage.removeUser()
      .then(() => dispatch(routerActions.replace({
        path: '/sign-in',
        query: { reason: '401' },
      })));
  } else {
    dispatch(window.alert(getErrorMessage(error)));
  }
};

export function errorMiddleWare({ dispatch }) {
  return next => (action) => {
    const asyncStep = _.get(action, 'meta.asyncPhase');
    const omitError = _.get(action, 'meta.omitError');

    if (asyncStep === ASYNC_PHASES.FAILED && !omitError) {
      const { payload: error } = action;

      commonErrorHandler(dispatch, error);
    }

    return next(action);
  };
}