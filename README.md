![logo-color 7](https://github.com/jtiradohernandez/ironhack-final-project/assets/23397511/a8b41e8a-deb1-443a-bea9-722a1291760b)


# Project | Nomai Timeless Resorts


## UML diagram
![Captura de pantalla 2024-04-16 a las 16 52 59](https://github.com/jtiradohernandez/ironhack-final-project/assets/23397511/cc57ba63-8a90-4bb9-aeed-1344dcdcbda9)

##    Setup

## Technologies Used
- Java
- SpringBoot
- MySQL

##    Controllers and Routes structure
| URL                                                                        | Method | Action                                                       | Role required   |
|----------------------------------------------------------------------------|--------|--------------------------------------------------------------|-----------------|
| api/login?username={username}&password={password}                          | GET    | Log in                                                       | ADMIN           |
| api/hotel                                                                  | GET    | Get all Hotels                                               | ADMIN, EMPLOYEE |
| api/hotel/{id}                                                             | GET    | Get Hotel by ID                                              | ADMIN, EMPLOYEE |
| api/hotel                                                                  | POST   | Create one or more Hotels                                    | ADMIN           |
| api/hotel                                                                  | DELETE | Delete one or more Hotels                                    | ADMIN           |
| api/hotel                                                                  | PATCH  | Edit one or more Hotels                                      | ADMIN           |
| api/hotel/employees                                                        | GET    | Get all Employees                                            | ADMIN, EMPLOYEE |
| api/hotel/employees/{id}                                                   | GET    | Get Employee by ID                                           | ADMIN, EMPLOYEE |
| api/hotel/employees                                                        | POST   | Create a new Employee                                        | ADMIN           |
| api/hotel/employees                                                        | PATCH  | Update an Employee                                           | ADMIN           |
| api/hotel/employees/{id}                                                   | DELETE | Delete an employee                                           | ADMIN           |
| api/hotel/{id}/employees                                                   | GET    | Get all Employees related to an Hotel                        | ADMIN, EMPLOYEE |
| api/hotel/employees/role                                                   | POST   | Set a Role to an Employee                                    | ADMIN           |
| api/hotel/employees/password/{id}/{password}                               | PATCH  | Update the password of an Employee                           | ADMIN, EMPLOYEE |
| api/hotel/clients                                                          | GET    | Get all Clients                                              | ADMIN, EMPLOYEE |
| api/hotel/clients/{id}                                                     | GET    | Get Client by ID                                             | ADMIN, EMPLOYEE |
| api/hotel/clients                                                          | POST   | Create a new Client                                          | ADMIN, EMPLOYEE |
| api/hotel/clients                                                          | PATCH  | Update a Client                                              | ADMIN, EMPLOYEE |
| api/hotel/facilities                                                       | GET    | Get all Facilities                                           | ADMIN, EMPLOYEE |
| api/hotel/facilities/{id}                                                  | GET    | Get Facility by ID                                           | ADMIN, EMPLOYEE |
| api/hotel/{id}/facilities                                                  | GET    | Get all Facilities of an Hotel                               | ADMIN, EMPLOYEE |
| api/hotel/facilities                                                       | POST   | Create one or more Facilities                                | ADMIN           |
| api/hotel/facilities                                                       | PATCH  | Update one or more Facilities                                | ADMIN           |
| api/hotel/facilities                                                       | DELETE | Delete one or more Facilities                                | ADMIN           |
| api/hotel/facilities/bookings                                              | GET    | Get all Bookings of Facilities                               | ADMIN, EMPLOYEE |
| api/hotel/facilities/bookings/{id}                                         | GET    | Get a Booking of a Facility by ID                            | ADMIN, EMPLOYEE |
| api/hotel/{id}/facilities/bookings                                         | GET    | Get all Bookings of Facilities of an Hotel                   | ADMIN, EMPLOYEE |
| api/hotel/facilities/bookings                                              | POST   | Create a Booking of a Facility                               | ADMIN, EMPLOYEE |
| api/hotel/facilities/bookings                                              | PATCH  | Update a Booking of a Facility                               | ADMIN, EMPLOYEE |
| api/hotel/facilities/bookings                                              | DELETE | Delete a Booking of a Facility                               | ADMIN, EMPLOYEE |
| api/hotel/bedrooms                                                         | GET    | Get all Bedrooms                                             | ADMIN, EMPLOYEE |
| api/hotel/bedrooms/{id}                                                    | GET    | Get Bedroom by ID                                            | ADMIN, EMPLOYEE |
| api/hotel/{id}/bedrooms                                                    | GET    | Get all Bedrooms of an Hotel                                 | ADMIN, EMPLOYEE |
| api/hotel/bedrooms                                                         | POST   | Create one or more Bedrooms                                  | ADMIN           |
| api/hotel/bedrooms                                                         | PATCH  | Update one or more Bedrooms                                  | ADMIN           |
| api/hotel/bedrooms                                                         | DELETE | Delete one or more Bedrooms                                  | ADMIN           |
| api/hotel/bedrooms/bookings                                                | GET    | Get all Bookings of Bedrooms                                 | ADMIN, EMPLOYEE |
| api/hotel/bedrooms/bookings/{id}                                           | GET    | Get a Booking of a Bedroom by ID                             | ADMIN, EMPLOYEE |
| api/hotel/{id}/bedrooms/bookings                                           | GET    | Get all Bookings of Bedrooms of an Hotel                     | ADMIN, EMPLOYEE |
| api/hotel/:id/bedrooms/bookings/availability/{arrivalDate}/{departureDate} | GET    | Get available Bedrooms between an arrival and departure date | ADMIN, EMPLOYEE |
| api/hotel/bedrooms/bookings                                                | POST   | Create a Booking of a Bedroom                                | ADMIN, EMPLOYEE |
| api/hotel/bedrooms/bookings                                                | PATCH  | Update a Booking of a Bedroom                                | ADMIN, EMPLOYEE |
| api/hotel/bedrooms/bookings                                                | DELETE | Delete a Booking of a Bedroom                                | ADMIN, EMPLOYEE |




##    Extra links
- [Trello board](https://trello.com/b/mpwpbS6r)
- [Lucidchart: UML Diagram](https://lucid.app/lucidchart/c7168214-eee8-4411-90d7-12470c8a2333/edit?viewport_loc=-2888%2C-2868%2C5244%2C4756%2C0_0&invitationId=inv_a5fb7a48-20f5-4536-a249-e4eb6c6ed326) 
- [Canva: Presentation Slides](https://www.canva.com/design/DAGE6rpwNNo/wu43thNGxL4GRG87qd5iOw/edit?utm_content=DAGE6rpwNNo&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
##    Future Work


##    Team members
Javier Tirado Hernández




