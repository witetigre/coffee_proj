/*

 *SerializeControl

 *

 *Coffee Alfa 1.0

 *

 *Author name here

 */
package config;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class SerializeControl
{
    private String fileName = "van.opt";

    public void setFileName(String fileName){
        // Меняем имя файла при желании
        this.fileName = fileName;
    }
    private boolean writeConfigFile(String data){
        /*
         * String data - Строка содержащая данные для записи в файл
         * Функция принимает параметр String для записи в файл и
         */
        File fileCheck = new File(this.fileName);
        if(fileCheck.exists()){
            return false;
        }
        try(FileWriter file = new FileWriter(fileName, false)){


            file.write(data);
            file.flush();
            return true;

        }catch(Exception e){

            return false;

        }}

    public boolean addVanConfig(String vanVol){
        /*
        * String vanVol - Параметр обьема
        * Функция предназначена для создания файла-описания фургона и записи в него обьема vanVol
        */

        // Что бы не заморачиватся сделал ввод через строку базовые параметры тут.
        // Но желателно чере Map вгонять сюда данные, хоть и пробегаемся циклом по нему, зато так код становится более
        // понятным. Если нужно будет подкоректирую это.

        String data = "--Van Settings--\n"+
                      "volume="+vanVol+";\n"+
                      "--Coffee--\n";

        // В случае успешной записи в файл функция возвращает true иначе false
        if(this.writeConfigFile(data)){

            return true;

        }else{

            return false;

        }

    }
    public boolean addCoffeeConfig(String coffeeType, String packageType, String vol){
        /*
         * String coffeeType - Тип кофе
         * String packageType - Тип упаковки
         * String packageType - Обьем в фургоне
         * Функция предназначена для создания файла-описания фургона и записи в него типов кофе с обьемом
         */
        String data = coffeeType+'_'+packageType+'='+vol+";\n";

        // В случае успешной записи в файл функция возвращает true иначе false
        if(this.writeConfigFile(data)){

            return true;

        }else {

            return false;

        }
    }
    public Map loadConfig(){
        Map result = new HashMap();
        String resString = "";
        result.put("error", 0);
        result.put("data", "none");

        try(FileReader file = new FileReader(this.fileName)){


            int ch = 0;
            while((ch = file.read()) != -1){

                resString += (char)ch;

            }


            result.put("data", resString);
            return result;

        }catch (Exception e){

            result.put("error", 1);

        }


        return result;
    }

}
