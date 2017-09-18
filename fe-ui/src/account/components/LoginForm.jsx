import React from 'react';
import { Field, propTypes, reduxForm } from 'redux-form';
import renderField from '../../shared/form/renderField';
import './LoginForm.less';

const renderInput = renderField('input');

const LoginForm = (props) => {
  const { error, handleSubmit, submitting, onSubmit, invalid } = props;
  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="login-subject">
        <label className="label-title" htmlFor="login-subject">LOG IN</label>
        <p>Welcome, please login</p>

        {error && <div className="alert alert-danger login-error-alert">
          <i className="fa fa-ban"/>
          <span>Invalid username or password!</span>
        </div>
        }
      </div>

      <div className="login-field">
        <label htmlFor="accountId">Username</label>
        <Field
          name="accountId"
          id="accountId"
          type="text"
          component={renderInput}
        />
      </div>
      <div className="login-field">
        <label htmlFor="password">Password</label>
        <Field
          name="password"
          id="password"
          type="password"
          component={renderInput}
        />
      </div>
      <div className="row">
        <button
          className="btn btn-primary btn-block btn-flat"
          type="submit"
          disabled={invalid || submitting}
        >Sign In
        </button>
      </div>
    </form>
  );
};

LoginForm.propTypes = propTypes;

export default reduxForm({
  form: 'loginForm',
})(LoginForm);
