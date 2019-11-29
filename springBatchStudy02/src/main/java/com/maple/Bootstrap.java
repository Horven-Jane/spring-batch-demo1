package com.maple;

import com.maple.util.SpringBeanUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author maplezhang
 * @date 2019/11/27
 */

//如果没有MapperScan这个注解，则每个mapper接口上都必须加上@Mapper 注解
@MapperScan("com.maple.dao.mapper")
@SpringBootApplication
public class Bootstrap {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Bootstrap.class, args);
        JobLauncher launcher = (JobLauncher)context.getBean("jobLauncher");
        Job job1 = (Job)context.getBean("creditBillPersistJob");
        try{
            JobParametersBuilder paramBuilder = new JobParametersBuilder();
            paramBuilder.addLong("DS", System.currentTimeMillis());
            JobExecution jobExecution = launcher.run(job1, paramBuilder.toJobParameters());
            System.out.println("JobExecution : " + jobExecution.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
