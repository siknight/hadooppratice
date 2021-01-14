package com.lisi.flow;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(new Configuration());

        job.setJarByClass(FlowDriver.class);

//        job.setPartitionerClass(PhonePartition.class);
//        job.setNumReduceTasks(6);

        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(FlowBean.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job,new Path("F:\\hadooptest\\phoneinput\\phone.txt"));
        FileOutputFormat.setOutputPath(job,new Path("F:\\hadooptest\\phoneoutput"));

        boolean b = job.waitForCompletion(true);

        System.exit(b?0:1);
    }
}
