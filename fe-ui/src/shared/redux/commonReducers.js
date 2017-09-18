import { reducer as formReducer } from 'redux-form';

import { listReducer } from '../list';
import userReducer from '../../account/userReducer';
import { loadingReducer } from '../../shared/loadingAndError';

export default {
  form: formReducer,
  currentUser: userReducer,
  listView: listReducer,
  loading: loadingReducer
};