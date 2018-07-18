package com.ai.controller.robot.v1;

import com.ai.domain.vo.Message;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController("RobotGetUpdateController")
@RequestMapping("/robot/api/v1")
public class GetUpdateController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "分页获取taskUser", notes = "根据id查询taskUser信息")
    @ResponseBody
    @GetMapping("poll")
    public Message poll(HttpServletRequest httpRequest, @Param("gateways") Long[] gateways, @Param("sims") Long[] sims, @Param("tasks") Long[] tasks, @Param("templates") Long[] templates, @Param("users") String[] users) {

        String appid = httpRequest.getHeader("appid");


        for(int i=0; i<10; i++) {
            Map<String, Map<Object, String>> opts = new HashMap<>();


            // 检查网关有没有增加操作
            String v = redisTemplate.opsForValue().get("gateway_add_"+appid);
            if (v != null && !"".equalsIgnoreCase(v)) {
                redisTemplate.opsForValue().set("gateway_add_"+appid,"",1, TimeUnit.SECONDS);
                return new Message().ok(0, "success").addData("data", opts).addData("gateway_add",v.split(","));
            }
            // 检查sim卡有没有增加操作
            v = redisTemplate.opsForValue().get("sim_add_"+appid);
            if (v != null && !"".equalsIgnoreCase(v)) {
                redisTemplate.opsForValue().set("sim_add_"+appid,"",1, TimeUnit.SECONDS);
                return new Message().ok(0, "success").addData("data", opts).addData("sim_add",v.split(","));
            }



            if (gateways!=null) {
                for (Long id : gateways) {
                    String redis_value = redisTemplate.opsForValue().get("gateway_" + id);
                    if (redis_value != null && !"".equalsIgnoreCase(redis_value)) {
                        Map<Object, String> opt = new HashMap<>();
                        opt.put(id, redis_value);
                        opts.put("gateway", opt);
                        redisTemplate.opsForValue().set("gateway_" + id,"",1, TimeUnit.SECONDS);
                    }
                }
            }

            if (sims != null) {
                for (Long id : sims) {

                    // 检查有没有新开始的任务
                    v = redisTemplate.opsForValue().get("task_start_"+id);
                    if (v != null && !"".equalsIgnoreCase(v)) {
                        redisTemplate.opsForValue().set("sim_start_"+id,"",1, TimeUnit.SECONDS);
                        return new Message().ok(0, "success").addData("data", opts).addData("task_add",v.split(","));
                    }

                    String redis_value = redisTemplate.opsForValue().get("sim_" + id);
                    if (redis_value != null && !"".equalsIgnoreCase(redis_value)) {
                        Map<Object, String> opt = new HashMap<>();
                        opt.put(id, redis_value);
                        opts.put("sim", opt);
                        redisTemplate.opsForValue().set("sim_" + id,"",1, TimeUnit.SECONDS);
                    }
                }
            }

//            if (tasks != null) {
//                for (Long id : tasks) {
//                    String redis_value = redisTemplate.opsForValue().get("task_" + id);
//                    if (redis_value != null && !"".equalsIgnoreCase(redis_value)) {
//                        Map<Object, String> opt = new HashMap<>();
//                        opt.put(id, redis_value);
//                        opts.put("task", opt);
//                        redisTemplate.opsForValue().set("task_" + id,"",1, TimeUnit.SECONDS);
//                    }
//                }
//            }

            if (templates!=null) {
                for (Long id : templates) {
                    String redis_value = redisTemplate.opsForValue().get("template_" + id);
                    if (redis_value != null && !"".equalsIgnoreCase(redis_value)) {
                        Map<Object, String> opt = new HashMap<>();
                        opt.put(id, redis_value);
                        opts.put("template", opt);
                        redisTemplate.opsForValue().set("template" + id,"",1, TimeUnit.SECONDS);
                    }
                }
            }

            if (users!=null) {
                for (String uid : users) {
                    String redis_value = redisTemplate.opsForValue().get("schedule_" + uid);
                    if (redis_value != null && !"".equalsIgnoreCase(redis_value)) {
                        Map<Object, String> opt = new HashMap<>();
                        opt.put(uid, redis_value);
                        opts.put("schedule", opt);
                        redisTemplate.opsForValue().set("schedule_" + uid,"",1, TimeUnit.SECONDS);
                    }
                }
            }





            if (opts.size() > 0) {
                return new Message().ok(0, "success").addData("data", opts);
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return new Message().error(2, "sleep函数执行异常");
                }
            }


        }
        return new Message().error(1, "没有数据被更新");
    }



}
