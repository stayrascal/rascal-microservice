import _ from 'lodash';
import { getActionTypes } from '../utils/actionUtils';
import { ASYNC_PHASES } from 'redux-action-tools';

const actionTypes = getActionTypes({
  ASYNC_STARTED: null,
  ASYNC_ENDED: null
});

export { actionTypes as loadingActionTypes };

export const wrapAsyncWithLoading = dispatch => asyncFn => function (...args) {
  dispatch({
    type: actionTypes.ASYNC_STARTED,
    payload: {
      source: 'ASYNC_FN',
      data: args
    }
  });
  return asyncFn.apply(this, args).then((data) => {
    dispatch({
      type: actionTypes.ASYNC_ENDED,
      payload: {
        source: 'ASYNC_FN',
        data
      }
    });
    return data;
  }, (e) => {
    dispatch({
      type: actionTypes.ASYNC_ENDED,
      payload: {
        source: 'ASYNC_FN',
        error: e
      }
    });
    return Promise.reject(e);
  });
};

export function loadingMiddleWare({ dispatch }) {
  return next => (action) => {
    const asyncStep = _.get(action, 'meta.asyncPhase');
    const omitLoading = _.get(action, 'meta.omitLoading');

    if (asyncStep && !omitLoading) {
      dispatch({
        type: asyncStep === ASYNC_PHASES.START
          ? actionTypes.ASYNC_STARTED
          : actionTypes.ASYNC_ENDED,
        payload: {
          source: 'ACTION',
          action
        },
      });
    }

    return next(action);
  }
}

export function loadingReducer(state = false, action) {
  switch (action.type) {
    case actionTypes.ASYNC_STARTED:
      return true;
    case actionTypes.ASYNC_ENDED:
      return false;
    default:
      return state;
  }
}