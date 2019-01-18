package ZookeeperDemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZookeeperTest {

    private ZooKeeper zkClient;
    private String connectString="127.0.0.1:2181";
    private int sessionTimeout=2000;
    private Watcher watcher=new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            List<String> children = null;//获取路径下所有子节点并监控
            System.out.println("-------------start--------------");
            try {
                children = zkClient.getChildren("/", true);
                for (String child:children) {
                    System.out.println(child);
                }
            System.out.println("-------------end--------------");
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

    @Before//初始化连接
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, watcher);

    }

    @Test//创建结点
    public void createNode() throws KeeperException, InterruptedException {
        String path = zkClient.create("/cokid", "system".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }

    @Test//获取子节点，并监控数据的变化
    public void getDataAndWatch() throws KeeperException, InterruptedException {

        Thread.sleep(Long.MAX_VALUE);//让主线程休息不要结束，让监听器线程可以监听到变化
    }

    @Test//判断节点是否存在
    public void isExist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/cokid", false);
        System.out.println(stat==null?"not exist":"exist");
    }

}
