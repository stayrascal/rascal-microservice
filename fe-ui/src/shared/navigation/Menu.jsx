import React, { PropTypes } from 'react';
import cx from 'classnames';
import { Link } from '../router';
import navigationConfig from '../constants/navigation';
import { isMenuActive } from './utils';

const MenuItem = props => (
  <Link to={props.item.link || ''}>
    {props.item.icon ? <i className={`fa ${props.item.icon}`}/> : null }
    <span>{props.item.name}</span>
    {
      props.isParent ? (
        <span className="pull-right-container">
          <i className="fa fa-angle-left pull-right"/>
        </span>
      ) : null
    }
  </Link>
);

MenuItem.propTypes = {
  item: PropTypes.shape({
    name: PropTypes.string,
    link: PropTypes.string,
    icon: PropTypes.string
  }).isRequired,
  isParent: PropTypes.bool.isRequired
};

const MenuIterator = (props) => {
  const { menu, path } = props;
  if (menu.pattern || menu.hidden) return null;

  const className = cx('treeview', {
    active: isMenuActive(path, menu)
  });

  const isParent = !!menu.children
    && !menu.hideChildrenInMenu
    && !!menu.children.find(child => child.link);

  return (
    <li className={className}>
      <MenuItem item={menu} isParent={isParent}/>
      {
        isParent ? (
          <ul className="treeview-menu">
            { menu.children.map(kid => <MenuIterator menu={kid} key={kid.id} path={path}/>)}
          </ul>
        ) : null
      }
    </li>
  );
};

MenuIterator.propTypes = {
  menu: PropTypes.shape({
    id: PropTypes.string,
    children: PropTypes.array
  }).isRequired,
  path: PropTypes.string.isRequired
};

const Menu = (props) => {
  const navConfig = navigationConfig;
  return (
    <ul className="sidebar-menu">
      <li className="header">MAIN NAVIGATION</li>
      {
        navConfig.children.map(item => <MenuIterator menu={item} key={item.id} path={props.path}/>)
      }
    </ul>
  );
};

Menu.propTypes = {
  path: PropTypes.string.isRequired
};

export default Menu;