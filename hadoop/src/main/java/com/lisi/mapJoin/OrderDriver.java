package com.lisi.mapJoin;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
//atguigu---->a.txt--1   atguigu----->b.txt-->1  atguigu---->a.txt--->
// split()  a.txt  1     b.txt--1
@SuppressWarnings("all")
public class OrderDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(OrderDriver.class);
        job.setMapperClass(JoinMapper.class);

        job.setOutputValueClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path("F:\\hadooptest\\ordergoodsinput\\order.txt"));
        FileOutputFormat.setOutputPath(job,new Path("F:\\hadooptest\\ordergoodsoutput02"));
        job.addCacheFile(new URI("file:/F:/hadooptest/ordergoodsinput/pd.txt"));
        job.setNumReduceTasks(0);
        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}
