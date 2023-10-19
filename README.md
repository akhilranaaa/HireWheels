# HireWheels

Simple monolithic Backend API for a car rental application developed in Spring Boot 🕶️☕

Features of the Project 🔌

  Types of users 👩‍🦲
    
    Each user registered on the application can have either of the following two roles:

      USER:
        Users with this role are regular customers who wish to book a vehicle from the application.
      
      ADMIN: 
        Users with this role serve as administrators for the application.
        Admins can add new vehicles to the application.
        Admins can change the availability of registered vehicles.

  Login 👨‍🦲

    This feature would allow users to log in to the application to continue ahead with vehicle booking.
  
  Signup 👨‍🦲

    This feature would allow users to register themselves on the web application. 
    Users must register themselves on the application if they wish to book vehicles.

  Vehicle Booking 🏎️

    This feature would allow users to book vehicles according to their requirements.
  
  Vehicle Registration 🏎️

    This feature would allow users with the ‘ADMIN’ role to add vehicles to the portal.
    While registering vehicles, the Admin maps each vehicle to a location. 
    This means that a particular vehicle will only be available for a pickup location.
  
Change Vehicle Availability 🏎️

    This feature would allow users with the ‘ADMIN’ role to change the availability of vehicles.
    For example, the Admin can make a registered vehicle unavailable for booking.
    
Note: Not all features are implemented yet 😁

Project setup

To run this project and test it locally:

    Pre-requisites
      JDK 17 or higher
      An IDE of choice
      MySQL8 (workbench to make things easier)

    Steps
      Clone the repository
      Open the project in an IDE
      Navigate to - src/main/java/org.ncu.hirewheels/HireWheelsApplication and RUN it.
      The application should be running on port 8085. You can change it if you like.


Endpoints

Currently, the following endpoints are functional

GET

hirewheels/api/v1/admin/base
      
<img width="1351" alt="screenshot" src="https://github.com/SwaggyXO/HireWheels/assets/76209941/d6dcbea7-4dcb-4fb5-866c-088e6b962d32">

hirewheels/api/v1/vehicle/all

<img width="1351" alt="screenshot" src="https://github.com/SwaggyXO/HireWheels/assets/76209941/3e62a8c0-b2cb-43cb-aec2-21162de1df5d">

POST

hirewheels/api/v1/admin/vehicles/create

<img width="1351" alt="screenshot" src="https://github.com/SwaggyXO/HireWheels/assets/76209941/203e830a-cecd-4bd2-bfa0-6b3130a806b6">

hirewheels/api/v1/booking/

<img width="1351" alt="screenshot" src="https://github.com/SwaggyXO/HireWheels/assets/76209941/2168dc9b-b32e-4df2-8a3a-98521d5c1943">

PATCH

hirewheels/api/v1/admin/vehicles/4

<img width="1351" alt="screenshot" src="https://github.com/SwaggyXO/HireWheels/assets/76209941/60bead90-1b28-4006-bab9-25b37e8977de">

Thanks for reading ❤️
