package com.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsClient {

    /**
     * 创建文件
     */
    @Test
    public void testMkdir(){
        Configuration configuration = new Configuration();
        configuration.setStrings("fs.defaultFS","hdfs://hadoop100:9000");
        System.setProperty("HADOOP_USER_NAME","lisi");
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
//            fileSystem.mkdirs(new Path("/user/lisi/ideaInput"));
            fileSystem.delete(new Path("/user/lisi/test/文件上传的细节处理.mp4"),true);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileSystem.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("创建成功");
    }

    /**
     * 上传文件
     */
    @Test
    public void testCopyFromLocalFile(){
        try {
            FileSystem fs = FileSystem.get(new URI("hdfs://hadoop100:9000"), new Configuration(), "lisi");
            fs.copyFromLocalFile(new Path("E:/王梁老师讲解视频/day02/文件上传的细节处理.mp4"),new Path("/user/lisi/test/"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println("创建成功");
    }



}
