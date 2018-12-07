/*

 *SerializeControl

 *

 *Coffee Alfa 1.0

 *

 *Author name here

 */
package config;


import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.io.*;

public class SerializeControl
{
    private String fileName = "van.opt";

    public void setFileName(String fileName)
    {
        // Меняем имя файла при желании
        this.fileName = fileName;
    }
    private boolean writeConfigFile(String data){
        /*
         * String data - Строка содержащая данные для записи в файл
         * Функция принимает параметр String для записи в файл и
         */


        try(FileWriter file = new FileWriter(this.fileName, true)){


            file.append(data);
            file.flush();
            return true;

        }catch(Exception e){

            return false;

        }
    }
    private boolean writeConfigFile(String data, boolean rewrite){
        /*
         * String data - Строка содержащая данные для записи в файл
         * Функция принимает параметр String для записи в файл и
         */


        try(FileWriter file = new FileWriter(this.fileName, false)){


            file.write(data);
            file.flush();
            return true;

        }catch(Exception e){

            return false;

        }
    }

    public void changeOption(String optionName, String value)
    {

        String resultString = "";

        Map<String, Map> config = this.loadConfig();
        if(optionName.equals("volume"))
        {

            config.get(optionName).put("volume", config.get(optionName).get("volume")); //0 - max value 1 - filled
            config.get(optionName).put("fillVolume", value); //0 - max value 1 - filled


        }else{
            String[] valueArr = value.split("\\|");

            config.get(optionName).put("volume", valueArr[0]);
            config.get(optionName).put("price", valueArr[1]);
            //Записываем изменившийся обьем

            this.calcVolume();

        }

        config.remove("error");
        Iterator it = config.entrySet().iterator();

        while (it.hasNext())
        {
            Map.Entry<String, Map> pair = (Map.Entry)it.next();
            String key = pair.getKey();
            Map param = pair.getValue();
            String volume = param.get("volume").toString();
            if(key.equals("volume")) {

               resultString += pair.getKey() + "=" + param.get("volume")+'|'+param.get("fillVolume")+'\n';

            }else{

                resultString += pair.getKey() + "=" + param.get("volume")+"|"+param.get("price")+'\n';

            }
            it.remove();

        }


        this.writeConfigFile("", true);
        this.writeConfigFile(resultString);


    }

    public boolean addVanConfig(float vanVol){
        /*
        * String vanVol - Параметр обьема
        * Функция предназначена для создания файла-описания фургона и записи в него обьема vanVol
        */

        // Что бы не заморачиватся сделал ввод через строку базовые параметры тут.
        // Но желателно чере Map вгонять сюда данные, хоть и пробегаемся циклом по нему, зато так код становится более
        // понятным. Если нужно будет подкоректирую это.

        String data = "volume="+vanVol+"|0\n";


        // В случае успешной записи в файл функция возвращает true иначе false
        if(this.writeConfigFile(data)){

            return true;

        }else{

            return false;

        }

    }
    public void calcVolume()
    {
        Map<String, Map> config = this.loadConfig();
        String volume = config.get("volume").get("volume").toString();
        config.remove("error");
        Iterator it = config.entrySet().iterator();
        float filledVolume = 0;
        while (it.hasNext())
        {
            Map.Entry<String, Map> pair = (Map.Entry)it.next();
            String key = pair.getKey();
            Map param = pair.getValue();

            if(!key.equals("volume")) {

                filledVolume += Float.parseFloat(param.get("volume").toString());

            }
            it.remove();

        }
       //String[] volumeArr = volume.split("\\|");

        this.changeOption("volume", Float.toString(filledVolume));
    }
    public boolean addCoffeeConfig(String coffeeType, String vol, String cost){
        /*
         * String coffeeType - Тип кофе
         * String packageType - Тип упаковки
         * String packageType - Обьем в фургоне
         * Функция предназначена для создания файла-описания фургона и записи в него типов кофе с обьемом, пересчет обьема
         */
        String data = coffeeType+'='+vol+'|'+cost+"\n"; //Строка типа Типкофе_упаковка=обьем|цена;

        this.calcVolume();

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


        try(FileReader file = new FileReader(this.fileName)){


            int ch = 0;
            //Посимвольно считываем файл настроек
            while((ch = file.read()) != -1)
            {

                resString += (char)ch; // Записываем символы в нашу строку

                if((char)ch == '\n' && !resString.equals("\n")) //Как только дошли конца строки \n переходим на следующую
                {



                    String[] splited = resString.split("="); // Разбираем параметр
                    String key = splited[0].trim();

                    Map value = new HashMap();

                    if(key.equals("volume")) //Если параметр у нас обьем фургона
                    {
                        String[] vanData = splited[1].split("\\|");
                        value.put("volume", Float.parseFloat(vanData[0].trim())); //Max value
                        value.put("fillVolume", Float.parseFloat(vanData[1].trim()));
                    }else { //Иначе (тип кофе, пачка и цена)

                        String[] coffeeData = splited[1].split("\\|");
                        value.put("volume", Float.parseFloat(coffeeData[0].trim()));// Заполняем соответствующие поля подмасива
                        value.put("price", Float.parseFloat(coffeeData[1].trim()));// Заполняем соответствующие поля подмасива
                    }
                    result.put(key, value); //Добавляем подмасив в основной масив
                    resString = "";
                }
            }



            return result;

        }catch (IOException e){

            result.put("error", e.getMessage()); //Заменить на код ошибки

        }


        return result;
    }

}
