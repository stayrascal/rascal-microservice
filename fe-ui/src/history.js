import _ from 'lodash';
import createHistory from 'history/createBrowserHistory';

const basename = _.get(process.env.config, 'server.basename');

const history = createHistory({
  basename,
});

export default history;