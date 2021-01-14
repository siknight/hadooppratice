package com.lisi.groupComparator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class GDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(GDriver.class);

        job.setMapperClass(GMapper.class);
        job.setReducerClass(GReducer.class);

        job.setMapOutputKeyClass(Order.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Order.class);
        job.setOutputValueClass(NullWritable.class);

        job.setGroupingComparatorClass(OderSortGroupingComparator.class);

        FileInputFormat.setInputPaths(job,new Path("F:\\hadooptest\\groupinput"));
        FileOutputFormat.setOutputPath(job,new Path("F:\\hadooptest\\groupoutput2"));

        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}
