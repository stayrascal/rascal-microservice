import React, { Component } from 'react';

export default class SearchBar extends Component {
  static defaultProps = {
    searchParams: {
      name: '',
      role: 'CLIENT',
    },
  };

  constructor(props) {
    super();
    this.state = {
      values: { ...props.searchParams },
    };
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      values: nextProps.searchParams,
    });
  }

  handleSubmit = () => {
    this.props.onSearch(this.state.values);
  };

  handleInputChange = (e) => {
    this.setState({
      values: {
        ...this.state.values,
        name: e.target.value,
      },
    });
  };

  handleSelectChange = (e) => {
    this.setState({
      values: {
        ...this.state.values,
        role: e.target.value,
      },
    });
  };

  render() {
    return (
      <div >
        <label >
          <span>Role: </span>
          <select
            name="role"
            value={this.state.values.role}
            onChange={this.handleSelectChange}
          >
            <option value=""/>
            <option value="ADMIN">Admin</option>
            <option value="CLIENT">Client</option>
          </select>
        </label>
        <label >
          <span>Name: </span>
          <input
            type="text"
            name="name"
            value={this.state.values.name || ''}
            onChange={this.handleInputChange}
          />
        </label>
        <button onClick={this.handleSubmit}>Search</button>
      </div>
    );
  }
}
