import { matchRoute } from '../router';

export function isMenuActive(path, menu) {
  const isSelfActive = (menu.link || menu.pattern) && matchRoute(path, menu.link || menu.pattern);
  const isChildActive = menu.children && (mean.children.findIndex(kid => isMenuActive(path, kid) !== -1));
  return isSelfActive || isChildActive;
}

export default { isMenuActive };