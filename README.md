# su18-202-code-linguists

Architectural Diagram:

![img_4365 4](https://user-images.githubusercontent.com/33049718/42916498-59f55612-8aba-11e8-80e9-3eca90a98e5f.jpg)


# Spring Boot REST API with Mongo DB

## Project Description
This is a java based application that is built levarging Spring Boot REST framework.
Build Tool Used: Gradle
NoSQL Database: Mongo DB for persisting data of application

Functionality: Following API's are part of this application:

- Add Card API: This API accepts Card Number and Card CVV and add cards in to Mongo DB Collection 'Cards'
- Payment API: This API creates payment for an order and persist data into Payment collection and deduct money from Card.
- Manage Order API: This API takes the items in order and adds item name with price and total cost into Order collection.
- Add Money API: This API adds money to Card.


## Configuration:

http://ec2containerinstance/addCard
http://ec2containerinstance/makePayment
http://ec2containerinstance/addOrder
http://ec2containerinstance/addMoney

### Add a Card:
POST /addCard
Accept: application/json
Content-Type: application/json

```
{
"cardNumber" : "xxx-xxx-xxx",
"cardCVV" : "Very basic, small rooms but clean",
"balance" : 100
}
```

RESPONSE: HTTP 201 (Created)
Location header: http://ec2containerinstance/addCard

### Make Payment
POST /makePayment
Accept: application/json
Content-Type: application/json

```
{
"cardID" : "e34-j799-9887b",
"amount" : "10"
}
```

RESPONSE: HTTP 201 (Created)
Location header: http://ec2containerinstance/makePayment

### Add order
POST /addOrder
Accept: application/json
Content-Type: application/json

```
{
	"coffee": "12",
	"tea": "32",
	"cookie": "54"
}
```

RESPONSE: HTTP 201 (Created)
Location header: http://ec2containerinstance/addOrder

### Add money
POST /addMoney
Accept: application/json
Content-Type: application/json

```
{
	"cardID" : "e79b5bd0-02e2-4ec6-a190-a0d4ee27c743",
	"amount" : 50
}
```

RESPONSE: HTTP 201 (Created)
Location header: http://ec2containerinstance/addMoney


## Steps to run:

### Pre-requirements:
jdk 1.8
Gradle

1. Download repo su18-202-code-linguists/OneStopCoffee/
2. Go in to downloaded folder
3. Use followimg commands to run:

```
$ gradle build
$ gradle shadowJar
$ java -cp build/libs/OneStopCoffee.jar OneStopCoffeeApplication.Main
```

Open Postman or browser to test APIs.(refer configurations for URI and payload)
