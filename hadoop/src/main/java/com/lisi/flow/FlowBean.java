package com.lisi.flow;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements WritableComparable {
    private String phone;  //手机号

    private long upFlow;  //上行流量

    private long downFlow;  //下行流量

    public FlowBean() {
        super();
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(phone);
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.phone=dataInput.readUTF();
        this.upFlow=dataInput.readLong();
        this.downFlow=dataInput.readLong();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    @Override
    public String toString() {
        return
                 phone + "\t" + upFlow + "\t" + downFlow+"\t";
    }

    @Override
    public int compareTo(Object o) {
        return Math.toIntExact((this.upFlow + this.downFlow) - (((FlowBean)o).getUpFlow() + ((FlowBean)o).getDownFlow()));
    }



}
