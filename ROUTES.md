# Routes

## Web

* Use this route to login

#### Route: /login/web
#### Method: POST
#### Body example
```js
{
	"username": "admin@cs",
	"password": "qwerty"
}
```
#### Response example
```js
{
    "userId": 1,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBjcyIsInJvbGUiOiJVU0VSX1dFQiIsImlzcyI6Imh0dHA6Ly9kZXZnbGFuLmNvbSIsImlhdCI6MTU4NTA1MjU2MCwiZXhwIjoxNTg1MDcwNTYwfQ.gTJ2ovcGXKTjTQxBjZC7mVttBeQ4u4roEQKdRsyKYvk"
}
```

* Use this route to get the list of confirmed users

#### Route: /map/data
#### Method: GET
####
#### Body example
```js
{
	"state": "confirmed",
	"from": 1585339334175,
	"to": 1585339334975
}
```

#### Header:
```js
"Bearer <token>"
```

#### Response
```js
[
  {
    "ubigeo": "ubigeo-code1",
    "cases:" 12
    "latitude": 12,
    "longitude": 14
  },
  {
    "ubigeo": "ubigeo-code2",
    "cases:" 100
    "latitude": 12,
    "longitude": 20
  }
]
```

* Use this route to notify a message to a list of users

#### Route: /notification/notify
#### Method: POST
#### Header:
```js
"Bearer <token>"
```

#### Body Example
```js
{
	"message": "Hola mundo",
	"userAppId": ["5e7a7f9ee3a7c2152101203c", "5e7a7f9ee3a7c2152101203d"]
}
```

#### Response
Check HTTP status

* Use this data to report a confirmed case (the server will send the
  notifications to the required people)

#### Route: /notification/report-case
#### Method: GET
#### Body example
```js
{
	"document": "12345678",
	"type": "DNI",
}
```
#### Response
Check HTTP status


## Mobile


## Mobile

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


