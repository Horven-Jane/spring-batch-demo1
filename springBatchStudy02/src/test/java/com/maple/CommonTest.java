package com.maple;

import com.maple.model.CreditBill;
import com.maple.service.writter.CreditBillDBWritter02;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author maplezhang
 * @date 2019/11/27
 */

// 让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest
public class CommonTest {

    @Autowired
    CreditBillDBWritter02 writter;

    @Test
    public void test1(){
        CreditBill creditBill = new CreditBill();
        creditBill.setAccountId("123");
        creditBill.setName("zhang");
        creditBill.setTxnAmount(100.0);
        creditBill.setTxnDate("20150101");

    }
}
