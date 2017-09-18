import { PropTypes } from 'react';

const inputAttrPropType = PropTypes.shape({
  value: PropTypes.oneOfType([PropTypes.string, PropTypes.array]),
  onChange: PropTypes.func,
});

const getInputPropType = valueType => PropTypes.shape({
  value: valueType,
  onChange: PropTypes.func,
});

const metaAttrPropType = PropTypes.shape({
  invalid: PropTypes.bool,
  error: PropTypes.string,
});

const fieldPropType = PropTypes.shape({
  input: inputAttrPropType,
  meta: metaAttrPropType,
});

export { fieldPropType, inputAttrPropType, metaAttrPropType, getInputPropType };
export default { fieldPropType };
