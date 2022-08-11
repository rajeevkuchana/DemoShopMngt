# SHOPs Management Demo
 
## Overview  
Its a Shop Management Application where the shop details are stored in the in-memory database and also calculate the nearest shop with respect to an user location.
Technnologies used are Java 11, Swagger 2.0 for API design and API testing using Swagger UI , H2 an open-source in-memory Database. Junit for unit testing(Mockito)

## REST APIs
Its a Spring Boot Java based microservice which implements functionalitiies 
 1) POST/ Shop : Which takes Shop Name(unique), Number and Postcode. Once the post request is submitted, the Postcode is used to get the latitude and longitude and save all the details. The responce to this API is either "Added" when the User input a Unquie Shop Name or "Update" when the User input an already existing Shop Name 
 2) GET/ Shop : Which takes the latitude and logitude as input and find the nearest Shop and outputs the same to the user.
 
 ## Process of Calulating latitude and Logitude.
 
In the POST/ shop REST API, when the user input postcode along with the other detials, this postcode is used to get latitude and longitude from google map API, which requies a googlemap account should be created and an API key is provided to this account.
Example Google Map URL: https://maps.googleapis.com/maps/api/geocode/json?address=122018&key=AIzaSyCQDOE_hc1-ka9v850dqdm-f_RMoowPZ2A

Once the latitude and logotude of the shop are available in the in-memroy DB, using the below query the nearest shop for a given latitude and logitude can be calculated and return to the user
Example Query: SELECT id , (   3959 *   acos(cos(radians(19.734858)) *    cos(radians(latitude)) *    cos(radians(longitude) -    radians(73.113280)) +    sin(radians(19.734858)) *   sin(radians(latitude )))) AS distance FROM SHOP ORDER BY distance LIMIT  1


URL :http://localhost:8080/  

Change default port value in application.properties
