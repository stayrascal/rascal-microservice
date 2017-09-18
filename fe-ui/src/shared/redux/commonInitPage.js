import userStorage from '../storage/user';
import accountActions from '../../account/actions';

export default function initPage(store) {
  store.dispatch(accountActions.ensureUser(userStorage.getUserSync()));
}