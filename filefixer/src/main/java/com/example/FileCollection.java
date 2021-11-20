package com.example;

import java.util.ArrayList;

class FileCollection implements IContainer {
    private java.util.ArrayList <String> toBeRenamed;

    public void getList(ArrayList<String> list){
        toBeRenamed = list;
    }
    public IIterator createIterator() {
        FileIterator result = new FileIterator();
        return result;
    }


    private class FileIterator implements IIterator{
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