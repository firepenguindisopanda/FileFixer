package com.example;

import java.util.ArrayList;

class FileCollection implements IContainer {//An implementation of the collection
    private java.util.ArrayList <String> toBeRenamed;

    public void getList(ArrayList<String> list){//Method to get the collection from the composite class.
        toBeRenamed = list;
    }
    public IIterator createIterator() {
        FileIterator result = new FileIterator();
        return result;
    }


    private class FileIterator implements IIterator{//This is the implementation of Iterator(implements the IIterator interface)
        private int m_position;
        
        public Boolean hasNext() 
        {
            if (m_position < toBeRenamed.size())
                return true;
            else
                return false;
        }

       
        public Object next() 
        {
            if(this.hasNext())
                return toBeRenamed.get(m_position++);
            else    
                return null;
        }

    }
}

