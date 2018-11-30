package com.rentavehicle;

import com.rentavehicle.model.*;
import com.rentavehicle.service.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestData {

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private PriceListItemService priceListItemService;

    @Autowired
    private ReservationService reservationService;

    @PostConstruct
    public void init() {


        // Authorities test data

        String au1 = "ROLE_ADMIN";
        String au2 = "ROLE_MANAGER";
        String au3 = "ROLE_USER";

        List<String> authorities1 = new ArrayList<>();
        authorities1.add(au1);


        List<String> authorities2 = new ArrayList<>();
        authorities2.add(au2);

        List<String> authorities3 = new ArrayList<>();
        authorities3.add(au3);


        // User test data

        User u1 = new User();
        u1.setUsername("user1");
        u1.setPassword("$2a$10$dChX5BFdvueSIvxs/x7SfuO6g.tqzuzI1YS3DST/3AULnFczc5JxK"); // pass
        u1.setRoles(authorities1); // ADMIN
        userService.save(u1);

        User u2 = new User();
        u2.setUsername("user2");
        u2.setPassword("$2a$10$RYSS4hATtXM/yA/mQQ81MuUGlaDJdbulS4JeV9Kmrk.sXMpYATa92"); // pass
        u2.setRoles(authorities2); // MANAGER
        userService.save(u2);

        User u3 = new User();
        u3.setUsername("user3");
        u3.setPassword("$2a$10$RYSS4hATtXM/yA/mQQ81MuUGlaDJdbulS4JeV9Kmrk.sXMpYATa92"); // pass
        u3.setRoles(authorities3); // USER
        userService.save(u3);


        // Agency test data

        Agency a1 = new Agency();
        a1.setName("Agency 1");
        a1.setDescription("Description 1");
        a1.setPhoneNumber("Number 1");
        a1.setEmial("mail1@mail.com");
        a1.setOwner(u1);
        agencyService.save(a1);

        Agency a2 = new Agency();
        a2.setName("Agency 2");
        a2.setDescription("Description 2");
        a2.setPhoneNumber("Number 2");
        a2.setEmial("mail2@mail.com");
        a2.setOwner(u2);
        agencyService.save(a2);

        Agency a3 = new Agency();
        a3.setName("Agency 3");
        a3.setDescription("Description 3");
        a3.setPhoneNumber("Number 3");
        a3.setEmial("mail3@mail.com");
        a3.setOwner(u2);
        agencyService.save(a3);

        Agency a4 = new Agency();
        a4.setName("Agency 4");
        a4.setDescription("Description 4");
        a4.setPhoneNumber("Number 4");
        a4.setEmial("mail4@mail.com");
        a4.setOwner(u1);
        agencyService.save(a4);

        Agency a5 = new Agency();
        a5.setName("Agency 5");
        a5.setDescription("Description 5");
        a5.setPhoneNumber("Number 5");
        a5.setEmial("mail5@mail.com");
        a5.setOwner(u2);
        agencyService.save(a5);

        Agency a6 = new Agency();
        a6.setName("Agency 6");
        a6.setDescription("Description 6");
        a6.setPhoneNumber("Number 6");
        a6.setEmial("mail6@mail.com");
        a6.setOwner(u1);
        agencyService.save(a6);


//        // Branch test data

        Branch b1 = new Branch();
        b1.setAddress("Address 1");
        b1.setPhoneNumber("Number1");
        b1.setAgency(a2);
        branchService.save(b1);

        Branch b2 = new Branch();
        b2.setAddress("Address 2");
        b2.setPhoneNumber("Number2");
        b2.setAgency(a1);
        branchService.save(b2);

        Branch b3 = new Branch();
        b3.setAddress("Address 3");
        b3.setPhoneNumber("Number3");
        b3.setAgency(a2);
        branchService.save(b3);

        Branch b4 = new Branch();
        b4.setAddress("Address 4");
        b4.setPhoneNumber("Number4");
        b4.setAgency(a1);
        branchService.save(b4);


        // VehicleType test data

        VehicleType vt1 = new VehicleType();
        vt1.setName("Car");
        vehicleTypeService.save(vt1);

        VehicleType vt2 = new VehicleType();
        vt2.setName("Motorcycle");
        vehicleTypeService.save(vt2);

        VehicleType vt3 = new VehicleType();
        vt3.setName("Bicycle");
        vehicleTypeService.save(vt3);


        // Vehicle test data

        Vehicle v1 = new Vehicle();
        v1.setName("Volkswagen Golf 6");
        v1.setAvailable(true);
        v1.setDescription("Neki random opis");
        v1.setSpecification("The new GTI is powered by a 2.0-litre turbocharged direct-injection petrol engine (TSI) with 220 PS (162 kW; 217 hp).");
        v1.setAgency(a1);
        v1.setVehicleType(vt1);
        vehicleService.save(v1);

        Vehicle v2 = new Vehicle();
        v2.setName("Audi A6");
        v2.setAvailable(false);
        v2.setDescription("Neki random opis");
        v2.setSpecification("It is a version of S6 Avant with increased engine power to 560 PS (412 kW; 552 hp) at 5700–6700 rpm and 700 N⋅m (516.3 lbf⋅ft) at 1750–5500 rpm.");
        v2.setAgency(a1);
        v2.setVehicleType(vt1);
        vehicleService.save(v2);

        Vehicle v3 = new Vehicle();
        v3.setName("Skoda Superb");
        v3.setAvailable(true);
        v3.setDescription("Neki random opis");
        v3.setSpecification("1.4 litre inline four cylinder (I4) TFSI (with a turbocharger and Fuel Stratified Injection), and a 118 kW (160 PS; 158 hp) 1.8 litre I4 TFSI.");
        v3.setAgency(a1);
        v3.setVehicleType(vt1);
        vehicleService.save(v3);

        Vehicle v4 = new Vehicle();
        v4.setName("Yamaha Aerox");
        v4.setAvailable(true);
        v4.setDescription("Neki random opis");
        v4.setSpecification("The Yamaha Aerox is a range of single-cylinder scooters made by Yamaha since 1997, available in either 50 cc or 100 cc for the European market, and 125 cc or 155 cc for the South East Asian market with several different body designs.");
        v4.setAgency(a1);
        v4.setVehicleType(vt2);
        vehicleService.save(v4);

        Vehicle v5 = new Vehicle();
        v5.setName("Aprilia Pegaso");
        v5.setAvailable(true);
        v5.setDescription("Neki random opis");
        v5.setSpecification("The Pegaso's 652cc powerplant is liquid-cooled and features double overhead camshafts operating on five radial valves. Aprilia claim 47hp ...");
        v5.setAgency(a2);
        v5.setVehicleType(vt2);
        vehicleService.save(v5);

        Vehicle v6 = new Vehicle();
        v6.setName("Scott Spark Pro 700");
        v6.setAvailable(true);
        v6.setDescription("Neki random opis");
        v6.setSpecification("The Spark Pro 700 is the ideal next step for any aspiring athlete who wants to compete with the best.");
        v6.setAgency(a2);
        v6.setVehicleType(vt3);
        vehicleService.save(v6);


        // PriceList test data

        PriceList pl1 = new PriceList();
        pl1.setAgency(a1);
        pl1.setStartDate(new LocalDate(2018, 1, 1));
        pl1.setEndDate(new LocalDate(2018, 12, 31));
        priceListService.save(pl1);

        PriceList pl2 = new PriceList();
        pl2.setAgency(a1);
        pl2.setStartDate(new LocalDate(2019, 1, 1));
        pl2.setEndDate(new LocalDate(2019, 12, 31));
        priceListService.save(pl2);

        PriceList pl3 = new PriceList();
        pl3.setAgency(a2);
        pl3.setStartDate(new LocalDate(2018, 1, 1));
        pl3.setEndDate(new LocalDate(2018, 12, 31));
        priceListService.save(pl3);


        // PriceListItem test data

        PriceListItem pli1 = new PriceListItem();
        pli1.setPricePerHour(5);
        pli1.setVehicle(v1);
        pli1.setPriceList(pl1);
        priceListItemService.save(pli1);

        PriceListItem pli2 = new PriceListItem();
        pli2.setPricePerHour(10);
        pli2.setVehicle(v2);
        pli2.setPriceList(pl1);
        priceListItemService.save(pli2);

        PriceListItem pli3 = new PriceListItem();
        pli3.setPricePerHour(1);
        pli3.setVehicle(v3);
        pli3.setPriceList(pl1);
        priceListItemService.save(pli3);

        PriceListItem pli4 = new PriceListItem();
        pli4.setPricePerHour(0.5);
        pli4.setVehicle(v4);
        pli4.setPriceList(pl1);
        priceListItemService.save(pli4);


        PriceListItem pli5 = new PriceListItem();
        pli5.setPricePerHour(7);
        pli5.setVehicle(v1);
        pli5.setPriceList(pl2);
        priceListItemService.save(pli5);

        PriceListItem pli6 = new PriceListItem();
        pli6.setPricePerHour(11);
        pli6.setVehicle(v2);
        pli6.setPriceList(pl2);
        priceListItemService.save(pli6);

        PriceListItem pli7 = new PriceListItem();
        pli7.setPricePerHour(2.5);
        pli7.setVehicle(v3);
        pli7.setPriceList(pl2);
        priceListItemService.save(pli7);

        PriceListItem pli8 = new PriceListItem();
        pli8.setPricePerHour(4);
        pli8.setVehicle(v5);
        pli8.setPriceList(pl3);
        priceListItemService.save(pli8);

        PriceListItem pli9 = new PriceListItem();
        pli9.setPricePerHour(1.25);
        pli9.setVehicle(v6);
        pli9.setPriceList(pl3);
        priceListItemService.save(pli9);


        // reservation test data

        DateTime startDate1 = new DateTime(2018, 11, 1, 1, 33, 0);
        DateTime endDate1 = new DateTime(2018, 11, 30, 1, 33, 0);


        DateTime startDate2 = new DateTime(2019, 11, 1, 1, 33, 0);
        DateTime endDate2 = new DateTime(2019, 11, 30, 1, 33, 0);

        Reservation res1 = new Reservation();
        res1.setVehicle(v1);
        res1.setBranchPickup(b2);
        res1.setBranchDelivery(b2);
        res1.setUser(u1);
        res1.setStartDate(startDate1);
        res1.setEndDate(endDate1);
        res1.setTotalPrice(5433.50);
        reservationService.save(res1);

        Reservation res2 = new Reservation();
        res2.setVehicle(v2);
        res2.setBranchPickup(b2);
        res2.setBranchDelivery(b2);
        res2.setUser(u2);
        res2.setStartDate(startDate2);
        res2.setEndDate(endDate2);
        res2.setTotalPrice(2225.35);
        reservationService.save(res2);

        Reservation res3 = new Reservation();
        res3.setVehicle(v5);
        res3.setBranchPickup(b1);
        res3.setBranchDelivery(b1);
        res3.setUser(u3);
        res3.setStartDate(startDate1);
        res3.setEndDate(endDate1);
        res3.setTotalPrice(112.55);
        reservationService.save(res3);


        Reservation res4 = new Reservation();
        res4.setVehicle(v1);
        res4.setBranchPickup(b2);
        res4.setBranchDelivery(b2);
        res4.setUser(u1);
        res4.setStartDate(startDate2);
        res4.setEndDate(endDate2);
        res4.setTotalPrice(1299.25);
        reservationService.save(res4);

        Reservation res5 = new Reservation();
        res5.setVehicle(v3);
        res5.setBranchPickup(b2);
        res5.setBranchDelivery(b2);
        res5.setUser(u2);
        res5.setStartDate(startDate2);
        res5.setEndDate(endDate2);
        res5.setTotalPrice(2225.35);
        reservationService.save(res5);

    }
}
