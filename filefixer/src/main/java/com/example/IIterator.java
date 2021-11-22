package com.example;

//This interface represent the AbstractIterator, defining the iterator.
public interface IIterator {
    public Boolean hasNext();//Method to check if collection has next object
    public Object next();//Method to go to next object in collection
}
