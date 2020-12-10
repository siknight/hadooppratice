package com.lisi.flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable,Text,Text,FlowBean> {

    Text text = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        String phone = split[0];
        String upFlow = split[split.length-3];
        String downFlow = split[split.length-2];
        FlowBean flowBean = new FlowBean();
        flowBean.setPhone(phone);
        flowBean.setUpFlow(Long.parseLong(upFlow));
        flowBean.setDownFlow(Long.parseLong(downFlow));
        text.set(phone);
        context.write(text,flowBean);
    }
}
