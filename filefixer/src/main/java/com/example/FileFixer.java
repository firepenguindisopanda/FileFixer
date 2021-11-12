package com.example;

/**
 * Write a description of class Runner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FileFixer
{
    
    
    public static void main(String[] args) throws Exception{
    
    
    Reader r1 = new Reader();
    
    r1.LoadCsvData("SampleData.csv");
    r1.LoadDirectoryFiles();
}
}
