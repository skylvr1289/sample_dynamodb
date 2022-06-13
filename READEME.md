## start the local dynamoDB using docker-compose  
``` docker-compose up -d```  
## Start the springboot application
## Use below api's
GET ```localhost:8001/```  
GET ```localhost:8001/initialize``` --> to create model tables  
GET ```localhost:8001/employees ```  
PUT ```localhost:8001/employees  ```  

``` 
{
    "employeeId":"1380661f-de81-485b-a4ab-8110bba8c8fa",
    "firstName":"Abhishek",
    "lastName": "Kumar",
    "email": "abhkumar@guidewire.com",
    "age": 31,
    "address":{
      "pincode":"560037",
      "state": "KA",
      "district": "Bangalore",
      "street": "Marathalli"
    }
}
```



