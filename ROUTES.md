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
