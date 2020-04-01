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


* Use this data to report a message to a region (department, province or district)

#### Route: /map/notify{ubigeo}
#### Method: POST
#### Header:
```js
"Bearer <token>"
```
#### Body example
```js
{
	"message": "Hola mundo"
}
```
#### Response
Check HTTP status


#### Route: /map/polygon/{ubigeo}
#### Method: GET
#### Header:
```js
"Bearer <token>"
```
#### Body example
```js
{
    "points": [
        {
            "x": -76.98795461122783,
            "y": -11.897146104712366,
            "type": "Point",
            "coordinates": [
                -76.98795461122783,
                -11.897146104712366
            ]
        },
        {
            "x": -77.02828123626566,
            "y": -11.96067772977152,
            "type": "Point",
            "coordinates": [
                -77.02828123626566,
                -11.96067772977152
            ]
        },
        {
            "x": -77.0598203612952,
            "y": -11.97691997978664,
            "type": "Point",
            "coordinates": [
                -77.0598203612952,
                -11.97691997978664
            ]
        },
        {
            "x": -77.07348323630801,
            "y": -11.918367729732115,
            "type": "Point",
            "coordinates": [
                -77.07348323630801,
                -11.918367729732115
            ]
        },
        {
            "x": -77.05405986128982,
            "y": -11.889839979705542,
            "type": "Point",
            "coordinates": [
                -77.05405986128982,
                -11.889839979705542
            ]
        },
        {
            "x": -77.025481986263,
            "y": -11.909277979723665,
            "type": "Point",
            "coordinates": [
                -77.025481986263,
                -11.909277979723665
            ]
        },
        {
            "x": -76.98795461122783,
            "y": -11.897146104712366,
            "type": "Point",
            "coordinates": [
                -76.98795461122783,
                -11.897146104712366
            ]
        }
    ],
    "coordinates": [
        {
            "type": "LineString",
            "coordinates": [
                {
                    "x": -76.98795461122783,
                    "y": -11.897146104712366,
                    "type": "Point",
                    "coordinates": [
                        -76.98795461122783,
                        -11.897146104712366
                    ]
                },
                {
                    "x": -77.02828123626566,
                    "y": -11.96067772977152,
                    "type": "Point",
                    "coordinates": [
                        -77.02828123626566,
                        -11.96067772977152
                    ]
                },
                {
                    "x": -77.0598203612952,
                    "y": -11.97691997978664,
                    "type": "Point",
                    "coordinates": [
                        -77.0598203612952,
                        -11.97691997978664
                    ]
                },
                {
                    "x": -77.07348323630801,
                    "y": -11.918367729732115,
                    "type": "Point",
                    "coordinates": [
                        -77.07348323630801,
                        -11.918367729732115
                    ]
                },
                {
                    "x": -77.05405986128982,
                    "y": -11.889839979705542,
                    "type": "Point",
                    "coordinates": [
                        -77.05405986128982,
                        -11.889839979705542
                    ]
                },
                {
                    "x": -77.025481986263,
                    "y": -11.909277979723665,
                    "type": "Point",
                    "coordinates": [
                        -77.025481986263,
                        -11.909277979723665
                    ]
                },
                {
                    "x": -76.98795461122783,
                    "y": -11.897146104712366,
                    "type": "Point",
                    "coordinates": [
                        -76.98795461122783,
                        -11.897146104712366
                    ]
                }
            ]
        }
    ],
    "type": "Polygon"
}
```
#### Response
Check HTTP status


* Use this data to generate "N" aprox. fake users

#### Route: /test/gen/{number of users}
#### Method: GET
#### Response
Check HTTP status


* Use this data to delete all app users

#### Route: /test/gen/deleteall
#### Method: GET
#### Response
Check HTTP status
