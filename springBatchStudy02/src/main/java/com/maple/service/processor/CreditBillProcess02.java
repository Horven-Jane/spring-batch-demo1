package com.maple.service.processor;

import com.maple.model.CreditBill;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author maplezhang
 * @date 2019/11/28
 */
@Component
public class CreditBillProcess02 implements ItemProcessor<CreditBill, CreditBill> {


    @Override
    public CreditBill process(CreditBill item) throws Exception {
        System.out.println("in [CreditBillProcess02], processing credit bill ... ");
        return item;
    }

}
