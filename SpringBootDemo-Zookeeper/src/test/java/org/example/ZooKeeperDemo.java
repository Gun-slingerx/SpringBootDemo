package org.example;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 简单测试示例
 *
 * @author 猿人谷 * @date 2019/12/16
 */
public class ZooKeeperDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperDemo.class);

    private static final int SESSION_TIME_OUT = 10000;
    // ZooKeeper服务的地址，如为集群，多个地址用逗号分隔
    private static final String CONNECT_STRING = "119.45.177.114:2181";
    private static final String ZNODE_PATH = "/zk_demo";
    private static final String ZNODE_PATH_PARENT = "/app1";
    private static final String ZNODE_PATH_CHILDREN = "/app1/app1_1";

    private ZooKeeper zk = null;

    @Before
    public void init() throws IOException {
        zk = new ZooKeeper(CONNECT_STRING, SESSION_TIME_OUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("已经触发了" + event.getType() + "事件！");
            }
        });

    }

    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        zk.create(ZNODE_PATH, "anna2019".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void testCreateParentZnode() throws KeeperException, InterruptedException {
        zk.create(ZNODE_PATH_PARENT, "anna2019".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void testCreateChildrenZnode() throws KeeperException, InterruptedException {
        zk.create(ZNODE_PATH_CHILDREN, "anna2020".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void testGet() throws KeeperException, InterruptedException {
        byte[] data1 = zk.getData(ZNODE_PATH, false, null);
        byte[] data2 = zk.getData(ZNODE_PATH_PARENT, false, null);
        byte[] data3 = zk.getData(ZNODE_PATH_CHILDREN, false, null);
        LOGGER.debug("{}的信息：{}", ZNODE_PATH, new String(data1));
        LOGGER.debug("{}的信息：{}", ZNODE_PATH_PARENT, new String(data2));
        LOGGER.debug("{}的信息：{}", ZNODE_PATH_CHILDREN, new String(data3));
    }

    /**
     * 删除
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void testDelete() throws KeeperException, InterruptedException {
        // 指定要删除的版本，-1表示删除所有版本
        zk.delete(ZNODE_PATH, -1);
    }

    /**
     * 删除含有子节点
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void testDeleteHasChildrenZnode() throws KeeperException, InterruptedException {
        // 指定要删除的版本，-1表示删除所有版本
        zk.delete(ZNODE_PATH_PARENT, -1);
    }

    @Test
    public void testSet() throws KeeperException, InterruptedException {
        Stat stat = zk.setData(ZNODE_PATH, "yuanrengu".getBytes(), -1);
        LOGGER.info(stat.toString());
    }

}
