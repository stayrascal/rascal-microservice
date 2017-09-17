const path = require('path');
const dyson = require('dyson');

const PORT = 3001;

dyson.bootstrap({
  configDir: path.resolve(__dirname, 'services'),
  port: PORT,
  delay: [200, 800],
});

console.log(`Dyson listening at port ${PORT}`);