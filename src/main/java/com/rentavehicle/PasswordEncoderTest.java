package com.rentavehicle;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void encryptPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode("pass");

        System.out.println(encodedPassword);
    }


    @Test
    public void dateParsing(){


        LocalDate dt = new LocalDate(1994, 11, 28);

        System.out.println(dt);

    }

}
