package com.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
public class ReaderTest {
    
    private Reader testReader;
    @BeforeEach
    public void setup(){
        testReader = new Reader();
    }

    @Test
    public void testLoadCsvData(){
        System.out.println("Testing Load Csv Data Function");
        Student[] testLCDOutput;
        testLCDOutput = testReader.LoadCsvData();
        assertTrue(testLCDOutput.length > 0);
    }

    @Test
    public void testLoadDirectoryFiles(){
        System.out.println("Testing Load Directory Files Function");
        ToRename[] testLDFOutput;
        try {
            testLDFOutput = testReader.LoadDirectoryFiles();
            assertTrue(testLDFOutput.length > 0);
        } catch (Exception e) {
            System.out.println("Error while loading files from directory");
            e.printStackTrace();
        }
        
    }

}
