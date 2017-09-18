import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import _ from 'lodash';

import accountActions from '../../account/actions';

import Header from './components/Header';
import Sidebar from './components/Sidebar';
import Content from './components/Content';
import LoadingMask from './components/LoadingMask';

import './index.less';

class DashboardComponent extends Component {
  componentDidMount() {
    document.body.classList.add('hold-transition', 'skin-blue', 'fixed', 'sidebar-mini');
  }

  componentWillUnmount() {
    document.body.classList.remove('hold-transition', 'skin-blue', 'fixed', 'sidebar-mini');
  }

  handleSignOut = () => this.props.signOutAction();

  render() {
    const { currentUser, children, location, loading } = this.props;
    return (
      <div className="dashboard wrapper">
        <Header onSignOut={this.handleSignOut} namePlaceholder={_.get(currentUser, 'name')}/>
        <Sidebar path={location.pathname} user={currentUser}/>
        <div className="content-wrapper page-content-wrapper loading-mask-container">
          {children}
          {
            loading && <LoadingMask />
          }
        </div>
      </div>
    );
  }
}

DashboardComponent.propTypes = {
  children: PropTypes.element.isRequired,
  signOutAction: PropTypes.func.isRequired,
  currentUser: PropTypes.shape({
    staff: PropTypes.shape({
      name: PropTypes.string,
    }),
  }),
  location: PropTypes.shape({
    pathname: PropTypes.string,
  }),
  loading: PropTypes.bool,
};

DashboardComponent.defaultProps = {
  currentUser: { staff: { name: '' } },
  location: { pathname: '' },
  loading: false,
};


const decorator = _.flowRight(
  connect(state => ({
    currentUser: state.currentUser,
    loading: state.loading,
  }), {
    signOutAction: accountActions.signOutAction,
  }),
);

const ConnectedDashboard = decorator(DashboardComponent);

const layoutWrapper = InnerContent => (
  function DashboardLayout(props) {
    return (
      <ConnectedDashboard {...props}>
        <InnerContent {...props} />
      </ConnectedDashboard>
    );
  }
);


export { DashboardComponent, layoutWrapper, Content };

export default ConnectedDashboard;
