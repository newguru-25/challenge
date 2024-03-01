# CHALLENGE

## DESCRIPTION
Simple project that contain `Spring-Boot`, `spring-security`, `JWT`, `Oauth2`, `full Crud Operation` using `Java 17`.

## Instalation
Step for installation:
```sh
# Clone this project from github
git clone https://github.com/newguru-25/challenge.git

# Clears the target directory and builds the project
mvn clean install
```

## Configuration
Step for configuration:
- Create new database in postgresql
- Set database name, user, and password in `application-properties`
- Create table `sec_user`
```sh 
    create table sec_user(
        id bigserial primary key,
        username varchar,
        email varchar,
        full_name varchar,
        password varchar,
        app_user_role varchar
    );
```

## Run Project
You can start this project using
```sh
mvn clean spring-boot:run
```

## Make Requests
Register ExchangeCurrency
```sh
curl --location 'localhost:8080/exchangecurrency/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGx1aXNAZ21haWwuY29tIiwiaXNzIjoiSGVsbG9BSSIsImlhdCI6MTcwOTMxNTAxMCwiZXhwIjoxNzA5NDAxNDEwfQ.UbvjxmqaNxPTkpgW216o0awd1c_Bk_iclOLaD_xzyShzuHmNiGCEWd5z_4ya44bDRSD_BuDskmjtf09YFTw6ZQ' \
--header 'Cookie: SESSION=1956c8a1-28eb-4fd9-a24b-4901a83dc11d' \
--data '{
    "amount":9.00,
    "exchangeType": 3.50,
    "currencyOrigin": "SOL",
    "currencyDestination": "DOLARES"
    
}'
```

Get ExchangeCurrency
```sh
curl --location 'localhost:8080/exchangecurrency/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGx1aXNAZ21haWwuY29tIiwiaXNzIjoiSGVsbG9BSSIsImlhdCI6MTcwOTMxNTAxMCwiZXhwIjoxNzA5NDAxNDEwfQ.UbvjxmqaNxPTkpgW216o0awd1c_Bk_iclOLaD_xzyShzuHmNiGCEWd5z_4ya44bDRSD_BuDskmjtf09YFTw6ZQ' \

```

Put ExchangeCurrency
```sh
curl --location --request PUT 'localhost:8080/exchangecurrency/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGx1aXNAZ21haWwuY29tIiwiaXNzIjoiSGVsbG9BSSIsImlhdCI6MTcwOTMxNTAxMCwiZXhwIjoxNzA5NDAxNDEwfQ.UbvjxmqaNxPTkpgW216o0awd1c_Bk_iclOLaD_xzyShzuHmNiGCEWd5z_4ya44bDRSD_BuDskmjtf09YFTw6ZQ' \
--header 'Content-Type: application/json' \
--data '{
"id":1,
"amount":9.00,
"exchangeType": 3.80,
"currencyOrigin": "SOL",
"currencyDestination": "DOLARES"

}'
```


Get Token ExchangeCurrency
```sh
curl --location 'localhost:8080/login' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cookie: SESSION=1956c8a1-28eb-4fd9-a24b-4901a83dc11d' \
--data-urlencode 'username=luis@gmail.com' \
--data-urlencode 'password=123456'
```




## Author
Created by Luis Mendoza Huaytalla.

