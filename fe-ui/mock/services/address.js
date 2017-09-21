const faker = require('faker');

const template = {
  language: () => 'en_US',
  parentId: () => 0,
  path: () => faker.address.streetAddress(),
  grade: () => 0,
  name: () => faker.address.streetAddress(),
  nameEn: () => faker.address.streetAddress(),
};

const PAGE_SIZE = 10;

const list = {
  path: '/desktop/address',
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