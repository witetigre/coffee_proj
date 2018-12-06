package service;

import service.Coffee;


import java.sql.Array;
import java.util.Map;

public class Main {
    public static void main(String[] args){


        CoffeeGround coffeeGround = new CoffeeGround("bank");
        coffeeGround.loadCoffee(10f, 256f);
        System.out.println("End");

    }
}
