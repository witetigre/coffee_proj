package service;

import service.Van;

public abstract class Coffee
{

    private String coffeeType;
    protected String packageType;


    public Coffee(String coffeeType){
        this.coffeeType = coffeeType;


    }
    public void setPackage(String packageType){
        this.packageType = packageType;
    }
    public abstract boolean loadCoffee(float vol, float cost); // Должен реализовывать загрузку именно это кофе в фургон

    protected String packCoffee()
    {
        return this.coffeeType+" "+this.packageType;
    }
}
class CoffeeBeans extends Coffee{
    public CoffeeBeans(){
        super("Coffee beans");
    }

    public boolean loadCoffee(float vol, float cost){

        String packedCofee = this.packCoffee();
        Van van = new Van();
        van.addCoffee(packedCofee, vol, cost);
        return true;
    }
}
class CoffeeGround extends Coffee{



    public CoffeeGround(){

        super("Coffee ground");
    }



    public boolean loadCoffee(float vol, float cost){

        String packedCofee = this.packCoffee();
        Van van = new Van();
        van.addCoffee(packedCofee, vol, cost);
        return true;
    }
}
class CoffeeInstant extends Coffee{



    public CoffeeInstant(){

        super("Coffee instant");

    }

    public boolean loadCoffee(float vol, float cost){

        String packedCofee = this.packCoffee();
        Van van = new Van();
        van.addCoffee(packedCofee, vol, cost);
        return true;
    }
}