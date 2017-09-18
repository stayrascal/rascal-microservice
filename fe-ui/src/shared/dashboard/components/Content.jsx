import React, { PropTypes } from 'react';
import cx from 'classnames';
import { propTypes as routerPropTypes } from '../../router';

import Breadcrumb from '../../navigation/Breadcrumb';

import './Content.less';

const Content = ({ className, pageTitle, children, location }) => (
  <div className={cx('page-content', className)}>
    <Breadcrumb path={location.pathname}/>
    <section className="content-header">
      <h1>{pageTitle}</h1>
    </section>
    <section className="content">
      {children}
    </section>
  </div>
);

Content.propTypes = {
  location: routerPropTypes.location.isRequired,
  children: PropTypes.node.isRequired,
  pageTitle: PropTypes.string.isRequired,
  className: PropTypes.string,
};

Content.defaultProps = {
  className: '',
};

export default Content;
