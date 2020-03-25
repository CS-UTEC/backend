# Routes

### Route: /login/web
### Method: POST
### Body example
```json
{
	"username": "admin@cs",
	"password": "qwerty"
}
```
### Response example
```json
{
    "userId": 1,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBjcyIsInJvbGUiOiJVU0VSX1dFQiIsImlzcyI6Imh0dHA6Ly9kZXZnbGFuLmNvbSIsImlhdCI6MTU4NTA1MjU2MCwiZXhwIjoxNTg1MDcwNTYwfQ.gTJ2ovcGXKTjTQxBjZC7mVttBeQ4u4roEQKdRsyKYvk"
}
```


### Route: /login/app
### Method: POST
### Body example
```json
{
	"document": "12345678",
	"type": "dni",
	"phone": "123456789"
}
```
### Response example
```json
{
    "userId": 4,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3OCIsInJvbGUiOiJVU0VSX0FQUCIsImlzcyI6Imh0dHA6Ly9kZXZnbGFuLmNvbSIsImlhdCI6MTU4NTA1NTU2NCwiZXhwIjoxNTg1MDczNTY0fQ.KSs0z2HlA-4S4tgnhHQh-9t7qdOgraKOOqovuyNV_go"
}
```


### Route: /ubication/:id
### Method: POST
### Header:
```
"Bearer <token>"
```
### Body example
```json
{
	"latitude": 12,
	"longitude": 13
}
```
### Response

Check HTTP status


### Route: /map/red-users
### Method: GET
### Header:
```json
"Bearer <token>"
```

### Response
```json
[
    {
        "timeStamp": "2020-03-24T16:12:41.557Z",
        "latitude": 12.0,
        "longitude": 13.0,
        "usetAppId": "5e7a7f9ee3a7c2152101203b""
    },
    {
        "timeStamp": "2020-03-24T17:18:24.202-05:00",
        "latitude": 40.2434,
        "longitude": 43.3243,
        "usetAppId": "5e7a7f9ee3a7c2152101203c"
    },
    {
        "timeStamp": "2020-03-24T17:18:24.212-05:00",
        "latitude": 35.32434,
        "longitude": 23.233,
        "usetAppId": "5e7a7f9ee3a7c2152101203d"
    }
]
```


### Route: /notification/notify
### Method: POST
### Header:
```json
"Bearer <token>"
```

### Response
```json
{
	"message": "Hola mundo",
	"userAppId": ["5e7a7f9ee3a7c2152101203c", "5e7a7f9ee3a7c2152101203d"]
}
```

### Route: /notification/get-all/:notification_id
### Method: GET
### Header:
```json
"Bearer <token>"
```

### Response
```json
[
    {
        "id": "5e7abefaf5367f37d141a77d",
        "timeStamp": "2020-03-25T02:16:26.123Z",
        "message": "Hola mundo",
        "checked": false,
        "user": {
            "id": "5e7a7f9ee3a7c2152101203c",
            "document": "11111111",
            "type": "dni",
            "phone": "12121212",
            "rol": null
        }
    },
    {
        "id": "5e7ac8b37dfe8a62abffda5f",
        "timeStamp": "2020-03-25T02:57:55.418Z",
        "message": "Chao mundo",
        "checked": false,
        "user": {
            "id": "5e7a7f9ee3a7c2152101203c",
            "document": "11111111",
            "type": "dni",
            "phone": "12121212",
            "rol": null
        }
    }
]
```

### Route: /notification/mark/:notification_id/:state
state = true or false
### Method: GET
### Header:
```json
"Bearer <token>"
```

### Response
Check HTTP status
