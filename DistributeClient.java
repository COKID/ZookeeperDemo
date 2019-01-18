package ZookeeperDemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistributeClient {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        DistributeClient client = new DistributeClient();

        //获取Zookeeper连接
        client.getConnect();

        //注册监听
        client.getChildren();

        //业务逻辑处理
        client.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/servers", true);
        //存储服务器节点名称
        ArrayList<String> hosts = new ArrayList<>();
        for(String child:children){
            byte[] data = zkClient.getData("/servers/" + child, false, null);

            hosts.add(new String(data));
        }
        //将所有在线主机打印到控制台
        System.out.println(hosts);

    }

    private ZooKeeper zkClient;
    private String connectString="127.0.0.1:2181";
    private int sessionTimeout=2000;
    private Watcher watcher=new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            try {
                getChildren();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    };
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, watcher);
    }

}
