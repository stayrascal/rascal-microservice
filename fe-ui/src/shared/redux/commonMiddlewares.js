import { routerMiddleware } from 'react-router-redux';
import { createLogger } from 'redux-logger';
import thunk from 'redux-thunk';
import { errorMiddleWare, loadingMiddleWare } from '../loadingAndError';

import history from '../../history';

const middlewares = [
  loadingMiddleWare,
  errorMiddleWare,
  routerMiddleware(history),
  thunk,
  createLogger()
];

export default middlewares;