export function formatCurrency(value) {
  const num = Number(value);

  return window.isNaN(num)
    ? ''
    : num.toLocaleString('zh', { style: 'currency', currency: 'CNY' });
}

export function displayDashWhenUndefined(value) {
  return (value === undefined || value === null) ? '----' : value;
}

export default { formatCurrency, displayDashWhenUndefined }