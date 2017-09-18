import { createReducer } from 'redux-action-tools';
import actionTypes from './constants/actionTypes';

const { SIGN_IN, SIGN_OUT, ENSURE_USER } = actionTypes;
const setUser = (state, action) => action.payload || state;
const cleanUser = () => null;
const initUser = null;

const reducer = createReducer()
  .when(SIGN_IN)
  .done(setUser)
  .failed(cleanUser)
  .when(ENSURE_USER, setUser)
  .when(SIGN_OUT)
  .done(cleanUser)
  .build(initUser);

export default reducer;
