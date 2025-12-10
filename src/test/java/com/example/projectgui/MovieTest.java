package com.example.projectgui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieTest {

    @Test
    void calculate() {
        //Students payment
        Movie movie = new Movie("M12","Test Movie","Drama",true);
        int days = 10;
        movie.setStudentId("S1");
        int result = movie.calculate(days);
        assertEquals(8,result,"Should return correct amount to pay");

        //External member payment
        Movie movie2 = new Movie("M13","Test Movie 2","Drama",true);
        int days2 = 10;
        movie2.setMemberId("E1");
        int result2 = movie2.calculate(days2);
        assertEquals(16,result2,"Should return correct amount to pay");
    }
}