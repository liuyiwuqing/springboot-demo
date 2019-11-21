package com.lywq.demo.common.tasks;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 王恩典
 * @title: tesk
 * @projectName demo
 * @description: 创建定时任务
 * @date 2019/11/8 19:54
 */
@Component
/**
 * 开启定时任务的注解
 */
@EnableScheduling
public class tesk {

//    @Scheduled(fixedRate = 5000)
//    public void job1(){
//        System.out.println("定时任务1" + new Date());
//    }
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void job2(){
//        System.out.println("定时任务2" + new Date());
//    }

}
