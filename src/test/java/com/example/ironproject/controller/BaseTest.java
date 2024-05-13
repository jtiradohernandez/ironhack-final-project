package com.example.ironproject.controller;

import com.example.ironproject.enumeration.Jobs;
import com.example.ironproject.model.Booking.BedroomBookings;
import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.enumeration.Service;
import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.model.People.Client;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.model.Security.Role;
import com.example.ironproject.repository.Booking.BedroomBookingsRepository;
import com.example.ironproject.repository.Booking.FacilityBookingRepository;
import com.example.ironproject.repository.HotelStructure.BedroomRepository;
import com.example.ironproject.repository.HotelStructure.FacilityRepository;
import com.example.ironproject.repository.HotelStructure.HotelRepository;
import com.example.ironproject.repository.People.ClientRepository;
import com.example.ironproject.repository.People.EmployeeRepository;
import com.example.ironproject.repository.Security.RoleRepository;
import com.example.ironproject.service.People.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected final ObjectMapper objectMapper = new ObjectMapper();
    protected String token;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected BedroomRepository bedroomRepository;
    @Autowired
    protected HotelRepository hotelRepository;
    @Autowired
    protected FacilityRepository facilityRepository;
    @Autowired
    protected ClientRepository clientRepository;
    @Autowired
    protected EmployeeRepository employeeRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected BedroomBookingsRepository bedroomBookingsRepository;
    @Autowired
    protected FacilityBookingRepository facilityBookingRepository;
    @Autowired
    protected RoleRepository roleRepository;

    protected Hotel hotel, hotel2, hotel3, hotel4, hotel5;
    protected int hotelId, hotelId1;
    protected Facility gym1, gym2, sauna, restaurant1, restaurant2;
    protected int facilityId,facilityId1;
    protected Bedroom bedroom1, bedroom2, bedroom3, bedroom4, bedroom5, bedroom6, bedroom7;
    protected int bedroomId, bedroomId1;
    protected Client client1, client2, client3, client4, client5;
    protected String clientId, clientId1;
    protected Employee employee1, employee2, employee3, employee4, employee5;
    protected String employeeId, employeeId1;
    protected static Role admin = new Role("ROLE_ADMIN");
    protected static Role employee = new Role("ROLE_EMPLOYEE");
    protected BedroomBookings bedroomBooking1, bedroomBooking2,bedroomBooking3,bedroomBooking4,bedroomBooking5;
    protected int bedroomBookingId, bedroomBookingId1;
    protected FacilityBooking facilityBooking1,facilityBooking2,facilityBooking3,facilityBooking4,facilityBooking5;
    protected int facilityBookingId, facilityBookingId1;

    Date date;


    @BeforeAll
    protected void initialSetUp() throws Exception {
        createTestingEmployees();
        token = login("daku","12345678");
    }

    protected String login(String username, String password) throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/login?username="+username+"&password="+password))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        return JsonPath.read(result.getResponse().getContentAsString(), "$.access_token");
    }

    protected void createTestingHotels(){
        hotel = new Hotel("Hotel Outer Wilds", "Santuario","Superficie","Hondonada Frágil",100);
        hotel2 = new Hotel("Wild Resort", "Estudio","Centro","Hondonada Frágil",100);
        hotel3 = new Hotel("Outer Hotels", "Estudio","Subterraneo","Lumbre",100);
        hotel4 = new Hotel("Twin Hotels", "Santuario","Nucleo","Espinoscuro",100);
        hotel5 = new Hotel("Nomai Resort", "Laboratorio","Subterraneo","Espinoscuro",100);
        hotelRepository.save(hotel);
        hotelRepository.save(hotel2);
        hotelRepository.save(hotel3);
        hotelRepository.save(hotel4);
        hotelRepository.save(hotel5);
    }

    protected void createTestingBedrooms(){
        createTestingHotels();
        bedroom1 = new Bedroom( hotel, 1, 2, 100 );
        bedroom2 = new Bedroom( hotel, 1, 2, 101 );
        bedroom3 = new Bedroom( hotel, 1, 2, 102 );
        bedroom4 = new Bedroom( hotel, 1, 3, 103 );
        bedroom5 = new Bedroom( hotel, 1, 4, 104 );
        bedroom6 = new Bedroom( hotel2, 1, 2, 100 );
        bedroom7 = new Bedroom( hotel2, 1, 4, 101 );
        bedroomRepository.save(bedroom1);
        bedroomRepository.save(bedroom2);
        bedroomRepository.save(bedroom3);
        bedroomRepository.save(bedroom4);
        bedroomRepository.save(bedroom5);
        bedroomRepository.save(bedroom6);
        bedroomRepository.save(bedroom7);
    }

    protected void createTestingFacilities(){
        createTestingHotels();
        gym1 = new Facility( hotel,"Gimnasio hondonada", -1, true, 150);
        gym2 = new Facility( hotel2, "Gimnasio nomai",4, true, 60);
        sauna = new Facility( hotel,"Sauna", -1, true, 6 );
        restaurant1 = new Facility( hotel,"Restaurante Hondonada", 5, true, 100 );
        restaurant2 = new Facility( hotel2,"Restaurante Nomai", 0, true, 200 );
        facilityRepository.save(gym1);
        facilityRepository.save(gym2);
        facilityRepository.save(sauna);
        facilityRepository.save(restaurant1);
        facilityRepository.save(restaurant2);
    }

    protected void createTestingClients(){
        date = new Date(90, 8, 2);
        client1 = new Client("1234","Esker",date, "Lumbre");
        client2 = new Client("1362","Riebeck",date, "Hondonada Frágil");
        client3 = new Client("7654","Feldespato",date, "Lumbre");
        client4 = new Client("6432","Gabbro",date, "Lumbre");
        client5 = new Client("6758","Chert",date, "Espinoscuro");
        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
        clientRepository.save(client4);
        clientRepository.save(client5);
    }

    protected void createTestingEmployees(){
        createRoles();
        date = new Date(90, 8, 2);
        employee1 = new Employee("1235","Solanum",date, Jobs.Reception,admin, "Solanum","12345678");
        employee2 = new Employee("48484848", "Xavi Tirado", date, Jobs.Reception, admin, "daku", "12345678");
        employee3 = new Employee("59595959", "Thais Real", date, Jobs.Cleaning, employee, "comolainfusion","12345678");
        employee4 = new Employee("9876","Ricard",date, Jobs.Cleaning,employee, "Ricard","12345678");
        employee5 = new Employee("6543","Victor",date, Jobs.Cleaning,employee, "Victor","12345678");
        userService.addEmployee(employee1);
        userService.addEmployee(employee1);
        userService.addEmployee(employee2);
        userService.addEmployee(employee3);
        userService.addEmployee(employee4);
        userService.addEmployee(employee5);
    }

    protected void createRoles(){
        roleRepository.save(admin);
        roleRepository.save(employee);
    }

    protected void createTestingFacilityBookings(){
        createTestingFacilities();
        createTestingEmployees();
        createTestingClients();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,00);
        Date date1 = calendar.getTime();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,00);
        Date date2 = calendar.getTime();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.DAY_OF_MONTH, 16);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,00);
        Date date3 = calendar.getTime();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,00);
        Date date4 = calendar.getTime();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,00);
        Date date5 = calendar.getTime();
        facilityBooking1 = new FacilityBooking(gym1,client1, Service.Gym, date1);
        facilityBooking2 = new FacilityBooking(gym1,client2, Service.Gym, date2);
        facilityBooking3 = new FacilityBooking(gym2,client3, Service.Gym, date3);
        facilityBooking4 = new FacilityBooking(sauna,client4, Service.Cleaning, date4);
        facilityBooking5 = new FacilityBooking(restaurant1,client5, Service.Cleaning, date5);
        facilityBooking1.setWorkerAssigned(employee1);
        facilityBooking2.setWorkerAssigned(employee3);
        facilityBooking3.setWorkerAssigned(employee5);
        facilityBookingRepository.save(facilityBooking1);
        facilityBookingRepository.save(facilityBooking2);
        facilityBookingRepository.save(facilityBooking3);
        facilityBookingRepository.save(facilityBooking4);
        facilityBookingRepository.save(facilityBooking5);
    }

    protected void createTestingBedroomBookings(){
        createTestingBedrooms();
        createTestingClients();
        Date arrivalDate1 = new Date(125, 5, 30);
        Date departureDate1 = new Date(125, 6, 10);
        Date arrivalDate2 = new Date(125, 8, 25);
        Date departureDate2 = new Date(125, 8, 30);
        Date arrivalDate3 = new Date(125, 9, 15);
        Date departureDate3 = new Date(125, 9, 30);
        bedroomBooking1 = new BedroomBookings(bedroom1, client1,arrivalDate1,departureDate1);
        bedroomBooking2 = new BedroomBookings(bedroom1, client2,arrivalDate2,departureDate2);
        bedroomBooking3 = new BedroomBookings(bedroom2, client3,arrivalDate3,departureDate3);
        bedroomBooking4 = new BedroomBookings(bedroom3, client4,arrivalDate1,departureDate3);
        bedroomBooking5 = new BedroomBookings(bedroom6, client1,arrivalDate2,departureDate3);
        bedroomBookingsRepository.save(bedroomBooking1);
        bedroomBookingsRepository.save(bedroomBooking2);
        bedroomBookingsRepository.save(bedroomBooking3);
        bedroomBookingsRepository.save(bedroomBooking4);
        bedroomBookingsRepository.save(bedroomBooking5);
    }
}