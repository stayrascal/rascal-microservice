import { PropTypes } from 'react';
import toRegex from 'path-to-regexp';

const propTypes = {
  location: PropTypes.shape({
    pathname: PropTypes.string,
    search: PropTypes.string
  }),
  match: PropTypes.shape({
    params: propTypes.object
  }),
  history: PropTypes.shape({
    push: PropTypes.func,
    replace: PropTypes.func,
    goBack: PropTypes.func
  }),
};

const matchRoute = (path, route) => {
  const r = toRegex(route);
  return r.test(path);
};

export { propTypes, matchRoute };
export * from './actions';
export { NavLink, Link } from 'react-router-dom';