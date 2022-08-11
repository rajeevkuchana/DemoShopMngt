# SHOPs Management Demo
 
## Overview  

Its a Spring Boot Java based microservice which implements functionalitiies 
 1) POST/ Shop : Which takes Shop Name(unique), Number and Postcode. Once the post request is submitted, the Postcode is used to get the latitude and longitude and save all the details. The responce to this API is either "Added" when the User input a Unquie Shop Name or "Update" when the User input an already existing Shop Name 
 2) GET/ Shop : Which takes the latitude and logitude as input and find the nearest Shop and outputs the same to the user.
 

URL :http://localhost:8080/  

Change default port value in application.properties
