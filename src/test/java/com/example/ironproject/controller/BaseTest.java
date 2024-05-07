package com.example.ironproject.controller;

import com.example.ironproject.enumeration.Jobs;
import com.example.ironproject.model.Booking.BedroomBookings;
import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.model.Booking.Service;
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
    protected int clientId, clientId1;
    protected Employee employee1, employee2, employee3, employee4, employee5;
    protected int employeeId, employeeId1;
    protected BedroomBookings bedroomBooking1, bedroomBooking2,bedroomBooking3,bedroomBooking4,bedroomBooking5;
    protected int bedroomBookingId, bedroomBookingId1;
    protected FacilityBooking facilityBooking1,facilityBooking2,facilityBooking3,facilityBooking4,facilityBooking5;
    protected int facilityBookingId, facilityBookingId1;


    @BeforeAll
    protected void initialSetUp() throws Exception {
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
        long ms = 900000000;
        Date date = new Date(ms);
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
        long ms = 900000000;
        Date date = new Date(ms);
        Role admin = new Role("ROLE_ADMIN");
        Role employee = new Role("ROLE_EMPLOYEE");
        employee1 = new Employee("1234","Solanum",date, Jobs.Reception,admin, "Solanum","12345678");
        employee2 = new Employee("59595959", "Thais", date, Jobs.Cleaning, employee, "comolainfusion","12345678");
        employee3 = new Employee("5676","Xavi",date, Jobs.Reception,admin, "Xavi","12345678");
        employee4 = new Employee("9876","Ricard",date, Jobs.Cleaning,employee, "Ricard","12345678");
        employee5 = new Employee("6543","Victor",date, Jobs.Cleaning,employee, "Victor","12345678");
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);
    }

    protected void createTestingFacilityBookings(){
        createTestingFacilities();
        createTestingEmployees();
        createTestingClients();
        long ms = 900000000;
        Date date = new Date(ms);
        facilityBooking1 = new FacilityBooking(gym1,client1, Service.Gym, date);
        facilityBooking1 = new FacilityBooking(gym1,client2, Service.Gym, date);
        facilityBooking1 = new FacilityBooking(gym2,client3, Service.Gym, date);
        facilityBooking1 = new FacilityBooking(sauna,client4, Service.Cleaning, date);
        facilityBooking1 = new FacilityBooking(restaurant1,client5, Service.Cleaning, date);
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
        long ms = 900000000;
        Date date = new Date(ms);
        bedroomBooking1 = new BedroomBookings(bedroom1, client1,date,date);
        bedroomBooking2 = new BedroomBookings(bedroom1, client2,date,date);
        bedroomBooking3 = new BedroomBookings(bedroom2, client3,date,date);
        bedroomBooking4 = new BedroomBookings(bedroom3, client4,date,date);
        bedroomBooking5 = new BedroomBookings(bedroom4, client1,date,date);
        bedroomBookingsRepository.save(bedroomBooking1);
        bedroomBookingsRepository.save(bedroomBooking2);
        bedroomBookingsRepository.save(bedroomBooking3);
        bedroomBookingsRepository.save(bedroomBooking4);
        bedroomBookingsRepository.save(bedroomBooking5);
    }
}
