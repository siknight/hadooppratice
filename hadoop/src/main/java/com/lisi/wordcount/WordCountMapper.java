package com.lisi.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
//        FileSplit fileSplit= (FileSplit)context.getInputSplit();
//        String name = fileSplit.getPath().getName();
//        System.out.println("name="+name);
        System.out.println("start...........");

    }

    Text text = new Text();
    IntWritable intWritable = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("11111111111");
        String[] splits = value.toString().split(" ");
        for(String word : splits){
            text.set(word);
            context.write(text,intWritable);
        }
    }
}
