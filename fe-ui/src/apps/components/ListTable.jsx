import React from 'react';
import _ from 'lodash';
import { Pagination, Table } from 'react-bootstrap';

const ListTable = ({
                     columns, // Definition of columns
                     list: { items, pageSize, pageIndex, totalCount }, // List data
                     onSelectPage, // Pagination callback
                   }) => (
  <div className="list-table">
    <div className="list-table-container box-body no-padding">
      <Table hover className="table-striped--reversed">
        <thead>
        <tr>
          {columns.map(({ key, name }) => (
            <th key={key} className="cell-content-container">
              <div className="cell-content">
                {name || key}
              </div>
            </th>
          ))}
        </tr>
        </thead>
        <tbody>
        {
          !(items && items.length)
            ? <tr>
            <td>Nothing here</td>
          </tr>
            : items.map(data => (
            <tr key={data.id}>
              {
                columns.map(({ key }) => (
                  <td key={key} className="cell-content-container">
                    <div className="cell-content">
                      {
                        _.get(data, key)
                      }
                    </div>
                  </td>
                ))
              }
            </tr>
          ))
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
        items={Math.round(totalCount / pageSize)}
        activePage={pageIndex}
        onSelect={onSelectPage}
      />
    </div>
  </div>
);

export default ListTable;
