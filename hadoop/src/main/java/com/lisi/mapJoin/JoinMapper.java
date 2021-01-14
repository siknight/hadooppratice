package com.lisi.mapJoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

public class JoinMapper extends Mapper<LongWritable,Text,Text,NullWritable> {

    HashMap pdmap = new HashMap<String,String>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        // 1 获取缓存的文件
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(cacheFiles[0].getPath()),"UTF-8"));
        String line;
        while (StringUtils.isNotEmpty(line = reader.readLine())){
            String[] splits = line.split("\t");
            pdmap.put(splits[0], splits[1]);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        String pid = split[1];
        if ("1001".equals(split[0])){
            context.getCounter("order","1001").increment(1);
        }else{
            context.getCounter("order","23456").increment(1);
        }
        String line = split[0]+"\t"+pdmap.get(pid)+"\t"+split[2];
        context.write(new Text(line),NullWritable.get());
    }
}
