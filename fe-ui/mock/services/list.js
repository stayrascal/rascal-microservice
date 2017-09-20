const faker = require('faker');

const template = {
  id: () => faker.random.uuid(),
  name: (params, query) => `${query.name || ''} ${faker.name.findName()}`,
  role: (params, query) => (query.role || faker.random.arrayElement(['ADMIN', 'CLIENT'])),
  address: () => faker.address.streetAddress()
};

const PAGE_SIZE = 10;

const list = {
  path: '/desktop/staff/list',
  method: 'GET',
  collection: true,
  template,
  size: PAGE_SIZE,
  container: {
    totalCount: 49,
    pageSize: PAGE_SIZE,
    pageIndex: function (params, query) {
      return parseInt(query['pageIndex']) || 0;
    },
    items: function (params, query, data) {
      return data;
    }
  }
};

module.exports = list;