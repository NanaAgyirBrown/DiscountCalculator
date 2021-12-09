<h1 align="center">Discount Rule Biller - C#</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.0-blue.svg?cacheSeconds=2592000" />
  <a href="#" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg" />
  </a>
</p>

 ### Description

 >  On a retail website, the following discounts apply:
 >  1. If the user is an employee of the store, he gets a 30% discount
 >  2. If the user is an affiliate of the store, he gets a 10% discount
 >  3. If the user has been a customer for over 2 years, he gets a 5% discount.
 >  4. For every 100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45
 >     as a discount).
 >  5. The percentage based discounts do not apply on groceries.
 >  6. A user can get only one of the percentage based discounts on a bill.

***

 ### Usage
 The solution can be run:-
 > * As a docker image by downloading the latest image from the [Discount Biller - Docker Image](https://hub.docker.com/r/thinkai/billercalculator)
 > * Or by building the image from the Dockerfile included in this project

***

 ### API Documentation
 > * The solution is served in a browser with an Interactive Swagger OpenAPI documentation.
 > * The documentation can be accessed at **https://{your_baseurl}:{OPTIONAL- Port}/swagger-ui.html**

***
 
 ### Bill-Generator EndPoint
 > * To generate a bill or invoice, one can POST a JSON object to the /biller/shoppingcart.

 >  * Request JSON body 
 >  * Example
 
***
```json
 {
  "user": 1,
  "cartItem": [
    {
      "item": 1,
      "quantity": 10
    },
    {
      "item": 2,
      "quantity": 5
    },
    {
      "item": 3,
      "quantity": 3
    },
    {
      "item": 4,
      "quantity": 16
    },
    {
      "item": 6,
      "quantity": 5
    },
    {
      "item": 5,
      "quantity": 16
    }
  ]
 }
 ```
***
 > * Response Body

***
```json
 {
  "isSuccessful": true,
  "hasError": false,
  "hasException": false,
  "data": {
    "Cart Items": [
      {
        "quantity": 10,
        "totalCost": 1250,
        "id": 1,
        "itemName": "Television",
        "categoryId": 2,
        "unitPrice": 125
      },
      {
        "quantity": 5,
        "totalCost": 9225,
        "id": 2,
        "itemName": "Laptop",
        "categoryId": 2,
        "unitPrice": 1845
      },
      {
        "quantity": 3,
        "totalCost": 4.5,
        "id": 3,
        "itemName": "Apples",
        "categoryId": 1,
        "unitPrice": 1.5
      },
      {
        "quantity": 16,
        "totalCost": 28,
        "id": 4,
        "itemName": "Mangoes",
        "categoryId": 1,
        "unitPrice": 1.75
      },
      {
        "quantity": 5,
        "totalCost": 3.75,
        "id": 6,
        "itemName": "Sugar",
        "categoryId": 1,
        "unitPrice": 0.75
      },
      {
        "quantity": 16,
        "totalCost": 1360,
        "id": 5,
        "itemName": "Phone",
        "categoryId": 2,
        "unitPrice": 85
      }
    ],
    "Invoice #": "f78c6d53-a515-4dd5-ab82-d966386ebce2",
    "user": {
      "id": 1,
      "name": "Kofi Mensah",
      "userTypeID": 1,
      "membershipDate": "2017-04-21T00:00:00",
      "bills": []
    },
    "Discount Rule": {
      "id": "c3acc639-feaa-493d-8e5f-fdec14d1ba17",
      "userTypeId": 1,
      "discountTypeId": 2,
      "discountValue": 30,
      "ruleAppliesToId": 2
    },
    "totalAmount": 11871.25,
    "discountableAmount": 11835,
    "discountAmount": 3550.5,
    "amountPayable": 8320.75
  },
  "errorPayload": {
    "status": 0,
    "message": null
  }
 }
```
***

 ### UML, Test & Code Coverage Resources (Included)
 > * The solution includes a Test project that can be run with any IDE that can compile C#.
 > * Recommend IDE is visual studio 2019
 > * The test would also expose and generate a test and a code coverage report generated in xml.
 > 
 > * Included in this repo is the last test coverage and a UML image explaining key classes and relations
 > 
 > * UML -ML.png
 > * Coverage - Code_Coverage_report.coveragexml

***

## Author

👤 **Alfred Nana Brown**

* Twitter: [https://twitter.com/AgyirBrown](https://twitter.com/AgyirBrown)
* Github: [https://github.com/NanaAgyirBrown](https://github.com/NanaAgyirBrown)
* LinkedIn: [https://www.linkedin.com/in/alfred-nana-brown-a21b6a58/](https://www.linkedin.com/in/alfred-nana-brown-a21b6a58\/)

<h3 align="left">Languages and Tools:</h3>
<p align="left"> <a href="https://azure.microsoft.com/en-in/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/microsoft_azure/microsoft_azure-icon.svg" alt="azure" width="40" height="40"/> </a> <a href="https://www.w3schools.com/cs/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/csharp/csharp-original.svg" alt="csharp" width="40" height="40"/> </a> <a href="https://www.docker.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="40" height="40"/> </a> <a href="https://dotnet.microsoft.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/dot-net/dot-net-original-wordmark.svg" alt="dotnet" width="40" height="40"/> </a> <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a> <a href="https://kubernetes.io" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/kubernetes/kubernetes-icon.svg" alt="kubernetes" width="40" height="40"/> </a> <a href="https://www.linux.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/linux/linux-original.svg" alt="linux" width="40" height="40"/> </a> <a href="https://www.microsoft.com/en-us/sql-server" target="_blank" rel="noreferrer"> <img src="https://www.svgrepo.com/show/303229/microsoft-sql-server-logo.svg" alt="mssql" width="40" height="40"/> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="40" height="40"/> </a> </p>
