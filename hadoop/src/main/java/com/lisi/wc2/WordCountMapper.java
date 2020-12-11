package com.lisi.wc2;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
@SuppressWarnings("all")
public class WordCountMapper extends Mapper<Text,Text,Text,LongWritable> {

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        long v = Long.parseLong(value.toString());
        context.write(key,new LongWritable(v));
    }
}
