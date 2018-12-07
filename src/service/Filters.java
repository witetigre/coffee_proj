package service;

import config.SerializeControl;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.Comparator;
import java.util.stream.Stream;

public class Filters {

    public <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        Map<K,V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K,V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e ->result.put(e.getKey(),e.getValue()));

        return result;
    }
    public void sortBy(String sortParam){

            SerializeControl serial = new SerializeControl();
            Map<String, Map> config = serial.loadConfig();
            config.remove("error");
            Iterator it = config.entrySet().iterator();
            String resultString = "";
            Map<String, Float> mapSort = new HashMap<>();
            while (it.hasNext())
            {
                Map.Entry<String, Map> pair = (Map.Entry)it.next();
                String key = pair.getKey();
                Map param = pair.getValue();
                String volume = param.get("volume").toString();
                if(!key.equals("volume")) {

                    resultString = pair.getKey() + " Volume = " + param.get("volume")+"| Price = "+param.get("price")+'\n';
                    if(sortParam.equals("cost")) {
                        mapSort.put(resultString, Float.parseFloat(param.get("price").toString()));
                    }
                    if(sortParam.equals("vol")) {
                        mapSort.put(resultString, Float.parseFloat(param.get("volume").toString()));
                    }


                }
                it.remove();

            }
            Map result = this.sortByValue(mapSort);
            Iterator it_result = result.entrySet().iterator();
            int pos = 0;
            while (it_result.hasNext())
            {
                pos++;

                Map.Entry<String, Map> pair = (Map.Entry)it_result.next();
                String key = pair.getKey();
                System.out.print(pos+") "+key);

                it_result.remove();
            }






    }
    public void sortRange(String sortParam, float[] range)
    {
        SerializeControl serial = new SerializeControl();
        Map<String, Map> config = serial.loadConfig();
        config.remove("error");
        Iterator it = config.entrySet().iterator();
        String resultString = "";
        Map<String, Float> mapSort = new HashMap<>();
        while (it.hasNext())
        {
            Map.Entry<String, Map> pair = (Map.Entry)it.next();
            String key = pair.getKey();
            Map param = pair.getValue();
            String volume = param.get("volume").toString();
            if(!key.equals("volume")) {

                resultString = pair.getKey() + " Volume = " + param.get("volume")+"| Price = "+param.get("price")+'\n';
                if(sortParam.equals("cost")) {
                    if(Float.parseFloat(param.get("price").toString()) >= range[0] &&
                            range[1] >=  Float.parseFloat(param.get("price").toString())) {
                        mapSort.put(resultString, Float.parseFloat(param.get("price").toString()));
                    }
                }
                if(sortParam.equals("vol")) {
                    if(Float.parseFloat(param.get("vol").toString()) >= range[0] &&
                            range[1] >=  Float.parseFloat(param.get("vol").toString())) {
                        mapSort.put(resultString, Float.parseFloat(param.get("vol").toString()));
                    }
                }


            }
            it.remove();

        }
        Map result = this.sortByValue(mapSort);
        Iterator it_result = result.entrySet().iterator();
        int pos = 0;
        while (it_result.hasNext())
        {
            pos++;

            Map.Entry<String, Map> pair = (Map.Entry)it_result.next();
            String key = pair.getKey();
            System.out.print(pos+") "+key);

            it_result.remove();
        }


        //String[] resultStringArr = resultString.split("\n");
    }
}
