import { go, goBack, goForward, push, replace } from 'react-router-redux';
import makePath from '../utils/makePath';

const wrapMethod = method => (pathConfig, ...args) => {
  let finalPath;
  if (typeof pathConfig === 'string') {
    finalPath = pathConfig
  } else {
    const { path, params, query } = pathConfig;
    finalPath = makePath(path, params, query);
  }
  return method(finalPath, ...args);
};

const pushWrapped = wrapMethod(push);
const replaceWrapped = wrapMethod(replace);

const routerActions = {
  go,
  goBack,
  goForward,
  push: pushWrapped,
  replace: replaceWrapped
};

export  { go, goBack, goForward, pushWrapped as push, replaceWrapped as replace, routerActions }