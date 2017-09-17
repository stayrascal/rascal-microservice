const webpack = require('webpack');

const baseConfig = require('./webpack.config');

function mergeEntry() {
  baseConfig.entry.app = [
    `webpack-dev-server/client?`, // WebpackDevServer host and port
    'webpack/hot/only-dev-server'// "only" prevents reload on syntax errors
  ].concat(baseConfig.entry.app);
  return baseConfig.entry;
}
module.exports = Object.assign({}, baseConfig, {
  devtools: 'cheap-module-source-map',
  entry: mergeEntry(),
  plugins: [new webpack.HotModuleReplacementPlugin()].concat(baseConfig.plugins)
});