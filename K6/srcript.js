import http from 'k6/http';
import { check } from 'k6';

export default function () {
    const url = 'http://localhost:8080/api/deals';
    const payload = JSON.stringify([{
        dealUniqueId: 'D-' + Math.floor(Math.random() * 100000),
        fromCurrencyIsoCode: 'USD',
        toCurrencyIsoCode: 'EUR',
        dealTimestamp: new Date().toISOString(),
        dealAmount: 1000.0
    }]);
    const params = { headers: { 'Content-Type': 'application/json' } };
    let res = http.post(url, payload, params);
    check(res, { 'status 200': (r) => r.status === 200 });
}
