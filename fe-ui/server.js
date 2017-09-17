const path = require('path');
const webpack = require('webpack');
const WebpackDevServer = require('webpack-dev-server');
const config = require('./webpack/webpack.dev.config');
const openBrowser = require('react-dev-utils/openBrowser');

const HOST = '0.0.0.0', PORT = '3000';
const serverUrl = `http://${HOST}:${PORT}`;

const publicPath = config.output.publicPath;

new WebpackDevServer(webpack(config), {
  publicPath: path.isAbsolute(publicPath) ? publicPath : path.resolve('/', publicPath),
  hot: true,
  historyApiFallback: true
}).listen(PORT, HOST, function (err, result) {
  if (err) {
    return console.log(err);
  }

  console.log(`Server started on ${serverUrl}`);

  if (openBrowser(serverUrl)) {
    console.log('Browser tab has been opened!')
  } else {
    console.warn('Failed to open browser tab, please open it by yourself')
  }
});