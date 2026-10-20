package com.example.tourapp.config;

import com.example.tourapp.dto.BookingDTO;
import com.example.tourapp.dto.ClientDTO;
import com.example.tourapp.dto.PlaceDTO;
import com.example.tourapp.dto.TourDTO;
import com.example.tourapp.dto.TripDTO;
import com.example.tourapp.model.Booking;
import com.example.tourapp.model.Bus;
import com.example.tourapp.model.Client;
import com.example.tourapp.model.Driver;
import com.example.tourapp.model.Guide;
import com.example.tourapp.model.Place;
import com.example.tourapp.model.Tour;
import com.example.tourapp.model.Trip;
import com.example.tourapp.model.UserData;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapperConfigTest {

    private final MapperConfig config = new MapperConfig();

    @Test
    void clientAndPlaceRoundTrip() {
        ModelMapper mapper = config.defaultMapper();

        Client client = new Client();
        client.setIdClient(1);
        client.setFirstName("Ana");
        client.setLastName("Perez");
        client.setDni("12345678");
        client.setEmail("ana@mail.com");

        ClientDTO clientDTO = mapper.map(client, ClientDTO.class);
        assertEquals(1, clientDTO.getIdClient());
        assertEquals("Ana", clientDTO.getFirstName());
        assertEquals("Ana", mapper.map(clientDTO, Client.class).getFirstName());

        Place place = new Place();
        place.setIdPlace(5);
        place.setNamePlace("Machu Picchu");
        place.setEntryFee(new BigDecimal("50.00"));

        PlaceDTO placeDTO = mapper.map(place, PlaceDTO.class);
        assertEquals(5, placeDTO.getIdPlace());
        assertEquals("Machu Picchu", placeDTO.getNamePlace());
    }

    @Test
    void tourRoundTripWithPlaces() {
        ModelMapper mapper = config.defaultMapper();

        Place place = new Place();
        place.setIdPlace(5);
        place.setNamePlace("Machu Picchu");
        place.setEntryFee(new BigDecimal("50.00"));
        Tour tour = new Tour();
        tour.setIdTour(2);
        tour.setNameTour("Cusco");
        tour.setDescription("Tour a Cusco");
        tour.setBasePrice(new BigDecimal("100.00"));
        tour.setDurationHours(new BigDecimal("2.50"));
        tour.setPlaces(List.of(place));

        TourDTO dto = mapper.map(tour, TourDTO.class);
        assertEquals(2, dto.getIdTour());
        assertEquals(1, dto.getPlaces().size());
        assertEquals(5, dto.getPlaces().get(0).getIdPlace());

        Tour back = mapper.map(dto, Tour.class);
        assertNotNull(back.getPlaces());
        assertEquals(1, back.getPlaces().size());
        assertEquals(5, back.getPlaces().get(0).getIdPlace());
    }

    @Test
    void tripRoundTripWithRelations() {
        ModelMapper mapper = config.defaultMapper();

        Tour tour = new Tour();
        tour.setIdTour(2);
        Guide guide = new Guide();
        guide.setIdGuide(3);
        Driver driver = new Driver();
        driver.setIdDriver(4);
        Bus bus = new Bus();
        bus.setIdBus(5);

        Trip trip = new Trip();
        trip.setIdTrip(1);
        trip.setTripDate(LocalDate.of(2026, 10, 20));
        trip.setDepartureTime(LocalTime.of(8, 0));
        trip.setTour(tour);
        trip.setGuide(guide);
        trip.setDriver(driver);
        trip.setBus(bus);

        TripDTO dto = mapper.map(trip, TripDTO.class);
        assertEquals(1, dto.getIdTrip());
        assertEquals(2, dto.getIdTour());
        assertEquals(3, dto.getIdGuide());
        assertEquals(4, dto.getIdDriver());
        assertEquals(5, dto.getIdBus());

        Trip back = mapper.map(dto, Trip.class);
        assertEquals(2, back.getTour().getIdTour());
        assertEquals(3, back.getGuide().getIdGuide());
        assertEquals(4, back.getDriver().getIdDriver());
        assertEquals(5, back.getBus().getIdBus());
    }

    @Test
    void bookingRoundTripWithRelations() {
        ModelMapper mapper = config.defaultMapper();

        Client client = new Client();
        client.setIdClient(1);
        UserData user = new UserData();
        user.setIdUser(2);
        Trip trip = new Trip();
        trip.setIdTrip(3);

        Booking booking = new Booking();
        booking.setIdBooking(4);
        booking.setDateTime(LocalDateTime.of(2026, 10, 20, 9, 30));
        booking.setTotalPrice(new BigDecimal("200.00"));
        booking.setClient(client);
        booking.setUser(user);
        booking.setTrip(trip);

        BookingDTO dto = mapper.map(booking, BookingDTO.class);
        assertEquals(4, dto.getIdBooking());
        assertEquals(1, dto.getIdClient());
        assertEquals(2, dto.getIdUser());
        assertEquals(3, dto.getIdTrip());

        Booking back = mapper.map(dto, Booking.class);
        assertEquals(1, back.getClient().getIdClient());
        assertEquals(2, back.getUser().getIdUser());
        assertEquals(3, back.getTrip().getIdTrip());
    }
}
