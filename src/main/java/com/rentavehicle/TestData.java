package com.rentavehicle;

import com.rentavehicle.model.*;
import com.rentavehicle.repository.BranchImageRepository;
import com.rentavehicle.repository.VehicleImageRepository;
import com.rentavehicle.service.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestData {

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

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

    @Autowired
    private RatingService ratingService;

    @Autowired
    private VehicleImageRepository vehicleImageRepository;

    @Autowired
    private BranchImageRepository branchImageRepository;

    @PostConstruct
    public void init() {

        // UserRole test data

        UserRole ur1 = new UserRole();
        ur1.setName("ROLE_ADMIN");
        userRoleService.save(ur1);

        UserRole ur2 = new UserRole();
        ur2.setName("ROLE_MANAGER");
        userRoleService.save(ur2);

        UserRole ur3 = new UserRole();
        ur3.setName("ROLE_USER");
        userRoleService.save(ur3);


        // User test data

        User u1 = new User();
        u1.setUsername("user1");
        u1.setPassword("$2a$10$dChX5BFdvueSIvxs/x7SfuO6g.tqzuzI1YS3DST/3AULnFczc5JxK"); // pass
        u1.setUserRole(ur1); // ADMIN
        u1.setApproved(true);
        userService.save(u1);

        User u2 = new User();
        u2.setUsername("user2");
        u2.setPassword("$2a$10$RYSS4hATtXM/yA/mQQ81MuUGlaDJdbulS4JeV9Kmrk.sXMpYATa92"); // pass
        u2.setUserRole(ur2); // MANAGER
        u2.setApproved(true);
        userService.save(u2);

        User u3 = new User();
        u3.setUsername("user3");
        u3.setPassword("$2a$10$RYSS4hATtXM/yA/mQQ81MuUGlaDJdbulS4JeV9Kmrk.sXMpYATa92"); // pass
        u3.setUserRole(ur3); // USER
        userService.save(u3);


        // Agency test data

        Agency a1 = new Agency();
        a1.setName("Car Rental");
        a1.setDescription("Neki random opis o agenciji...");
        a1.setPhoneNumber("+381642243464");
        a1.setEmial("mail1@mail.com");
        a1.setOwner(u1);
        a1.setLogo("images/agency-logo/1/logo1.png");
        agencyService.save(a1);

        Agency a2 = new Agency();
        a2.setName("Secure Rent");
        a2.setDescription("Neki random opis o agenciji...");
        a2.setPhoneNumber("+381631242347");
        a2.setEmial("mail2@mail.com");
        a2.setOwner(u2);
        a2.setLogo("images/agency-logo/2/logo2.jpg");
        agencyService.save(a2);

        Agency a3 = new Agency();
        a3.setName("ForCar");
        a3.setDescription("Neki random opis o agenciji...");
        a3.setPhoneNumber("+381612241234");
        a3.setEmial("mail3@mail.com");
        a3.setOwner(u2);
        a3.setLogo("images/agency-logo/3/logo3.jpg");
        agencyService.save(a3);

        Agency a4 = new Agency();
        a4.setName("Cole Rental");
        a4.setDescription("Neki random opis o agenciji...");
        a4.setPhoneNumber("+38166971232");
        a4.setEmial("mail4@mail.com");
        a4.setOwner(u1);
        a4.setLogo("images/agency-logo/4/logo4.jpg");
        agencyService.save(a4);

        Agency a5 = new Agency();
        a5.setName("Auto Rent");
        a5.setDescription("Neki random opis o agenciji...");
        a5.setPhoneNumber("+381628241838");
        a5.setEmial("mail5@mail.com");
        a5.setOwner(u2);
        a5.setLogo("images/agency-logo/5/logo5.png");
        agencyService.save(a5);

        Agency a6 = new Agency();
        a6.setName("X-Car Rental");
        a6.setDescription("Neki random opis o agenciji...");
        a6.setPhoneNumber("+38161729495");
        a6.setEmial("mail6@mail.com");
        a6.setOwner(u1);
        a6.setLogo("images/agency-logo/6/logo6.jpg");
        agencyService.save(a6);


//        // Branch test data

        Branch b1 = new Branch();
        b1.setAddress("Bul. Kralja Petra I 38, Novi Sad");
        b1.setPhoneNumber("+3816599785");
        b1.setAgency(a2);
        b1.setLatitude(45.259592);
        b1.setLongitude(19.828038);
        branchService.save(b1);

        Branch b2 = new Branch();
        b2.setAddress("Stražilovska 34, Novi Sad");
        b2.setPhoneNumber("+38164225551");
        b2.setAgency(a1);
        b2.setLatitude(45.249461);
        b2.setLongitude(19.848467);
        branchService.save(b2);

        Branch b3 = new Branch();
        b3.setAddress("Kosovska 41, Novi Sad");
        b3.setPhoneNumber("+38166226212");
        b3.setAgency(a2);
        b3.setLatitude(45.263129);
        b3.setLongitude(19.850361);
        branchService.save(b3);

        Branch b4 = new Branch();
        b4.setAddress("Belgijska 7, Beograd");
        b4.setPhoneNumber("+381621161273");
        b4.setAgency(a1);
        b4.setLatitude(44.792514);
        b4.setLongitude(20.440736);
        branchService.save(b4);

        Branch b5 = new Branch();
        b5.setAddress("Mutapopova 32, Beograd");
        b5.setPhoneNumber("+38161621273");
        b5.setAgency(a1);
        b5.setLatitude(44.799295);
        b5.setLongitude(20.473711);
        branchService.save(b5);

        Branch b6 = new Branch();
        b6.setAddress("Bul. Zorana Đinđića 6, Beograd");
        b6.setPhoneNumber("+38166421233");
        b6.setAgency(a1);
        b6.setLatitude(44.813026);
        b6.setLongitude(20.427663);
        branchService.save(b6);


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
        v1.setName("Volkswagen Golf 7");
        v1.setAvailable(true);
        v1.setDescription("Neki random opis...");
        v1.setSpecification("The new GTI is powered by a 2.0-litre turbocharged direct-injection petrol engine (TSI) with 220 PS (162 kW; 217 hp).");
        v1.setAgency(a1);
        v1.setVehicleType(vt1);
        vehicleService.save(v1);

        Vehicle v2 = new Vehicle();
        v2.setName("Audi A6");
        v2.setAvailable(false);
        v2.setDescription("Neki random opis...");
        v2.setSpecification("It is a version of S6 Avant with increased engine power to 560 PS (412 kW; 552 hp) at 5700–6700 rpm and 700 N⋅m (516.3 lbf⋅ft) at 1750–5500 rpm.");
        v2.setAgency(a1);
        v2.setVehicleType(vt1);
        vehicleService.save(v2);

        Vehicle v3 = new Vehicle();
        v3.setName("Skoda Superb");
        v3.setAvailable(true);
        v3.setDescription("Neki random opis...");
        v3.setSpecification("1.4 litre inline four cylinder (I4) TFSI (with a turbocharger and Fuel Stratified Injection), and a 118 kW (160 PS; 158 hp) 1.8 litre I4 TFSI.");
        v3.setAgency(a1);
        v3.setVehicleType(vt1);
        vehicleService.save(v3);

        Vehicle v4 = new Vehicle();
        v4.setName("Yamaha Aerox");
        v4.setAvailable(true);
        v4.setDescription("Neki random opis...");
        v4.setSpecification("The Yamaha Aerox is a range of single-cylinder scooters made by Yamaha since 1997, available in either 50 cc or 100 cc for the European market, and 125 cc or 155 cc for the South East Asian market with several different body designs.");
        v4.setAgency(a1);
        v4.setVehicleType(vt2);
        vehicleService.save(v4);

        Vehicle v5 = new Vehicle();
        v5.setName("Aprilia Pegaso");
        v5.setAvailable(true);
        v5.setDescription("Neki random opis...");
        v5.setSpecification("The Pegaso's 652cc powerplant is liquid-cooled and features double overhead camshafts operating on five radial valves. Aprilia claim 47hp ...");
        v5.setAgency(a2);
        v5.setVehicleType(vt2);
        vehicleService.save(v5);

        Vehicle v6 = new Vehicle();
        v6.setName("Scott Spark Pro 700");
        v6.setAvailable(true);
        v6.setDescription("Neki random opis...");
        v6.setSpecification("The Spark Pro 700 is the ideal next step for any aspiring athlete who wants to compete with the best riders in the world.");
        v6.setAgency(a2);
        v6.setVehicleType(vt3);
        vehicleService.save(v6);

        Vehicle v7 = new Vehicle();
        v7.setName("Mercedes AMG GLE");
        v7.setAvailable(true);
        v7.setDescription("Neki random opis...");
        v7.setSpecification("The overall silhouette of the Mercedes-AMG GLE 63 Coupé appears ready for the next challenge at any time: Whatever the challenge – it does not matter to the Mercedes-AMG GLE 63 Coupé delivers best performance");
        v7.setAgency(a1);
        v7.setVehicleType(vt1);
        vehicleService.save(v7);


        Vehicle v8 = new Vehicle();
        v8.setName("Volkswagen Beetle");
        v8.setAvailable(true);
        v8.setDescription("Neki random opis...");
        v8.setSpecification("The Beetle featured a rear-located, rear-wheel drive, air-cooled four-cylinder, boxer engine in a two-door bodywork featuring a flat front windscreen...");
        v8.setAgency(a1);
        v8.setVehicleType(vt1);
        vehicleService.save(v8);

        Vehicle v9 = new Vehicle();
        v9.setName("Volkswagen Passat");
        v9.setAvailable(true);
        v9.setDescription("Neki random opis...");
        v9.setSpecification("The base 1.8L turbocharged and direct-injection four-cylinder mill makes 170 horsepower and 184 lb.-ft. of torque.");
        v9.setAgency(a1);
        v9.setVehicleType(vt1);
        vehicleService.save(v9);

        Vehicle v10 = new Vehicle();
        v10.setName("Porsche Cayenne");
        v10.setAvailable(true);
        v10.setDescription("Neki random opis...");
        v10.setSpecification("The GTS is powered with a 405 PS (298 kW; 399 hp) 4.8 L V8 and features a sport suspension and 21-inch (533 mm) wheels.");
        v10.setAgency(a1);
        v10.setVehicleType(vt1);
        vehicleService.save(v10);

        Vehicle v11 = new Vehicle();
        v11.setName("Volkswagen Touareg");
        v11.setAvailable(true);
        v11.setDescription("Neki random opis...");
        v11.setSpecification("The Volkswagen Touareg is a mid-size luxury crossover SUV produced by German automaker Volkswagen since 2002 at the Volkswagen Bratislava Plant.");
        v11.setAgency(a1);
        v11.setVehicleType(vt1);
        vehicleService.save(v11);


        // PriceList test data

        PriceList pl1 = new PriceList();
        pl1.setAgency(a1);
        pl1.setStartDate(new LocalDate(2019, 1, 1));
        pl1.setEndDate(new LocalDate(2019, 12, 31));
        priceListService.save(pl1);

        PriceList pl2 = new PriceList();
        pl2.setAgency(a1);
        pl2.setStartDate(new LocalDate(2020, 1, 1));
        pl2.setEndDate(new LocalDate(2020, 12, 31));
        priceListService.save(pl2);

        PriceList pl3 = new PriceList();
        pl3.setAgency(a2);
        pl3.setStartDate(new LocalDate(2019, 1, 1));
        pl3.setEndDate(new LocalDate(2019, 12, 31));
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

        PriceListItem pli10 = new PriceListItem();
        pli10.setPricePerHour(14.99);
        pli10.setVehicle(v7);
        pli10.setPriceList(pl1);
        priceListItemService.save(pli10);

        PriceListItem pli11 = new PriceListItem();
        pli11.setPricePerHour(4.50);
        pli11.setVehicle(v8);
        pli11.setPriceList(pl1);
        priceListItemService.save(pli11);

        PriceListItem pli12 = new PriceListItem();
        pli12.setPricePerHour(6);
        pli12.setVehicle(v9);
        pli12.setPriceList(pl1);
        priceListItemService.save(pli12);

        PriceListItem pli13 = new PriceListItem();
        pli13.setPricePerHour(16.99);
        pli13.setVehicle(v10);
        pli13.setPriceList(pl1);
        priceListItemService.save(pli13);

        PriceListItem pli14 = new PriceListItem();
        pli14.setPricePerHour(11);
        pli14.setVehicle(v11);
        pli14.setPriceList(pl1);
        priceListItemService.save(pli14);

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
        res4.setVehicle(v6);
        res4.setBranchPickup(b1);
        res4.setBranchDelivery(b1);
        res4.setUser(u1);
        res4.setStartDate(startDate1);
        res4.setEndDate(endDate1);
        res4.setTotalPrice(1299.25);
        reservationService.save(res4);

        Reservation res5 = new Reservation();
        res5.setVehicle(v6);
        res5.setBranchPickup(b1);
        res5.setBranchDelivery(b3);
        res5.setUser(u2);
        res5.setStartDate(startDate1);
        res5.setEndDate(endDate1);
        res5.setTotalPrice(12235.35);
        reservationService.save(res5);

        Reservation res6 = new Reservation();
        res6.setVehicle(v9);
        res6.setBranchPickup(b2);
        res6.setBranchDelivery(b2);
        res6.setUser(u1);
        res6.setStartDate(startDate1);
        res6.setEndDate(endDate1);
        res6.setTotalPrice(3452.25);
        reservationService.save(res6);

        Reservation res7 = new Reservation();
        res7.setVehicle(v10);
        res7.setBranchPickup(b2);
        res7.setBranchDelivery(b2);
        res7.setUser(u1);
        res7.setStartDate(startDate1);
        res7.setEndDate(endDate1);
        res7.setTotalPrice(1200);
        reservationService.save(res7);


        // rating test data

        Rating r1 = new Rating();
        r1.setScore(5);
        r1.setComment("Random comment...");
        r1.setAgency(a1);
        r1.setUser(u1);
        ratingService.save(r1);

        Rating r2 = new Rating();
        r2.setScore(4);
        r2.setComment("Bla bla comment...");
        r2.setAgency(a1);
        r2.setUser(u1);
        ratingService.save(r2);

        Rating r3 = new Rating();
        r3.setScore(4);
        r3.setComment("Random comment...");
        r3.setAgency(a2);
        r3.setUser(u2);
        ratingService.save(r3);

        Rating r4 = new Rating();
        r4.setScore(5);
        r4.setComment("Bla bla comment 2...");
        r4.setAgency(a1);
        r4.setUser(u2);
        ratingService.save(r4);


        // vehicle image test data

        VehicleImage vi1 = new VehicleImage();
        vi1.setName("images/vehicle-images/1/1/golf1.jpg");
        vi1.setVehicle(v1);
        vehicleImageRepository.save(vi1);

        VehicleImage vi2 = new VehicleImage();
        vi2.setName("images/vehicle-images/1/2/audi.jpg");
        vi2.setVehicle(v2);
        vehicleImageRepository.save(vi2);

        VehicleImage vi3 = new VehicleImage();
        vi3.setName("images/vehicle-images/1/3/skoda.jpg");
        vi3.setVehicle(v3);
        vehicleImageRepository.save(vi3);

        VehicleImage vi4 = new VehicleImage();
        vi4.setName("images/vehicle-images/1/4/aerox.jpg");
        vi4.setVehicle(v4);
        vehicleImageRepository.save(vi4);

        VehicleImage vi5 = new VehicleImage();
        vi5.setName("images/vehicle-images/1/1/golf2.jpg");
        vi5.setVehicle(v1);
        vehicleImageRepository.save(vi5);

        VehicleImage vi6 = new VehicleImage();
        vi6.setName("images/vehicle-images/1/3/skoda2.jpg");
        vi6.setVehicle(v3);
        vehicleImageRepository.save(vi6);

        VehicleImage vi7 = new VehicleImage();
        vi7.setName("images/vehicle-images/1/7/mercedes.jpg");
        vi7.setVehicle(v7);
        vehicleImageRepository.save(vi7);

        VehicleImage vi8 = new VehicleImage();
        vi8.setName("images/vehicle-images/1/8/buba.jpg");
        vi8.setVehicle(v8);
        vehicleImageRepository.save(vi8);

        VehicleImage vi9 = new VehicleImage();
        vi9.setName("images/vehicle-images/1/9/passat.jpg");
        vi9.setVehicle(v9);
        vehicleImageRepository.save(vi9);

        VehicleImage vi10 = new VehicleImage();
        vi10.setName("images/vehicle-images/1/10/porsche.jpg");
        vi10.setVehicle(v10);
        vehicleImageRepository.save(vi10);

        VehicleImage vi11 = new VehicleImage();
        vi11.setName("images/vehicle-images/1/11/touareg.jpg");
        vi11.setVehicle(v11);
        vehicleImageRepository.save(vi11);

        VehicleImage vi12 = new VehicleImage();
        vi12.setName("images/vehicle-images/2/5/pegaso.jpg");
        vi12.setVehicle(v5);
        vehicleImageRepository.save(vi12);

        VehicleImage vi13 = new VehicleImage();
        vi13.setName("images/vehicle-images/2/6/scott.jpg");
        vi13.setVehicle(v6);
        vehicleImageRepository.save(vi13);


        // branch images test data

        BranchImage bi1 = new BranchImage("images/branch-images/1/2/b1.jpg", b2);
        branchImageRepository.save(bi1);

        BranchImage bi2 = new BranchImage("images/branch-images/1/4/b2.jpg", b4);
        branchImageRepository.save(bi2);

        BranchImage bi3 = new BranchImage("images/branch-images/1/5/b3.jpg", b5);
        branchImageRepository.save(bi3);

        BranchImage bi4 = new BranchImage("images/branch-images/1/6/b4.jpg", b6);
        branchImageRepository.save(bi4);
    }
}
