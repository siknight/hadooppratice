package com.lisi.groupComparator;


import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class GReducer extends Reducer<Order,NullWritable,Order,NullWritable> {
    @Override
    protected void reduce(Order key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key,NullWritable.get());
    }
}
