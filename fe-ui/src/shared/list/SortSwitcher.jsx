import React, { Component, PropTypes } from 'react';
import cx from 'classnames';

import './SortSwitcher.less';

const SORT_TYPE = {
  INC: 'ASC',
  DEC: 'DESC',
};
const SORT_TYPES = [SORT_TYPE.INC, SORT_TYPE.DEC];

class SortSwitcher extends Component {
  static propTypes = {
    sortType: PropTypes.oneOf(SORT_TYPES),
    sortBy: PropTypes.string.isRequired,
    currentSortBy: PropTypes.string,
    onSortChange: PropTypes.func.isRequired,
  };
  static defaultProps = {
    sortType: undefined,
    currentSortBy: undefined,
  };

  constructor(props) {
    super(props);

    this.handleSwitch = this.handleSwitch.bind(this);
  }

  handleSwitch() {
    const { sortType: currentSortType } = this.props;

    const nextSortType = currentSortType
      ? (currentSortType === SORT_TYPE.INC
        ? SORT_TYPE.DEC
        : SORT_TYPE.INC)
      : SORT_TYPE.INC;

    this.props.onSortChange({
      sortType: nextSortType,
      sortBy: this.props.sortBy,
    });
  }

  render() {
    const { sortType } = this.props;
    const isActive = this.props.sortBy === this.props.currentSortBy;
    const className = cx('sort-switcher', {
      [`sort-switcher--${sortType}`]: isActive,
    });

    return (
      <div className={className} onClick={this.handleSwitch}>
        {
          (!isActive || !sortType)
            ? (
            <div>
              <i className="fa fa-long-arrow-down"/>
              <i className="fa fa-long-arrow-up"/>
            </div>
          )
            : (
            sortType === SORT_TYPE.INC
              ? <i className="fa fa-long-arrow-down"/>
              : <i className="fa fa-long-arrow-up"/>
          )
        }
      </div>
    );
  }
}

export { SORT_TYPE };

export default SortSwitcher;
