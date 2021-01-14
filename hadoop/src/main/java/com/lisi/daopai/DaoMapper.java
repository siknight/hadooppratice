package com.lisi.daopai;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.nio.MappedByteBuffer;

public class DaoMapper extends Mapper<LongWritable,Text,Text,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        FileSplit  fs = (FileSplit) context.getInputSplit();
        String name = fs.getPath().getName();
        String[] splits = value.toString().split("\t");
        for (String word : splits){
            context.write(new Text(word),new Text(name+"-->"+1));
        }
    }
}
