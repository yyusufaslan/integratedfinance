# integratedfinance
integrated finance interview project

API's:

1. Exchange Rate API
○ input: currency pair to retrieve the exchange rate
○ output: exchange rate

curl --location --request POST 'http://localhost:8080/api/listExchangeRate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "sourceCurrency" : "USD",
    "targetCurrency" : "EUR"
}'


2. Exchange API:
○ input: source amount, source currency, target currency
○ output: amount in target currency, and transaction id.

curl --location --request POST 'http://localhost:8080/api/createExchange' \
--header 'Content-Type: application/json' \
--data-raw '{
    "sourceAmount" : 15,
    "sourceCurrency" : "USD",
    "targetCurrency" : "TRY"
}'

3. Exchange List API
○ input: transaction id or conversion date
i. only one of the inputs shall be provided for each call
○ output: list of conversions filtered by the inputs

curl --location --request POST 'http://localhost:8080/api/listExchange' \
--header 'Content-Type: application/json' \
--data-raw '{
    "transactionId" : 8715737984178226072
}'
