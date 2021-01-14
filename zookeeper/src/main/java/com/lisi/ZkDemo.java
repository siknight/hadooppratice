package com.lisi;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * zookeeper demo
 */
public class ZkDemo {
    ZooKeeper zkClient = null;
    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper("hadoop102:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("init..." + watchedEvent.getPath());
                try{
                    zkClient.getChildren("/",true);
                }catch (Exception e){

                }
            }
        });
    }

    @Test
    public void test01() throws KeeperException, InterruptedException {
        zkClient.create("/app1","idea hello".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
    }

    @Test
    public void test02() throws KeeperException, InterruptedException {
        zkClient.setData("/app1","app1 hello".getBytes(),2);
    }

    /**
     * 监听器 ，创建结点..............
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void test03() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children){
            System.out.println("child="+child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

}
