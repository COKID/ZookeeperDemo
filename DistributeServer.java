package ZookeeperDemo;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DistributeServer {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeServer server=new DistributeServer();
        //连接Zookeeper
        server.getConnect();

        //服务器去Zookeeper注册
        server.regist(args[0]);

        //业务逻辑处理
        server.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void regist(String hostname) throws KeeperException, InterruptedException {
        String path = zkClient.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname+" is online");
    }

    private ZooKeeper zkClient;
    private String connectString="127.0.0.1:2181";
    private int sessionTimeout=2000;
    private Watcher watcher=new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {

        }
    };
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, watcher);
    }
}
