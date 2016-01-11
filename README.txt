The web service named transactionservice supports the following methods:

PUT

Request:
To create a transaction, PUT request can be used with the following URL:
http://localhost:8080/TransactionManagement/transactionservice/transaction/$transactionid(where server name and port (localhost:8080) must be changed accordingly and $transactionid must be a long)
along with the x-www-form-urlencoded body containing amount, type and/or parent id of the transaction.

Response:
JSON String with "status" as key and "OK" as value for successful transaction and various error strings for unsuccessful transaction.


GET

Request:
To retrieve a particular transaction with a given transaction id
http://localhost:8080/TransactionManagement/transactionservice/transaction/$transactionid
Response:
JSON String with transaction details or JSON string with "status" as key and various error strings for unsuccessful retrieval of the transaction.

Request:
To retrieve the transaction ids of all the transactions with a particular transaction type
http://localhost:8080/TransactionManagement/transactionservice/transaction/type/$type
Response:
String of array of all the transaction ids of the given type.


Request:
To retrieve the sum of all the transaction having parent id shared by the parent id of the given transaction id.
http://localhost:8080/TransactionManagement/transactionservice/sum/$transactionid
Response:
JSON String with "sum" as key and sum of all the transactions as value.

POST
http://localhost:8080/TransactionManagement/transactionservice
along with the x-www-form-urlencoded body containing transaction id, amount, type and/or parent id of the transaction.
Response:
JSON String with "status" as key and "OK" as value for successful transaction and various error strings for unsuccessful transaction.

The transaction service can be tested using Postman Application provided by Chrome.

A java client has also been developed to create the transaction and test it.
The project is also added in the github and can be accessed at the following url:
https://github.com/nawab36085/TransactionClient.git
Test method:
Provide the arguments to the program as follows:
serverName transactionId transactionAmount transactionType transactionParentId(optional)

Example:
http://localhost:8080 10 197.67 travel 0
or
http://localhost:8080 10 197.67 travel