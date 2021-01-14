package com.lisi.wc03;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class WcMapper extends Mapper<LongWritable,Text,Text,IntWritable> {

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        System.out.println("map init ...");
//        FileSplit inputSplit = (FileSplit) context.getInputSplit();
//        System.out.println("split path="+inputSplit.getPath());
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(" ");
        for (String word : splits){
            context.write(new Text(word),new IntWritable(1));
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        System.out.println("cleanup.......");
    }
}
