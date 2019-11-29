package com.maple.service.writter;

import com.maple.dao.mapper.CreditBillMapper;
import com.maple.model.CreditBill;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author maplezhang
 * @date 2019/11/27
 */
@Component
public class CreditBillDBWritter02 implements ItemWriter<CreditBill> {

    @Autowired
    CreditBillMapper creditBillMapper;

    @Override
    public void write(List<? extends CreditBill> items) throws Exception {
        for(CreditBill each : items){
            creditBillMapper.insertSelective(each);
        }
    }
}
