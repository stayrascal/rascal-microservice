import React from 'react';
import { flowRight } from 'lodash';
import { connect } from 'react-redux';
import { withRedux } from '../../shared/redux';
import { layoutWrapper } from '../../shared/dashboard';

const Hello = ({ currentUser }) => (<div>Hello: {currentUser.name} !</div>);

const decorator = flowRight([
  withRedux(),
  layoutWrapper,
  connect(state => state)
]);

export default decorator(Hello);