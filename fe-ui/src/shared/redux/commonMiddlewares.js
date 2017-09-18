import { routerMiddleware } from 'react-router-redux';
import createLogger from 'redux-logger';
import thunk from 'redux-thunk';
import { errorMiddleware, loadingMiddleware } from '../loadingAndError';

import history from '../../history';

const middlewares = [
  loadingMiddleware,
  errorMiddleware,
  routerMiddleware(history),
  thunk,
  createLogger()
];

export default middlewares;