import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import _ from 'lodash';

import actions from '../actions';
import LoginForm from '../components/LoginForm';
import { withRedux } from '../../shared/redux';
import bodyClass from '../../shared/utils/bodyClass';

import './Login.less';

class Login extends Component {
  handleSubmit = user => this.props.signInAction(user);

  render() {
    return (
      <div className="login-page-body">
        <div className="login-page-box">
          <div className="login-box-body">
            <LoginForm onSubmit={this.handleSubmit}/>
          </div>
        </div>
      </div>
    );
  }
}

Login.propTypes = {
  signInAction: PropTypes.func.isRequired
};

const decorator = _.flowRight(
  withRedux(),
  bodyClass({
    className: 'login-page',
  }),
  connect(state => state, {
    signInAction: actions.signInAction
  })
);

export default decorator(Login);