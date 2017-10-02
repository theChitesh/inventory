# inventory management system

The Application is build with Spring Boot with JWT token based authentication and runtime Derby DB.


The application works for two roles. 
1) sys-admin : Users with this role are allowed to perform all GET and POST operations on Stock Resource
sys-admin token :- eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik9EY3hPVEJDT1VWR09UVXhPREpETmpreU9VRkdRelZHUlVVNU0wRkZPRU15T0RFMk1qZ3hPQSJ9.eyJ1c2VybmFtZSI6IkludmVudG9yeSBVc2VyIiwidXNlcmlkIjoidXNyXzEiLCJyb2xlIjoic3lzLWFkbWluIn0.CARRuAKNP7yO2vf9VHAn39UadP89P1i0CbQn1mv6unUcCYTDUC80o61PaDtigX31A1lTVoIdLCZEI4mDkniHUwkoCXUa11Bzoljv-QRh5ApOaHYJngSQyvS_807is3Lht8fButTQA6W7kRLlaJNwKeUITPfDKdCXFlXi11L-IcoC0pjgPF2NOw6xsaat35CM7I7BRaOOWLZLurAB1RjXoFC_YWH05MgMZ2KXGx_gXsu2jq7inObY3NniQsAeP9GT4yTMmQxXbtDA9QbQ4dcZOXvrHIr7M0b_QkOWxsfQfY0vM08noKA7rTn3oomMa_-NPfq4hmIQbvjU0ni3tSv_zg


2) user : User with this role are only allowed to perform GET operations.
user token :- eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik9EY3hPVEJDT1VWR09UVXhPREpETmpreU9VRkdRelZHUlVVNU0wRkZPRU15T0RFMk1qZ3hPQSJ9.eyJ1c2VybmFtZSI6IkludmVudG9yeSBVc2VyIiwidXNlcmlkIjoidXNyXzEiLCJyb2xlIjoidXNlciJ9.NrRyQVW8XYyhXz6lCgakVk8qQckSv_mDuqNmWAUOiW36yJ1uOCgUfEF2K8t3QTNIBolZ4vxuzhTlGlW7XV0niDbC9zX4D-4E07m4RdVh-rOYt6mkSbx6Yx3oTZ92gh7vcF3qBQI5IpeoaCgsQ7ta2ixmluZ49D1odkEv4qS4RBTHdrDlLs2c0ahMmZQHygMqQeFaxnhQr8BBORWbilc84GqEAnkN3SkPzhgqju9Ny1vdRCde7tXAE6bJEuIB6zWAPEpsbKfo2t9z5wrYQRwfvJWDqiRrOT5pqWOnndi2ChhTGdf5kC8ZsKNuOGdMPcs5zeMJOC1hSzZD0hkkv7zkkg


To access the application add these token in Authorization header before making a request.

The application works with two assumption.
1) Promotion on a stock is applicable when the qunatity of stock is less than 100 Units and number of stocks in Inventory is more than 10 days.

2) Oder additonal stock advise is set, when the qunatity of stock in Inventory is less than 100 Units.

GET/POST :- http://localhost:9191/inventory/stocks

Stock Resource
 {
        "id": 1,
        "name": "First Item",
        "quantity": 100000,
        "activatePromotion": false,
        "entryDate": "30-09-2017",
        "lastUpdateDate": "2017-10-01",
        "updatedBy": "Admin User",
        "amountPerDay": 5,
        "orderAdditionalStock": false,
        "daysInInventory": 1,
        "inventoryCost": 5
   }



Note : The entry date of the stock is intentional allowed to edit by sys-admin user, so that the feature of the application can be explored.
With every start application clears the database. And perviously added stock will not appear.

