package fr.utc.api.test;

import fr.utc.mylottery.rpc.IAPIBooth;
import com.alibaba.fastjson.JSON;
import fr.utc.mylottery.rpc.req.DrawReq;
import fr.utc.mylottery.rpc.req.InitReq;
import fr.utc.mylottery.rpc.res.DrawRes;
import fr.utc.mylottery.rpc.res.InitRes;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
@EnableDubbo
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Reference(interfaceClass = IAPIBooth.class, check = false, version = "1.0.0", url = "dubbo://127.0.0.1:20880")
    private IAPIBooth apiBooth;

    @Test
    public void test_api() {
        DrawReq req = new DrawReq("lichenxin",100001L);
        DrawRes result = apiBooth.doDraw(req);
        System.out.println(JSON.toJSONString(result));
    }
    @Test
    public void test_init(){
        InitReq req = new InitReq(100001L);
        InitRes result = apiBooth.initialize(req);
        System.out.println(JSON.toJSONString(result));
    }



}
