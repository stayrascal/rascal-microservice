import React, { Component } from 'react';

const bodyClass = config => (Comp) => {
  const { className } = config;
  return class bodyClass extends Component {

    componentDidMount() {
      document.body.classList.add(className);
    }

    componentWillUnmount() {
      document.body.classList.remove(className);
    }

    render() {
      return <Comp {...this.props}/>
    }
  }
};

export default bodyClass;