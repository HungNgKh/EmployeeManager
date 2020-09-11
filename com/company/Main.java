package com.company;


import java.util.ArrayList;

public class Main {

    public static class Obj{
        private int a;

        public Obj(int a)
        {
            this.a = a;
        }

        public int get()
        {
            return a;
        }
    }

    public static void main(String[] args) {

        ArrayList list = new ArrayList();
        list.add(new Obj(1));
        list.add(new Obj(2));
        list.add("String");

        Object[] strs = list.toArray();
        for(Object i : strs)
        {
            System.out.println(i.getClass());
            System.out.println(i instanceof Obj ? ((Obj)i).get() : "Failed");
        }
    }
}
