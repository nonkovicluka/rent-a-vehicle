# Rent a Vehicle Web App

Rent A Vehicle is a small web application for renting vehicles and managing renting agencies.

## Getting Started

1. Clone or download this repository to your local computer
2. Navigate to _~/RentAVehicle/src/main/resources/application.properties_ and enter your database username and password  
&nbsp;&nbsp;&nbsp;Example:    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`spring.datasource.username=rick`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`spring.datasource.password=sanchez`


#### Running

1. Open your IDE and import _rent-a-vehicle_ project
2. Go to _~/RentAVehicle/src/main/java/com/rentavehicle/_ &nbsp;package and right click **_RentAVehicleApplication.java_**,
 in **Eclipse** click _Run as_, and choose _Java Application_, and in **Intellij** click _Run 'RentAVehicle...'_  


## Features

* Three type of users roles (registration available for **_Manager_** and **_User_**)  
&nbsp;&nbsp;&nbsp;**Admin** (username: **_user1_**, password: **_pass_**)  
&nbsp;&nbsp;&nbsp;**Manager** (username: **_user2_**, password: **_pass_**)  
&nbsp;&nbsp;&nbsp;**User** (username: **_user3_**, password: **_pass_**)  

##### Unregistered user

An unregistered user can browse agencies, search vehicles, register etc., but does not have access to
other features.

##### User Features

After user accounts are approved by admin, users can make vehicle reservations, 
and rate certain agency only after their registration is completed.

##### Manager Features
Manager accounts also need to be approved first by admin, after they can create their own renting agencies, 
branches, vehicles, price lists, edit vehicles and price per hour, and monitor reservations.  

##### Admin Features
Admin has all features as previous users, but also receives a web-socket message in console when a new user is registered. 
On 'Users' page, admin can manage newly registered users by approving or banning them.