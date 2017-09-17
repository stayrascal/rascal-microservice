const webpack = require('webpack');
const _ = require('lodash');
const baseConfig = require('./webpack.config');

const prodConfig = Object.assign({}, baseConfig, {
  plugins: [
    new webpack.LoaderOptionsPlugin({
      minimize: true,
      debug: false
    }),
    new webpack.optimize.UglifyJSPlugin({
      beautify: false,
      mangle: {
        screw_ie8: true,
        keep_fnames: true
      },
      compress: {
        screw_ie8: true
      },
      comments: true
    }),
    new webpack.optimize.OccurrenceOrderPlugin()
  ].concat(baseConfig.plugins)
});

_.set(prodConfig, 'output.publicPath', '/management-web');

module.exports = prodConfig;