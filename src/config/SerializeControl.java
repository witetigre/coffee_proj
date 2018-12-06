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


            file.append(data);
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

        String data = "volume="+vanVol+"\n";


        // В случае успешной записи в файл функция возвращает true иначе false
        if(this.writeConfigFile(data)){

            return true;

        }else{

            return false;

        }

    }
    public boolean addCoffeeConfig(String coffeeType, String packageType, String vol, String cost){
        /*
         * String coffeeType - Тип кофе
         * String packageType - Тип упаковки
         * String packageType - Обьем в фургоне
         * Функция предназначена для создания файла-описания фургона и записи в него типов кофе с обьемом
         */
        String data = coffeeType+'_'+packageType+'='+vol+'|'+cost+"\n"; //Строка типа Типкофе_упаковка=обьем|цена;

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
            int i = 0;
            //Посимвольно считываем файл настроек
            while((ch = file.read()) != -1)
            {

                resString += (char)ch; // Записываем символы в нашу строку

                if((char)ch == '\n') //Как только дошли конца строки \n переходим на следующую
                {

                    i++;

                    String[] splited = resString.split("="); // Разбираем параметр
                    String key = splited[0].trim();

                    Map value = new HashMap();

                    if(key.equals("volume")) //Если параметр у нас обьем фургона
                    {

                        value.put("volume", Float.parseFloat(splited[1].trim())); //Добавляем в подмасив его значение

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
