import React from 'react';
import { Route, Switch } from 'react-router-dom';

import Account from './account';
import Apps from './apps';

function getRoutes() {
  return (
    <Switch>
      <Route exact path={'/sign-in'} component={Account.Login}/>
      <Route exact path={'/hello'} component={Apps.Hello}/>
    </Switch>
  );
}

export default getRoutes;