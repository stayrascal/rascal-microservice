import React, { PropTypes } from 'react';

import './Header.less';

const Header = ({ namePlaceholder, onSignOut }) => (
  <header className="main-header">
    <a className="logo">
      <span className="logo-mini"><b>R</b>W</span>
      <span className="logo-lg"><b>RETAIL</b> WISE</span>
    </a>
    <nav className="navbar navbar-static-top">
      <a className="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span className="sr-only">Toggle navigation</span>
        <span className="icon-bar"/>
        <span className="icon-bar"/>
        <span className="icon-bar"/>
      </a>

      <div className="navbar-custom-menu">
        <ul className="nav navbar-nav">
          <li className="dropdown user user-menu">
            <a className="dropdown-toggle" data-toggle="dropdown">
              {namePlaceholder} <span className="caret"/>
            </a>
            <ul className="dropdown-menu">
              <li>
                <button type="button" className="btn btn-default" onClick={onSignOut}>Sign out</button>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
);

Header.propTypes = {
  onSignOut: PropTypes.func.isRequired,
  namePlaceholder: PropTypes.string,
};

Header.defaultProps = {
  namePlaceholder: '',
};

export default Header;
