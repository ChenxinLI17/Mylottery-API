package fr.utc.api.test;

import com.alibaba.fastjson.JSON;
import fr.utc.mylottery.rpc.ILotBooth;
import fr.utc.mylottery.rpc.req.LotReq;
import fr.utc.mylottery.rpc.res.LotRes;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableDubbo
public class LotTest{
    @Reference(interfaceClass = ILotBooth.class, check = false,version = "1.0.0", url = "dubbo://127.0.0.1:20880")
    private ILotBooth lotBooth;

    //success
    @Test
    public void test_lot(){
        LotReq req = new LotReq();
        req.setStrategyId(10001L);
        req.setUserId("lcx");
        LotRes res = lotBooth.drawalg(req);
        System.out.println(JSON.toJSONString(res));
    }
}
