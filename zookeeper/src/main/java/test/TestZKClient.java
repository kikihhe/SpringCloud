package test;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-05-14 20:19
 */
public class TestZKClient {

    private ZkClient zkClient;

    @Before
    public void before() {
        String client = "47.109.60.208:2181";
        int sessionTimeOut = 60000 * 30;
        int connectionTimeOut = 6000;
         zkClient = new ZkClient(client, sessionTimeOut, connectionTimeOut, new SerializableSerializer());
    }

    /**
     * 创建节点
     */
    @Test
    public void createNode() {
        String path = "/node1";
        String data = "xiaohe";
        CreateMode type = CreateMode.PERSISTENT;
        // 创建一个持久节点
        zkClient.create(path, data, type);

        // 创建一个持久顺序节点
        zkClient.create(path, "zhansan", CreateMode.EPHEMERAL_SEQUENTIAL);

        // 创建一个临时节点
        zkClient.create(path, "lisi", CreateMode.EPHEMERAL);

        // 创建一个临时顺序节点
        zkClient.create(path, "xiaoming", CreateMode.EPHEMERAL_SEQUENTIAL);


        int count = zkClient.countChildren(path);
        System.out.println(count);
    }



    @After
    public void after() {
        zkClient.close();
    }

}
