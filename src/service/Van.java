package service;

import config.SerializeControl;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class Van
{

    public boolean checkVan(){
        File file = new File("van.opt");

        return file.exists();
    }
    public void show(){
        SerializeControl serial = new SerializeControl();
        Map<String, Map> config = serial.loadConfig();
        config.remove("error");
        Iterator it = config.entrySet().iterator();

        while (it.hasNext())
        {
            Map.Entry<String, Map> pair = (Map.Entry)it.next();
            String key = pair.getKey();
            Map param = pair.getValue();
            String volume = param.get("volume").toString();
            if(key.equals("volume")) {

                System.out.println(pair.getKey() + " Volume = " + param.get("volume")+" | Filled volume = "+param.get("fillVolume")+'\n');

            }else{

                System.out.println(pair.getKey() + " Volume = " + param.get("volume")+"| Price = "+param.get("price")+'\n');

            }
            it.remove();

        }

    }
    public void createVan(float vol){
        SerializeControl serilalize = new SerializeControl();
        serilalize.addVanConfig(vol);
        System.out.println("Van succesfuly created!");

    }
    public void setVanVolume(float volume)
    {
        SerializeControl serilalize = new SerializeControl();

            Map<String, Map> config = serilalize.loadConfig();
            var currentVolume = config.get("volume");
            if(currentVolume == null){

                serilalize.addVanConfig(volume);

            }else{

                serilalize.changeOption("volume", Float.toString(volume)+"|0\n");
                serilalize.calcVolume();
            }




    }
    public boolean checkCoffee(String coffeePackName){
        SerializeControl serilalize = new SerializeControl();
        Map<String, Map> config = serilalize.loadConfig();
        var result = config.get(coffeePackName);
        if(result == null){
            return false;
        }else{
            return true;
        }
    }
    public void addCoffee(String coffeeTypePacked, float vol, float cost)
    {


        SerializeControl serilalize = new SerializeControl();



        Map<String, Map> config = serilalize.loadConfig();

        float volume = Float.parseFloat(config.get("volume").get("volume").toString());

        float fillVolume = Float.parseFloat(config.get("volume").get("fillVolume").toString());

        fillVolume += vol;

        if(fillVolume > volume){

            System.out.println("Can't load, van is filled ["+fillVolume+"/"+volume+"](filled volume/volume)");

        }else {

            if(!this.checkCoffee(coffeeTypePacked)){
                serilalize.addCoffeeConfig(coffeeTypePacked, Float.toString(vol), Float.toString(cost));
                serilalize.calcVolume();
                Map<String, Map> configNew = serilalize.loadConfig();
                float fillVolumeTotal = Float.parseFloat(configNew.get("volume").get("fillVolume").toString());
                System.out.println("Coffee vas loaded [" + fillVolumeTotal + "/" + volume + "](filled volume/volume)");
            }else {



                config.get(coffeeTypePacked).put("volume", vol);


                serilalize.changeOption(coffeeTypePacked, vol + "|" + cost);
                serilalize.calcVolume();
                Map<String, Map> configNew = serilalize.loadConfig();
                float fillVolumeTotal = Float.parseFloat(configNew.get("volume").get("fillVolume").toString());
                System.out.println("Coffee vas loaded [" + fillVolumeTotal + "/" + volume + "](filled volume/volume)");
            }

        }
    }
    public void changeCoffe(String coffeeTypePacked, float vol, float cost)
    {


        SerializeControl serilalize = new SerializeControl();



        Map<String, Map> config = serilalize.loadConfig();

        float volume = Float.parseFloat(config.get("volume").get("volume").toString());

        float fillVolume = Float.parseFloat(config.get("volume").get("fillVolume").toString());

        fillVolume += vol;

        if(fillVolume > volume){

            System.out.println("Can't load, van is filled ["+fillVolume+"/"+volume+"](filled volume/volume)");

        }else {

            if(!this.checkCoffee(coffeeTypePacked)){

                System.out.println("This coffee is not found. Use {add} to add coffee to van.");
            }else {



                config.get(coffeeTypePacked).put("volume", vol);


                serilalize.changeOption(coffeeTypePacked, vol + "|" + cost);
                serilalize.calcVolume();
                Map<String, Map> configNew = serilalize.loadConfig();
                float fillVolumeTotal = Float.parseFloat(configNew.get("volume").get("fillVolume").toString());
                System.out.println("Coffee vas changed [" + fillVolumeTotal + "/" + volume + "](filled volume/volume)");
            }

        }
    }

}
