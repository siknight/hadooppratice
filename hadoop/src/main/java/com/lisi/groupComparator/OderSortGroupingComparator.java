package com.lisi.groupComparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OderSortGroupingComparator extends WritableComparator {
    public OderSortGroupingComparator() {
        super(Order.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Order o1 = (Order)a;
        Order o2 = (Order)b;
        int order = o1.getOid().compareTo(((Order) b).getOid());
        return  order;
    }
}
