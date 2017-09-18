import React, { Component } from 'react';
import { connect } from 'react-redux';

const DEFAULT_OPTIONS = {
  connectRedux: false,
  mapStateToProps: undefined,
  mapLoadingToProps: loading => ({ loading }),
  mapDataToProps: data => ({ data }),
  mapErrorToProps: error => ({ error }),
};

export function connectPromise(options) {
  return (Comp) => {
    const finalOptions = {
      ...DEFAULT_OPTIONS,
      ...options,
    };
    const {
      connectRedux,
      promiseLoader,
      mapLoadingToProps,
      mapStateToProps,
      mapDataToProps,
      mapErrorToProps
    } = finalOptions;

    class AsyncComponent extends Component {
      constructor(props) {
        super(props);
        this.state = {
          loading: true,
          data: undefined,
          error: undefined
        };
      }

      componentDidMount() {
        promiseLoader(this.props)
          .then(
            (data) => {
              console.log('async completed', data);
              this.setState({ data, loading: false });
            },
            error => this.setState({ error, loading: false }),
          );
      }

      render() {
        const { data, error, loading } = this.state;
        const dataProps = data ? mapDataToProps(data) : undefined;
        const errorProps = error ? mapErrorToProps(error) : undefined;
        return (
          <Comp
            {...mapLoadingToProps(loading)}
            {...dataProps}
            {...errorProps}
            {...this.props}
          />
        );
      }
    }

    return connectRedux
      ? connect(mapStateToProps)(AsyncComponent)
      : AsyncComponent;
  };
}

export default { connectPromise };
