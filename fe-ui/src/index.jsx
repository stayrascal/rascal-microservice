import 'babel-polyfill';
import React from 'react';
import { render } from 'react-dom';
import { Router } from 'react-router';
import _ from 'lodash';
import './shared/theme';

import getRoutes from './routes';
import history from './history';

const basename = _.get(process.env.config, 'server.basename');

let routes = getRoutes();

function renderApp() {
  render(
    <Router history={history} basename={basename}>
      <div id="root-app">
        {routes}
      </div>
    </Router>,
    document.getElementById('app')
  );
}

renderApp();

if (module.hot) {
  module.hot.accept('./routes', () => {
    const nextGetRoutes = require('./routes').default;
    routes = nextGetRoutes();
    renderApp();
  });
}
