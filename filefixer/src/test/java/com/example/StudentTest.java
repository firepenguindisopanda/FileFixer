package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student foo;
    @BeforeEach
    public void setup(){
        foo = new Student("601725", "nicholasfoo", "81304376",true);
    }
    @Test
    public void testStudentInstantiation(){
        System.out.println("Testing Student Object Instantiation");
        assertNotNull(foo);
    }
    @Test
    public void testGetNameOfStudent(){
        System.out.println("Testing the Student Name");
        String expResult = "nicholasfoo";
        String result = foo.getname();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetIDOfStudent(){
        System.out.println("Testing the Student ID");
        String expResult = "81304376";
        String actual = foo.getID();
        assertEquals(expResult, actual);
        assertTrue(foo.getID().length() >= 8);
    }
    @Test
    public void testGetPIDOfStudent(){
        System.out.println("Testing the Student Participant ID");
        String expResult = "601725";
        String actual = foo.getPID();
        assertEquals(expResult, actual);
    }
    //testGSA
    @Test
    public void testGetStudentAttendance(){
        System.out.println("Testing the Student attendance value");
        
        Boolean actual = foo.getAttendanceStatus();
        assertEquals(true, actual);
    }

    @Test
    public void testToStringOfStudentClass(){
        System.out.println("Testing the toString output for the Student class");
        
        String expResult = String.format("PID: %s\nName: %s\nID: %s\nHas Attended: %b", "601725", "nicholasfoo", "81304376", true);
        String actual = String.format("PID: %s\nName: %s\nID: %s\nHas Attended: %b", foo.getPID(), foo.getname(), foo.getID(), foo.getAttendanceStatus());
        assertEquals(expResult, actual);
    }
}
