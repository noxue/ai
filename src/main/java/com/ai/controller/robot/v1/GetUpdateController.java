package com.ai.controller.robot.v1;

import com.ai.domain.vo.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("RobotGetUpdateController")
@RequestMapping("/robot/api/v1/update")
public class GetUpdateController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "分页获取taskUser", notes = "根据id查询taskUser信息")
    @ResponseBody
    @GetMapping("poll")
    public Message findTaskUserById(String type, String[] ids) {
        if (StringUtils.isEmpty(type)) {
            return new Message().error(8000, "缺少参数项");
        }
        if (!type.equals("gateway") || !type.equals("sim") || !type.equals("template") || !type.equals("config")) {
            return new Message().error(8001, "不在服务区");
        }

        if (ids.length == 0) {
            return new Message().error(8002, "缺少参数 id");
        }
        String data = "";
        String redis_value = "";
        StringBuffer jsonStr = new StringBuffer();
        for (int i = 0; i < ids.length; i++) {
            redis_value = redisTemplate.opsForValue().get(type + "_" + ids[i]);
            if (redis_value == null) {
                redis_value = "";
            }
            jsonStr.append("{'" + ids[i] + "':" + "'" + redis_value + "'")
                    .append("}")
                    .append(",");
        }
        jsonStr = jsonStr.deleteCharAt(jsonStr.length() - 1);
        data = "[" + jsonStr.toString() + "]";
        return new Message().ok(0, "success").addData("updateDate", data);
    }

}
