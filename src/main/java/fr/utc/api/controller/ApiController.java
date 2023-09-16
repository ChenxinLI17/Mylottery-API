package fr.utc.api.controller;
import fr.utc.mylottery.rpc.IAPIBooth;
import fr.utc.mylottery.rpc.req.DrawReq;
import fr.utc.mylottery.rpc.req.InitReq;
import fr.utc.mylottery.rpc.res.DrawRes;
import fr.utc.mylottery.rpc.res.InitRes;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@EnableDubbo
@CrossOrigin(origins = "http://localhost:5173")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Reference(interfaceClass = IAPIBooth.class, check = false, version = "1.0.0", url = "dubbo://127.0.0.1:20880")
    private IAPIBooth apiBooth;

    @GetMapping
    public InitRes Initialize(@RequestParam("activityId") String activityId) {
        logger.info("get");
        InitReq req = new InitReq(Long.parseLong(activityId));
        return apiBooth.initialize(req);
    }

    @PostMapping
    public DrawRes doDraw(@RequestBody DrawReq drawReq) {
        logger.info("post "+ drawReq.getuId() + " "+drawReq.getActivityId());
        DrawRes drawRes = apiBooth.doDraw(drawReq);
        return drawRes;
    }
}
