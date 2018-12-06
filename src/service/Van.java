package service;

import config.SerializeControl;

import java.util.Map;

public class Van {

    public void setVanVolume(float volume)
    {
        SerializeControl serilalize = new SerializeControl();

            Map<String, Map> config = serilalize.loadConfig();
            var currentVolume = config.get("volume");
            if(currentVolume == null){

                serilalize.addVanConfig(volume);

            }else{

                System.out.println("Volume already set, please change it in file van.opt");

            }




    }
    public void addCoffee(String coffeeTypePacked, float vol, float cost)
    {
        SerializeControl serilalize = new SerializeControl();
        Map<String, Map> config = serilalize.loadConfig();
        float volume = Float.parseFloat(config.get("volume").get("volume").toString());
        float fillVolume = Float.parseFloat(config.get("volume").get("fillVolume").toString());
        fillVolume += vol;
        if(volume <= fillVolume){
            System.out.println("Can't load, van is filled ["+fillVolume+"/"+volume+"](filled volume/volume)");
        }else {


            config.get("volume").put("fillVolume", fillVolume);
            config.get(coffeeTypePacked).put("volume", vol);
            serilalize.changeOption(coffeeTypePacked, vol+"|"+cost);
            System.out.println("Coffee vas loaded ["+fillVolume+"/"+volume+"](filled volume/volume)");

        }
    }

}
