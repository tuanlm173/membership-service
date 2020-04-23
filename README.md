# Membership Reward Service

## Getting Started

1. Create database (Using Postgres)

On command line  `createdb membership`, then create tables and insert values using sql.txt; source data in data.xlsx

Check out the project

`cd membership-service`

Build the project

`./mvnw package`

Run the project 

`./mvnw spring-boot:run`

## Update config and save to database
##### Update config
```
PUT localhost:8082/api/test/config/1?sales=100000
```
##### Response

```json
{
    "data": 1,
    "extra": {
        "updatedTime": "2020-04-23T22:01:17Z"
    }
}
```
##### Check updated config
```
GET localhost:8082/api/test/config?id=1
```
##### Response

```json
{
    "data": {
        "id": 1,
        "sales": 100000,
        "points": 1
    },
    "extra": {
        "updatedTime": "2020-04-23T22:01:19Z"
    }
}
```

## Get card type information
```
GET localhost:8082/api/test/cardtype?id=2
```
##### Response
```json
{
    "data": {
        "id": 2,
        "name": "the_bac",
        "spentThreshold": 5000000,
        "duration": 365,
        "discountPercent": 2,
        "createdOn": "2019-12-18",
        "modifiedOn": "2019-12-18"
    },
    "extra": {
        "updatedTime": "2020-04-23T22:01:21Z"
    }
}
```

## Get loyalty card information
```
GET localhost:8082/api/test/loyaltycard?id=1
```
##### Response
```json
{
    "data": {
        "id": 1,
        "code": "LT0001",
        "phone": "0987654321",
        "point": 23,
        "totalSpent": 2345000,
        "startDate": "2019-12-18",
        "endDate": "2020-12-18",
        "createdOn": "2019-12-18",
        "modifiedOn": "2019-12-18",
        "cardType": {
            "id": 1,
            "name": "the_chuan",
            "spentThreshold": 0,
            "duration": 365,
            "discountPercent": 1,
            "createdOn": "2019-12-18",
            "modifiedOn": "2019-12-18"
        }
    },
    "extra": {
        "updatedTime": "2020-04-23T22:28:40Z"
    }
}
```
## Update transaction and update card type if necessary
##### Update transaction, calculate points and update card type
```
POST localhost:8082/api/test/addtransaction?id=1&loyaltyCardId=1&spentAdjust=3040000
```
##### Response
```json
{
    "data": 1,
    "extra": {
        "updatedTime": "2020-04-23T22:29:20Z"
    }
}
```

##### Check new transaction
```
GET localhost:8082/api/test/transaction?id=1
```
##### Response
```json
{
    "data": {
        "id": 1,
        "pointAdjust": 30,
        "spentAdjust": 3040000,
        "createdOn": "2020-04-23",
        "loyaltyCard": {
            "id": 1,
            "code": "LT0001",
            "phone": "0987654321",
            "point": 53,
            "totalSpent": 5385000,
            "startDate": "2019-12-18",
            "endDate": "2020-12-18",
            "createdOn": "2019-12-18",
            "modifiedOn": "2020-04-23",
            "cardType": {
                "id": 2,
                "name": "the_bac",
                "spentThreshold": 5000000,
                "duration": 365,
                "discountPercent": 2,
                "createdOn": "2019-12-18",
                "modifiedOn": "2019-12-18"
            }
        }
    },
    "extra": {
        "updatedTime": "2020-04-23T22:29:29Z"
    }
}
```
##### Check new loyalty card status
```
GET localhost:8082/api/test/loyaltycard?id=1
```
##### Response
```json
{
    "data": {
        "id": 1,
        "code": "LT0001",
        "phone": "0987654321",
        "point": 53,
        "totalSpent": 5385000,
        "startDate": "2019-12-18",
        "endDate": "2020-12-18",
        "createdOn": "2019-12-18",
        "modifiedOn": "2020-04-23",
        "cardType": {
            "id": 2,
            "name": "the_bac",
            "spentThreshold": 5000000,
            "duration": 365,
            "discountPercent": 2,
            "createdOn": "2019-12-18",
            "modifiedOn": "2019-12-18"
        }
    },
    "extra": {
        "updatedTime": "2020-04-23T22:29:49Z"
    }
}
```