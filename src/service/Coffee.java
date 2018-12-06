package service;

import service.Van;
public abstract class Coffee
{

    private String coffeeType = "coffee beans";
    private String packageType;


    public Coffee(String coffeeType, String packageType){
        this.coffeeType = coffeeType;
        this.packageType = packageType;

    }

    public abstract boolean loadCoffee(float vol, float cost); // Должен реализовывать загрузку именно это кофе в фургон

    protected String packCoffee()
    {
        return this.coffeeType+" "+this.packageType;
    }
}
class CoffeeBeans extends Coffee{
    public CoffeeBeans(String packageType){
        super("Coffee ground", packageType);
    }

  public boolean loadCoffee(float vol, float cost){

      //Записываем кофе в файл фургона
       return true;
    }
}
class CoffeeGround extends Coffee{



    public CoffeeGround(String packageType){

        super("Coffee ground", packageType);
    }



    public boolean loadCoffee(float vol, float cost){

        String packedCofee = this.packCoffee();
        Van van = new Van();
        van.addCoffee(packedCofee, vol, cost);
        return true;
    }
}
class CoffeeInstant extends Coffee{



    public CoffeeInstant(String packageType){

        super("Coffee instant", packageType);

    }

    public boolean loadCoffee(float vol, float cost){
        return true;
    }
}