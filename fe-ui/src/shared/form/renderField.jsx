import React, { PropTypes } from 'react';
import classnames from 'classnames';
import { ControlLabel, FormGroup, HelpBlock } from 'react-bootstrap';
import { inputAttrPropType, metaAttrPropType } from './reduxFormPropTypes';

const renderField = (Comp) => {
  const RenderField = (props) => {
    const {
      input,
      meta: { touched, error },
      groupClassName,
      groupSize,
      fieldClassName,
      label,
      ...otherProps
    } = props;
    const validationState = touched && error ? 'error' : null;

    const finalGroupClassName = classnames('form-group', groupClassName);
    const finalFieldClassName = classnames('form-control', fieldClassName);

    const inputProps = typeof Comp === 'string'
      ? input
      : { input, meta: props.meta };

    return (
      <FormGroup bsClass={finalGroupClassName} bsSize={groupSize} validationState={validationState}>
        <ControlLabel>
          {label}
        </ControlLabel>
        <Comp
          className={finalFieldClassName}
          {...inputProps}
          {...otherProps}
        />
        {touched && error && <HelpBlock>{error}</HelpBlock>}
      </FormGroup>
    );
  };

  RenderField.propTypes = {
    input: inputAttrPropType.isRequired,
    meta: metaAttrPropType.isRequired,
    label: PropTypes.node,
    groupClassName: PropTypes.string,
    fieldClassName: PropTypes.string,
    groupSize: PropTypes.oneOf(['lg', 'large', 'sm', 'small']),
  };

  RenderField.defaultProps = {
    label: '',
    groupClassName: '',
    fieldClassName: '',
    groupSize: undefined,
  };

  return RenderField;
};

export default renderField;
