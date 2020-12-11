package com.lisi.flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text,FlowBean,FlowBean,LongWritable> {
    LongWritable sumTotal = new LongWritable();
    FlowBean flowBean = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long sumUpFlow = Long.valueOf(0);
        long sumDownFlow = Long.valueOf(0);
        long sumTotalFlow ;
        for(FlowBean flowBean2 : values){
            sumUpFlow += flowBean2.getUpFlow();
            sumDownFlow += flowBean2.getDownFlow();
        }
        sumTotalFlow = sumUpFlow + sumDownFlow;
        flowBean.setUpFlow(sumUpFlow);
        flowBean.setDownFlow(sumDownFlow);
        flowBean.setPhone(key.toString());
        sumTotal.set(sumTotalFlow);
        context.write(flowBean,sumTotal);
    }
}
