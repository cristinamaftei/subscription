# How to run
1. cd into the subscription checkout location
2. run mvn clean install
3. run java -jar target/subscription-0.0.1-SNAPSHOT.jar


# Endpoints
## Create account
POST localhost:8080/registration/create
Header: Content-type: application/json
Body: 
```json
{
    "firstName": "First",
    "lastName": "Customer",
    "email": "first@customer.ro"
}
```

## Verify account
GET localhost:8080/registration?email=first@customer.ro

