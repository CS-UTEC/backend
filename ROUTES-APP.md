#  Routes App

* Use this route to login

#### Route: /login/app
#### Method: POST
#### Body example
```js
{
	"document": "12345678",
	"type": "DNI",
	"departamento": "Lima",
	"provincia": "Lima",
	"distrito": "Barranco"
}
```
#### Response example
```js
{
    "userId": 4,
}
```

* Use this route to get the interval between ubication requests
- The value is in minutes

#### Route: /ubication/interval
#### Method: GET
#### Body example
#### Response example
```js
{
    "interval": 60
}
```

#### Route: /ubication/report
#### Method: POST
#### Body example
```js
{
	"document": "12345678",
	"type": "DNI",
	"latitude": 12,
	"longitude": 13
}
```
#### Response

Check HTTP status

* Use this route to delete the ubication data store of a user

#### Route: /ubication/delete-data
#### Method: DELETE
#### Body example
```js
{
	"document": "12345678",
	"type": "DNI",
}
```
#### Response

Check HTTP status


* Use this route to get all the notifications of a user

#### Route: /notification/get-all
#### Method: GET
#### Body example
```js
{
	"document": "12345678",
	"type": "DNI",
}
```
#### Response
```js
[
    {
        "id": "5e7e62e4dc7cb027e6cde9ce",
        "timeStamp": "2020-03-27T15:32:36.686-05:00",
        "message": "Mensaje de hoy",
        "checked": false
    },
    {
        "id": "5e7e62e1dc7cb027e6cde9cd",
        "timeStamp": "2020-03-27T15:32:33.067-05:00",
        "message": "Mensaje de hoy",
        "checked": false
    },
    {
        "id": "5e7e62dbdc7cb027e6cde9cc",
        "timeStamp": "2020-03-27T15:32:27.894-05:00",
        "message": "Mensaje de hoy",
        "checked": false
    },
    {
        "id": "5e7e6236dc7cb027e6cde9cb",
        "timeStamp": "2020-03-27T15:29:41.988-05:00",
        "message": "Mensaje de hoy",
        "checked": false
    }
]
```

* Use this route to change the state of a notification

#### Route: /notification/mark
#### Method: GET
#### Body example
```js
{
  "document": "12345678",
  "type": "DNI",
  "notificationId": "5e7ac8b37dfe8a62abffda5f",
  "checked": true
}
```
### Response
Check HTTP status

* Use this route to report symptoms and get a score an a message

#### Route: /symptom/report
#### Method: POST
#### Body example
```js
{
  "document": "12345678",
  "type": "DNI",
  "result": [
    true,
    false,
    true,
    true,
    false,
    true,
    true,
    false,
    true,
    true,
    true,
    true,
    false]
}
```
### Response
```js
{
    "message": "Llame a los servicios para realizar detección para SARS-COV2 (COVID 19)",
    "score": 15
}  
```


