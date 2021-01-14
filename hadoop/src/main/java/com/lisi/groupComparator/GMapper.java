package com.lisi.groupComparator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GMapper extends Mapper<LongWritable,Text,Order,NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split("\t");
        Order order = new Order();
        order.setOid(splits[0]);
        order.setGid(splits[1]);
        order.setMoney(Double.valueOf(splits[2]));
        context.write(order,NullWritable.get());
    }
}
