import config.SerializeControl;


import java.sql.Array;
import java.util.Map;

public class Main {
    public static void main(String[] args){

        SerializeControl ser = new SerializeControl();
        Map<String, Map> test = ser.loadConfig();
        var price = test.get("coffeebeans_package2").get("volume");

        System.out.println(price);

    }
}
