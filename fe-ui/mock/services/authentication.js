const faker = require('faker');

const template = {
  "identityType": () => faker.random.arrayElement(['MEMBER', 'STAFF']),
  "identityId": () => faker.random.uuid(),
  "authenticationType": () => faker.random.arrayElement(['EMAIL_TRANSIENT_KEY', 'PASSWORD']),
  "authenticationName": () => faker.name.findName(),
  "primaryKeyValue": () => faker.random.arrayElement(['EMAIL_TRANSIENT_KEY', 'PASSWORD']),
  "primaryKeyExpireTime": () => faker.date.recent()
};

const PAGE_SIZE = 10;

const list = {
  path: '/desktop/authentication',
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