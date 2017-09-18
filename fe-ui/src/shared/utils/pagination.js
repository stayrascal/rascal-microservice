const getPages = ({ totalCount, pageSize }) => (Math.ceil(totalCount / pageSize));

export {
  getPages
};

export default { getPages };