package com.lisi.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PhonePartition extends Partitioner<Text,FlowBean> {

    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        String phoneto3 = text.toString().substring(0, 3);
        int partition =0;
        if("136".equals(phoneto3)){
           partition = 4;
        }else if("137".equals(phoneto3)){
            partition = 1;
        }else if("138".equals(phoneto3)){
            partition = 2;
        }else if("139".equals(phoneto3)){
            partition = 3;
        }
        return partition;
    }
}
