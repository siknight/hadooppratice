package com.lisi.wc2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
@SuppressWarnings("all")
public class WordCountReducer extends Reducer<Text,LongWritable,Text,LongWritable> {

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        int sum =0;
        for (LongWritable iw:values){
            sum+=iw.get();
        }
        context.write(key,new LongWritable(sum));
    }
}
