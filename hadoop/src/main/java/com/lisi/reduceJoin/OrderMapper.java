package com.lisi.reduceJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable,Text,Text,OrderGoods> {
    Text key2= new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        FileSplit fileSplit =(FileSplit) context.getInputSplit();
        String name = fileSplit.getPath().getName();
        OrderGoods orderGoods = new OrderGoods();
        if (name.contains("order")){
            String[] splits = value.toString().split("\t");
            String id =splits[0];
            String pid =splits[1];
            int amount = Integer.parseInt(splits[2]);
            orderGoods.setId(id);
            orderGoods.setPid(pid);
            String ppppid = orderGoods.getPid();
            orderGoods.setAmount(amount);
            orderGoods.setPname("");
            key2.set(pid);
//            context.write(new Text(pid),orderGoods);
        }else if(name.contains("pd")){
            String[] splits = value.toString().split("\t");
            String  pid = splits[0];
            String pname = splits[1];
            orderGoods.setPid(pid);
            orderGoods.setPname(pname);
            orderGoods.setId("");
            orderGoods.setAmount(0);
            key2.set(pid);
//            context.write(new Text(pid),orderGoods);
        }
        context.write(key2,orderGoods);
    }
}
