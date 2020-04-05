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
Check http status

* Use this route to get the list of user. It just returns the ubigeos with cases >= 1.

#### Route: /map/data
#### Method: POST
####
#### Body example
```js
{
	"from": 1485339334175,
	"to": 1885339334975
}
```

#### Header:
```js
"Bearer <token>"
```

#### Response
```js
{
    "recovered": [
        {
            "ubigeo": "050608",
            "casos": 1
        },
        {
            "ubigeo": "010306",
            "casos": 1
        },
        {
            "ubigeo": "160201",
            "casos": 1
        },
        {
            "ubigeo": "200406",
            "casos": 1
        },
        {
            "ubigeo": "080905",
            "casos": 1
        },
        {
            "ubigeo": "170301",
            "casos": 1
        },
        {
            "ubigeo": "250104",
            "casos": 1
        },
        {
            "ubigeo": "130810",
            "casos": 1
        },
        {
            "ubigeo": "060203",
            "casos": 1
        },
        {
            "ubigeo": "220503",
            "casos": 1
        },
        {
            "ubigeo": "160504",
            "casos": 1
        },
        {
            "ubigeo": "200302",
            "casos": 1
        },
        {
            "ubigeo": "200401",
            "casos": 1
        },
        {
            "ubigeo": "160103",
            "casos": 1
        },
        {
            "ubigeo": "010403",
            "casos": 1
        }
    ],
    "neutral": [
        {
            "ubigeo": "050705",
            "casos": 1
        },
        {
            "ubigeo": "050601",
            "casos": 1
        },
        {
            "ubigeo": "080803",
            "casos": 1
        },
        {
            "ubigeo": "110104",
            "casos": 1
        },
        {
            "ubigeo": "060801",
            "casos": 1
        },
        {
            "ubigeo": "160201",
            "casos": 1
        },
        {
            "ubigeo": "110101",
            "casos": 1
        },
        {
            "ubigeo": "050301",
            "casos": 2
        },
        {
            "ubigeo": "051011",
            "casos": 1
        },
        {
            "ubigeo": "151014",
            "casos": 1
        },
        {
            "ubigeo": "250201",
            "casos": 1
        },
        {
            "ubigeo": "120804",
            "casos": 1
        },
        {
            "ubigeo": "080902",
            "casos": 1
        },
        {
            "ubigeo": "170101",
            "casos": 1
        },
        {
            "ubigeo": "250401",
            "casos": 2
        },
        {
            "ubigeo": "221002",
            "casos": 1
        },
        {
            "ubigeo": "130401",
            "casos": 1
        },
        {
            "ubigeo": "140107",
            "casos": 1
        },
        {
            "ubigeo": "220603",
            "casos": 2
        },
        {
            "ubigeo": "140307",
            "casos": 1
        },
        {
            "ubigeo": "160511",
            "casos": 1
        },
        {
            "ubigeo": "160702",
            "casos": 1
        },
        {
            "ubigeo": "200114",
            "casos": 1
        },
        {
            "ubigeo": "160103",
            "casos": 1
        }
    ],
    "confirmed": [
        {
            "ubigeo": "090602",
            "casos": 1
        },
        {
            "ubigeo": "090607",
            "casos": 1
        },
        {
            "ubigeo": "090507",
            "casos": 1
        },
        {
            "ubigeo": "150807",
            "casos": 1
        },
        {
            "ubigeo": "250301",
            "casos": 1
        },
        {
            "ubigeo": "130807",
            "casos": 1
        },
        {
            "ubigeo": "130614",
            "casos": 1
        },
        {
            "ubigeo": "250101",
            "casos": 1
        },
        {
            "ubigeo": "200404",
            "casos": 1
        },
        {
            "ubigeo": "200114",
            "casos": 1
        },
        {
            "ubigeo": "010402",
            "casos": 1
        }
    ]
}
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
  "publicityId" : "464a4v7vcq6s"
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
  "publicityId" : "464a4v7vcq6s"
}
```
#### Response
Check HTTP status


* Use this route to report a message to a region (department, province or district)

#### Route: /map/notify/{ubigeo}
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

* Use this route to report a message to array of ubigeos

#### Route: /map/notify-region
#### Method: POST
#### Header:
```js
"Bearer <token>"
```
#### Body example
```js
{
	"message": "Cuidado!",
	"ubigeos": [
		"120206",
		"150104",
		"150130",
		"010101"
	]
}
```
#### Response
Check HTTP status



* Use this route to get the polygon that represents a region (department,
  province or district)

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


* Use this route to generate "N" aprox. fake users

#### Route: /test/gen/{number of users}
#### Method: GET
#### Response
Check HTTP status


* Use this route to delete all app users

#### Route: /test/gen/deleteall
#### Method: GET
#### Response
Check HTTP status

* Use this route to get stadistics

### Route: /map/stadistics
### Method: GET
### Response

Format "[day, year, month, ubigeo]: [neutral, confirmed, recovered]"

```js
{
    "[4, 4, 2020, 150708]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 081103]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 140204]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 040811]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 010601]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 060409]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 100403]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 160506]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 160605]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 160701]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 160403]": [
        2,
        0,
        0
    ],
    "[4, 4, 2020, 160604]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 030604]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 160102]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 200607]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 160105]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 160501]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 160302]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 200605]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 050611]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 200105]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 250203]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 250302]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 200401]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 250101]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 040505]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 250102]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 090709]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 250201]": [
        0,
        1,
        0
    ],
    "[4, 4, 2020, 250202]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 221002]": [
        0,
        1,
        0
    ],
    "[4, 4, 2020, 080306]": [
        0,
        1,
        0
    ],
    "[4, 4, 2020, 110101]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 080902]": [
        1,
        0,
        1
    ],
    "[4, 4, 2020, 120608]": [
        0,
        1,
        0
    ],
    "[4, 4, 2020, 170101]": [
        0,
        1,
        0
    ],
    "[4, 4, 2020, 190113]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 130206]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 050705]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 130303]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 080707]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 110114]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 220205]": [
        1,
        0,
        0
    ],
    "[4, 4, 2020, 220602]": [
        0,
        0,
        1
    ],
    "[4, 4, 2020, 220603]": [
        1,
        0,
        0
    ]
}
```
