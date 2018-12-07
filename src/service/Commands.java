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
        System.out.println("1) Command entry example:\n" +
                "[add --{coffeeType(Num)} --{coffee package} --{volume} --{cost}]\n" +
                "2) add --1 --pack --100 --2500\n" +
                "aviable commands:\n" +
                "add --{coffeeType(Num)} --{coffee package(String)} --{volume} --{cost} => add coffe to the van\n" +
                "show => Show all coffee without sort\n" +
                "change --{coffeeType(String)} --{coffee package(String)} --{volume} --{cost} => change coffe in the van\n" +
                "filterBy --{filterParam(String)} => filterParam can be [vol] or [cost]\n" +
                "range --{filterParam(String)} --{from|to} => range filter with sort. FilterParam can be [vol] or [cost]\n" +
                "from - is minimum value, to - is maximum value\n" +
                "3) Example range filter:\n" +
                "range --vol --100|400");
    }
}
