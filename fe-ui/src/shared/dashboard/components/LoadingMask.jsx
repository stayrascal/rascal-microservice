import React from 'react';
import bodyClass from '../../utils/bodyClass';
import './LoadingMask.less';

import spinImg from '../assets/spin.gif';

const LoadingMask = () => (
  <div className="loading-mask">
    <div className="loading-mask__content">
      <img src={spinImg} alt="Loading"/>
      Loading...
    </div>
  </div>
);

export default bodyClass({ className: 'loading-active' })(LoadingMask);

