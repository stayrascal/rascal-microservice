import moment from 'moment';

const DATE_TIME_FORMAT = 'YYYY/MM/DD HH:mm';
const DATE_FORMAT = 'YYYY/MM/DD';

export function serializeMomentArray(dateArray = []) {
  return dateArray.map(date => (date ? +date : '')).join(',');
}

export function deserializeMomentArray(dateArrayString = '') {
  return dateArrayString.split(',').map(dateStr => (dateStr ? moment(parseInt(dateStr, 10)) : undefined));
}

export function formatDatetime(dateString) {
  return dateString ? moment(dateString).format(DATE_TIME_FORMAT) : undefined;
}

export function setStartOfDay(date) {
  const result = moment(date);
  result.hour(0);
  result.minute(0);
  result.second(0);
  return result;
}

export function setEndOfDay(date) {
  const result = moment(date);
  result.hour(23);
  result.minute(59);
  result.second(59);
  return result;
}

export function transformDateForRequest(dateField,
                                        targetStartField = 'from_date',
                                        targetEndField = 'to_date',
                                        request) {
  const {
    [dateField]: dateValue,
    ...otherResults
  } = request;

  let dateObj;

  if (dateValue) {
    const [startDate, endDate] = deserializeMomentArray(dateValue);

    dateObj = Object.assign(
      {},
      startDate ? {
        [targetStartField]: +setStartOfDay(startDate),
      } : undefined,
      endDate ? {
        [targetEndField]: +setEndOfDay(endDate),
      } : undefined
    );
  }

  return {
    ...dateObj,
    ...otherResults,
  };
}

export const isFutureDay = d => setStartOfDay(d).isAfter(setStartOfDay(new Date()));

export const getLastMonth = () => moment().subtract(1, 'months');

export { DATE_TIME_FORMAT, DATE_FORMAT };

export default  {
  DATE_FORMAT,
  DATE_TIME_FORMAT,
  formatDatetime,
};