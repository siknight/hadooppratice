package com.lisi.reduceJoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class OrderReducer extends Reducer<Text,OrderGoods,OrderGoods,NullWritable> {

    public static void main(String[] args) {
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setId("1111");
        OrderGoods orderGoods1 = new OrderGoods();
        orderGoods1 = orderGoods;
        System.out.println("og="+orderGoods.hashCode());
        System.out.println("og2="+orderGoods.hashCode());

    }
    @Override
    protected void reduce(Text key, Iterable<OrderGoods> values, Context context) throws IOException, InterruptedException {
        OrderGoods orderGoods1 = new OrderGoods();  //存储商品
        ArrayList<OrderGoods> orderGoodslist= new ArrayList<>();  //订单集合


        for (OrderGoods orderGoods : values){  //订单可能有多个，但是商品表只有一个
                System.out.println("or==="+orderGoods.hashCode());
                if("".equals(orderGoods.getPname())){

                    try {
                        OrderGoods orderGoods5= new OrderGoods();
                        BeanUtils.copyProperties(orderGoods5,orderGoods);
                        orderGoodslist.add(orderGoods5);   //添加到订单集合
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }else {
//                    orderGoods1 = orderGoods;
                    try {
                        BeanUtils.copyProperties(orderGoods1,orderGoods);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
        }

        for(OrderGoods og : orderGoodslist){
            System.out.println("name="+orderGoods1.getPname());
            og.setPname(orderGoods1.getPname());
            System.out.println(og.getPname());
            context.write(og,NullWritable.get());
        }
    }

}
