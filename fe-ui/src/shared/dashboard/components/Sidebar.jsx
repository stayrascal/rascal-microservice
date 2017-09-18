import React, { PropTypes } from 'react';

import Menu from '../../navigation/Menu';

import './Sidebar.less';

const Sidebar = props => (
  <aside className="main-sidebar">
    <section className="sidebar">
      <Menu {...props} />
    </section>
  </aside>
);

Sidebar.propTypes = {
  path: PropTypes.string.isRequired,
};

export default Sidebar;
