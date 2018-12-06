package service;
public abstract class Coffee
{
    public abstract boolean loadCoffee(String packedCoffee); // Должен реализовывать
}
class CoffeeBeans extends Coffee{

  private String coffeeType;
  private String packageType;
  private float vol; // Обьем
  private float cost;

  public boolean loadCoffee(String packageCoffee){

      //Записываем кофе в файл фургона
       return true;
    }
}
class CoffeeGround extends Coffee{

    private String coffeeType;
    private String packageType;
    private float vol; // Обьем
    private float cost;

    public boolean loadCoffee(String packageCoffee){
        return true;
    }
}
class CoffeeInstant extends Coffee{
    private String coffeeType;
    private String packageType;
    private float vol; // Обьем
    private float cost;

    public boolean loadCoffee(String packageCoffee){
        return true;
    }
}