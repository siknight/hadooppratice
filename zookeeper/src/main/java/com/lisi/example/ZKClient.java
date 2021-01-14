package com.lisi.example;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZKClient {

    private static String connectString = "hadoop100:2181,hadoop101:2181,hadoop102:2181";
    private static int sessionTimeout = 2000;
    private static  ZooKeeper zk =null;
    public static void main(String[] args) throws IOException, InterruptedException {
        String parentNode = "/servers";
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                try {
                    List<String> children = zk.getChildren(parentNode , true);
                    ArrayList<String> add = new ArrayList<>();
                    for(String child : children){
                        add.add(new String(zk.getData(parentNode + "/"+child,false,null)));
                    }
                    System.out.println(add);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("client is working.........");
        Thread.sleep(Long.MAX_VALUE);
    }
}
