package com.lisi.example;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ZkServer {
    private static String connectString = "hadoop100:2181,hadoop101:2181,hadoop102:2181";
    private static int sessionTimeout = 2000;


    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        String parentNode = "/servers";
        ZooKeeper zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });

        //启动一个后向/servers下面注册一个主机
        String create = zk.create(parentNode + "/server", "hadoop101".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("一个主机上线了");
        Thread.sleep(Long.MAX_VALUE);
    }
}
