const faker = require('faker');

const template = {
  mobile: () => faker.phone.phoneNumber(),
  email: () => faker.internet.email(),
  name: (params, query) => `${query.name || ''} ${faker.name.findName()}`,
  authorizations: (params, query) => (query.role || faker.random.arrayElement(['ADMIN', 'CLIENT'])),
};

const PAGE_SIZE = 10;

const list = {
  path: '/desktop/users',
  method: 'GET',
  collection: true,
  template,
  size: PAGE_SIZE,
  container: {
    totalCount: 20,
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