# ZookeeperDemo
Zookeeper动态监听服务器上下线
- 本例创建临时有序结点，请根据实际情况修改。
- 代码中的服务端和客户端是相对于实际产品，而这些(所谓的客户端服务端)相对于Zookeeper来说都是客户端
- 我们使用Zookeeper动态监听，使用时请启动Zookeeper服务在节点目录下创建Servers结点。
- 首先启动DistributeClient
- 运行DistributeServer，需要注意的是本例采用从main方法args参数获取服务器编号，运行时请务必添加args参数。
- 可以运行多个DistributeServer添加不同参数
