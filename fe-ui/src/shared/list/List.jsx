import React, { Component, PropTypes } from 'react';
import _ from 'lodash';
import { Pagination, Table } from 'react-bootstrap';
import SortSwitcher from '../../shared/list/SortSwitcher';
import { propTypes as listPropTypes } from '../../shared/list/reduxList';

const SELECT_MODES = {
  MULTI: 'MULTI',
  SINGLE: 'SINGLE'
};

class List extends Component {
  static propTypes = {
    idField: PropTypes.string,
    selectMode: PropTypes.oneOf([SELECT_MODES.MULTI, SELECT_MODES.SINGLE]),
    selectedItems: PropTypes.arrayOf(PropTypes.string),
    disableRowSelect: PropTypes.func,
    columns: PropTypes.arrayOf(PropTypes.shape({
      key: PropTypes.string,
      name: PropTypes.oneOfType([PropTypes.string, PropTypes.func]),
      render: PropTypes.oneOfType([PropTypes.string, PropTypes.func]),
      sortable: PropTypes.bool,
      width: PropTypes.string,
    })).isRequired,
    sortParams: listPropTypes.sortParams,
    list: listPropTypes.listData,
    onSelectPage: PropTypes.func.isRequired,
    onSort: PropTypes.func.isRequired,
    onSelect: PropTypes.func,
    noResultsMessage: PropTypes.string
  };

  static defaultProps = {
    idField: 'id',
    selectMode: undefined,
    selectedItems: [],
    sortParams: {},
    list: {
      items: [],
      pageItems: 0,
      pageIndex: 0,
    },
    disableRowSelect: () => false,
    onSelect(selectedItems) {
      console.warn(selectedItems, 'Please pass onSelect to List for enable select feature');
    },
    noResultsMessage: 'No results',
  };

  static SELECT_MODES = SELECT_MODES;

  constructor(props) {
    super(props);

    this.selectInputName = _.uniqueId('LIST_SELECT_');
  }

  handleToggleAllSelect = isCurrentAllSelected => () => {
    const { onSelect, disableRowSelect, list: { items = [] } } = this.props;

    const selectableDatas = _.reject(items, disableRowSelect);

    const nextSelectedItems = isCurrentAllSelected
      ? []
      : selectableDatas.map(v => v.id);

    onSelect(nextSelectedItems);
  };

  handleToggleRowSelect = (data, isCurrentSelected) => () => {
    const { onSelect, selectedItems, idField, selectMode } = this.props;
    const rowId = data[idField];

    let nextSelectedItems;

    if (selectMode === SELECT_MODES.MULTI) {
      nextSelectedItems = isCurrentSelected
        ? _.without(selectedItems, rowId)
        : [...selectedItems, rowId];
    } else {
      nextSelectedItems = [rowId];
    }

    onSelect(nextSelectedItems);
  };

  checkRowSelectedStatus(rowItem) {
    const { selectedItems, idField } = this.props;

    return _.includes(selectedItems, rowItem[idField]);
  }

  checkAllSelectedStatus() {
    const { selectedItems, disableRowSelect, list: { items } } = this.props;

    const selectableDatas = _.reject(items, disableRowSelect);

    if (!selectableDatas || !selectableDatas.length || !selectedItems.length) {
      return false;
    }

    const dataIds = selectableDatas.map(d => d.id);

    return _.xor(dataIds, selectedItems).length === 0;
  }

  renderRows() {
    const { list: { items }, columns, idField } = this.props;
    return items.map(data => (
      <tr key={data[idField]}>
        {this.renderSelectRow(data)}
        {
          columns.map(({ render, key }) => (
            <td key={key} className="cell-content-container">
              <div className="cell-content">
                {
                  _.isFunction(render)
                    ? render(data)
                    : _.get(data, render || key)
                }
              </div>
            </td>
          ))
        }
      </tr>
    ));
  }

  renderSelectRow(data) {
    const { selectMode, disableRowSelect } = this.props;

    if (!selectMode) {
      return false;
    }

    const selectorType = selectMode === SELECT_MODES.MULTI
      ? 'checkbox'
      : 'radio';

    const isRowSelected = this.checkRowSelectedStatus(data);
    const isRowDisabled = disableRowSelect(data);

    return (
      <td style={{ width: '50px' }} className="cell-content-container">
        <div className="cell-content">
          <input
            type={selectorType}
            onChange={this.handleToggleRowSelect(data, isRowSelected)}
            name={this.selectInputName}
            disabled={isRowDisabled}
            checked={isRowSelected}
          />
        </div>
      </td>
    );
  }

  renderSelectAll() {
    const { selectMode } = this.props;

    // only render select all when selectMode is multi
    if (!selectMode) {
      return false;
    }

    if (selectMode !== SELECT_MODES.MULTI) {
      return (
        <th style={{ width: '50px' }} className="cell-content-container">
          <div className="cell-content"/>
        </th>
      );
    }

    const isAllSelected = this.checkAllSelectedStatus();

    return (
      <th style={{ width: '50px' }} className="cell-content-container">
        <div className="cell-content">
          <input
            type="checkbox"
            onChange={this.handleToggleAllSelect(isAllSelected)}
            checked={isAllSelected}
          />
        </div>
      </th>
    );
  }

  render() {
    const {
      columns,
      sortParams,
      list: { items, pageItems, pageIndex, loading },
      onSort,
      onSelectPage,
      noResultsMessage,
    } = this.props;
    return (
      <div className="list-table">
        <div className="list-table-container box-body no-padding">
          <Table hover className="table-striped--reversed">
            <thead>
            <tr>
              {
                this.renderSelectAll()
              }
              {columns.map(({ key, width, name, sortable }) => {
                const style = width ? { width } : undefined;
                const finalName = typeof name === 'function' ? name() : name;
                return (
                  <th key={key} style={style} className="cell-content-container">
                    <div className="cell-content">
                      {finalName}
                      {
                        sortable && <SortSwitcher
                          sortType={sortParams.sortType}
                          currentSortBy={sortParams.sortBy}
                          sortBy={key}
                          onSortChange={onSort}
                        />
                      }
                    </div>
                  </th>
                );
              })}
            </tr>
            </thead>
            <tbody>
            {
              !(items && items.length)
                ? (loading
                ? <tr>
                  <td colSpan="2">Loading</td>
                </tr>
                : <tr>
                  <td colSpan="2">{noResultsMessage}</td>
                </tr>)
                : this.renderRows()
            }
            </tbody>
          </Table>
        </div>
        <div className="box-footer clearfix">
          <Pagination
            className="no-margin pull-right"
            bsSize="small"
            prev
            next
            first
            last
            maxButtons={5}
            items={pageItems}
            activePage={pageIndex + 1}
            onSelect={onSelectPage}
          />
        </div>
      </div>
    );
  }
}
export default List;