import config.SerializeControl;
import java.util.Map;
public class Main {
    public static void main(String[] args){

        SerializeControl ser = new SerializeControl();
        Map test = ser.loadConfig();
        System.out.println("Hellow Coffee");

    }
}
