#  Routes Web

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
#### Method: POST
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
        "departamento": "Lima",
        "provincia": "Huaras",
        "distrito": "Comas",
        "casos": 2
    },
    {
        "departamento": "Lima",
        "provincia": "Lima",
        "distrito": "Comas",
        "casos": 1
    },
    {
        "departamento": "Lima",
        "provincia": "Lima",
        "distrito": "Barranco",
        "casos": 1
    }
]
```

* Use this data to report a confirmed case (the server will send the
  notifications to the required people)

#### Route: /notification/report-case
#### Method: POST
#### Header:
```js
"Bearer <token>"
```
#### Body example
```js
{
	"document": "12345678",
	"type": "DNI",
}
```
#### Response
Check HTTP status

* Use this data to report a recovered case

#### Route: /notification/report-recover
#### Method: POST
#### Body example
```js
{
	"document": "12345678",
	"type": "DNI",
}
```
#### Response
Check HTTP status


* Use this data to report a message to a departamento

#### Route: /notification/departamento
#### Method: POST
#### Header:
```js
"Bearer <token>"
```
#### Body example
```js
{
	"departamento": "Lima",
	"message": "Hola mundo"
}
```
#### Response
Check HTTP status


* Use this data to report a message to a provincia

#### Route: /notification/provincia
#### Method: POST
#### Header:
```js
"Bearer <token>"
```
#### Body example
```js
{
	"departamento": "Lima",
	"provincia": "Lima",
	"message": "Hola mundo"
}
```
#### Response
Check HTTP status


* Use this data to report a message to a distrito

#### Route: /notification/distrito
#### Method: POST
#### Header:
```js
"Bearer <token>"
```
#### Body example
```js
{
	"departamento": "Lima",
	"provincia": "Lima",
	"distrito": "Comas",
	"message": "Hola mundo"
}
```
#### Response
Check HTTP status
