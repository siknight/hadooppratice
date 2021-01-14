package com.lisi.groupComparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class Order implements WritableComparable<Order> {
    private String oid;
    private String gid;
    private Double money;


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public static void main(String[] args) {
        String a="0001";
        String b = "0002";
        System.out.println(a.compareTo(b));
        int compare = Double.compare(1, 2);
        System.out.println(compare);
    }

    @Override
    public int compareTo(Order o) {
        int i = this.oid.compareTo(o.oid);
        if(i==0){
            i = Double.compare(o.money,this.money);
        }
        return  i;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(oid);
        dataOutput.writeUTF(gid);
        dataOutput.writeDouble(money);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.oid = dataInput.readUTF();
        this.gid = dataInput.readUTF();
        this.money = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return   oid + '\t' +gid+"\t"+money;

    }
}
