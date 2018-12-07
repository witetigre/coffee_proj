package service;

public class Commands
{
    public void change(String packedCoffee, float vol, float cost)
    {
        Van van = new Van();
        van.changeCoffe(packedCoffee, vol, cost);
    }

    public void showVan(){
        Van van = new Van();
        van.show();
    }

    public void filterBy(String filterParam){
        Filters filtr = new Filters();
        filtr.sortBy(filterParam);
    }
    public void range(String filterParam, float[] range){
        Filters filtr = new Filters();
        filtr.sortRange(filterParam, range);
    }
    public void help(){

    }
}
