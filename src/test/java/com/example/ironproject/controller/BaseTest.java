package com.example.ironproject.controller;

import com.example.ironproject.model.Booking.BedroomBookings;
import com.example.ironproject.model.Booking.FacilityBooking;
import com.example.ironproject.model.HotelStructure.Bedroom;
import com.example.ironproject.model.HotelStructure.Facility;
import com.example.ironproject.model.HotelStructure.Hotel;
import com.example.ironproject.model.People.Client;
import com.example.ironproject.model.People.Employee;
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
    protected Facility gym1, gym2, sauna, spa, pool, restaurant1, restaurant2;
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
        hotelRepository.save(hotel);
        hotel2 = new Hotel("Wild Resort", "Estudio","Centro","Hondonada Frágil",100);
        hotelRepository.save(hotel2);
        hotel3 = new Hotel("Outer Hotels", "Estudio","Subterraneo","Lumbre",100);
        hotelRepository.save(hotel3);
        hotel4 = new Hotel("Twin Hotels", "Santuario","Nucleo","Espinoscuro",100);
        hotelRepository.save(hotel4);
        hotel5 = new Hotel("Nomai Resort", "Laboratorio","Subterraneo","Espinoscuro",100);
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




}
