package com.lisi.daopai;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DaoReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        HashMap<String, Integer> map = new HashMap<>();
        for(Text text : values){
            String s = text.toString();
            String[] splits = s.split("-->");
            String fileName = splits[0];
            int count = Integer.parseInt(splits[1]);
            if(!map.containsKey(fileName)){
                map.put(fileName,count);
            }else{
                int mapcount = map.get(fileName);
                map.put(fileName,count+mapcount);
            }
        }
//        new StringBuffer()
        for (Map.Entry<String, Integer> entry : map.entrySet()){

        }
    }
}
