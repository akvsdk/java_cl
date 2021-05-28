package com.j1ang.demo.api.scheduled;

import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.time.LocalDateTime;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;

@Component
public class ScheduledTask {

    //    @Scheduled(cron = "0 30 19 * * ? *")
    @Scheduled(cron = "0 */1 * * * ?")
    public void scheduledTask() {
        StaticLog.info("任务执行时间：{}", LocalDateTime.now());
        try {
            String holiday = getFileJson();
            JSONArray array = JSONUtil.parseArray(holiday);
            Console.log(array.toStringPretty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getFileJson() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("data/service.json");
        byte[] bytes = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
        return new String(bytes);
    }

}
