import cx from 'classnames';

export const getListModifierClass = (prefix, list) => {
  const { loading, items } = list;
  return cx({
    [`${prefix}--empty`]: !loading && !(items && items.length),
    [`${prefix}--loading`]: loading
  });
};

export default { getListModifierClass };