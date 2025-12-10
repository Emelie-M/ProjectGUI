package com.example.projectgui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieTest {

    @Test
    void calculate() {
        Movie movie = new Movie("M12","Test Movie","Drama",true);
        int days = 10;
        movie.setStudentId("S1");
        int result = movie.calculate(days);
        assertEquals(8,result,"Should return correct amount to pay");
    }
}