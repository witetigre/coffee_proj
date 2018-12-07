package service;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        Van van = new Van();
        CoffeeGround coffeeGround = new CoffeeGround();
        CoffeeBeans coffeeBeans = new CoffeeBeans();
        CoffeeInstant coffeeInstant = new CoffeeInstant();
        Commands commandsFunctions = new Commands();


        if(!van.checkVan()){
            System.out.print("Van is not exsist. please enter {maxVolume} to create a van: ");
            String answ = input.nextLine();
            van.createVan(Float.parseFloat(answ));

        }
        System.out.println("To add coffe please eneter [add --{coffeeType(Num)} --{coffee package} --{volume} --{cost}]. " +
                "Please use '--' before params. \n" +
                "Aviable {coffeeName}:\n" +
                "1 - Coffee beans\n" +
                "2 - Coffee ground\n" +
                "3 - Coffee instant\n");
        String answ = input.nextLine();
        String[] consoleString = answ.split("--");
        String command = consoleString[0].trim();
        if(command.equals("add"))
        {
            int coffeeName = Integer.parseInt(consoleString[1].trim());
            String coffeePackage = consoleString[2].trim();
            float volume = Float.parseFloat(consoleString[3].trim());
            float cost = Float.parseFloat(consoleString[4].trim());


            //Van check param


            switch (coffeeName){
                case 1: coffeeBeans.setPackage(coffeePackage);coffeeBeans.loadCoffee(volume, cost);break;
                case 2: coffeeGround.setPackage(coffeePackage);coffeeGround.loadCoffee(volume, cost);break;
                case 3: coffeeInstant.setPackage(coffeePackage);coffeeInstant.loadCoffee(volume, cost);break;
            }

        }else{
            System.out.println("Error in command. Check syntax.");
        }
        while(!answ.equals("exit"))
        {
            System.out.println("Enter command or enter exit to exit");
            String answ = input.nextLine();
            consoleString = answ.split("--");
            command = consoleString[0].trim();
            switch (command){
                case "change": commandsFunctions.change();break;


            }

        }
        /*CoffeeGround coffeeGround = new CoffeeGround("bank");
        coffeeGround.loadCoffee(1f, 256f);*/
       // System.out.println("End");

    }
}
