package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student foo;
    @BeforeEach
    public void setup(){
        foo = new Student("601725", "nicholasfoo", "81304376",true);
    }
    @Test
    public void testGetNameOfStudent(){
        System.out.println("getNameOfStudent");
        String expResult = "nicholasfoo";
        String result = foo.getname();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetIDOfStudent(){
        System.out.println("getIDOfStudent");
        String expResult = "81304376";
        String actual = foo.getID();
        assertEquals(expResult, actual);
    }
    @Test
    public void testGetPIDOfStudent(){
        System.out.println("getPIDOfStudent");
        String expResult = "601725";
        String actual = foo.getPID();
        assertEquals(expResult, actual);
    }
    @Test
    public void testGetStudentAttendance(){
        System.out.println("getAttendanceOfStudent");
        
        Boolean actual = foo.getAttendanceStatus();
        assertTrue(actual);
    }

    @Test
    public void testToStringOfStudentClass(){
        System.out.println("TestingToStringOfStudentClass");
        /**
         * "PID: " + getPID() + "\nName: " + 
         * getname() + "\nID: " + getID() + "\nHas Attended: " + 
         * getAttendanceStatus();
         */
        String expResult = String.format("PID: %s\nName: %s\nID: %s\nHas Attended: %b", "601725", "nicholasfoo", "81304376", true);
        String actual = String.format("PID: %s\nName: %s\nID: %s\nHas Attended: %b", foo.getPID(), foo.getname(), foo.getID(), foo.getAttendanceStatus());
        assertEquals(expResult, actual);
    }
}
