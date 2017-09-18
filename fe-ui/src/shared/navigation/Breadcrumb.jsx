import React, { PropTypes } from 'react';
import { Link, matchRoute } from '../router';
import Menus from '../constants/navigation';

const findWayBackHome = (path, item, ret = []) => {
  if ((item.link && item.link === path) || (item.pattern && matchRoute(path, item.pattern))) {
    const { id, name, link } = item;
    ret.push({ id, name, link });
    return ret;
  }

  if (item.children) {
    for (let i = 0; i < item.children.length; i += 1) {
      const kidRet = findWayBackHome(path, item.children[i], ret);
      if (kidRet.length > 0) {
        const { id, name, link } = item;
        ret.push({ id, name, link });
        ret.concat(kidRet);
        return ret;
      }
    }
  }
  return ret;
};

const Breadcrumb = (props) => {
  const wayBackHome = findWayBackHome(props.path, Menus).reverse();
  return (
    <section className="content-header">
      <ol className="breadcrumb">
        {
          wayBackHome.map((bread, i) => {
            const disableLink = (i + 1 === wayBackHome.length) || !bread.link;
            return (
              <li key={bread.id} className={disableLink ? 'active' : ''}>
                {
                  disableLink ? <span>{bread.name}</span> : <Link to={bread.link}>{bread.name}</Link>
                }
              </li>
            );
          })
        }
      </ol>
    </section>
  );
};


Breadcrumb.propTypes = {
  path: PropTypes.string.isRequired
};

export default Breadcrumb;