const faker = require('faker');

const isManager = accountId => accountId === 'manager_id';

const signIn = {
  path: '/desktop/staff/sign-in',
  method: 'POST',
  template: {
    token: faker.random.uuid(),
    name: faker.name.findName(),
    role: function (params, query, body) {
      return isManager(body.accountId) ? 'MANAGER' : 'ADMIN';
    },
  },
  render: function (req, res, next) {
    if (req.body.accountId == 'NULL') {
      res.sendStatus(401);
    } else {
      res.send(res.body);
    }
    next();
  }
};

module.exports = signIn;