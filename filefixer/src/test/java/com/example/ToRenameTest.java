package com.example;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class ToRenameTest {
    private ToRename testToRename;
    @BeforeEach
    public void setup(){
        testToRename = new ToRename("601725", "nicholasfoo", "81304376",true);

    }

    @Test
    public void testToRenameInstantiation(){
        System.out.println("Testing ToRename Instantiation by AssertNotNull");
        assertNotNull(testToRename);
    }

    @Test
    public void testGetNameOfToRename(){
        System.out.println("Testing get name of ToRename Object");
        String expResult = "nicholasfoo";
        String result = testToRename.getname();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetPIDOfToRename(){
        System.out.println("Testing get PID of ToRename Object");
        String expResult = "601725";
        String result = testToRename.getPID();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetIDOfToRename(){
        System.out.println("Testing get ID of ToRename Object");
        String expResult = "81304376";
        String result = testToRename.getID();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetAttendanceOfToRename(){
        System.out.println("Testing get Attendance of ToRename Object");
        
        Boolean result = testToRename.getAttendanceStatus();
        assertEquals(true, result);
        
        
    }
    @Test
    public void testGetToBeRenamedList(){
        System.out.println("Testing getToBeRenamedList Method");
        
        java.util.ArrayList<String> test = new java.util.ArrayList<>();
        assertEquals(test.getClass(), testToRename.getToBeRenamedList().getClass());
        
    }
    @Test
    public void testAddToList(){
        System.out.println("Testing addToList");
        String dummyData = "myoriginalfilename";
        testToRename.addToList(dummyData);
        String expected = dummyData;
        String actual = testToRename.getToBeRenamedList().get(0);
        assertTrue(testToRename.getToBeRenamedList().size() > 0);

        assertEquals(expected, actual);
    }
}
