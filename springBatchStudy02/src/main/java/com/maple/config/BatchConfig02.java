package com.maple.config;

import com.maple.model.CreditBill;
import com.maple.service.processor.CreditBillProcess02;
import com.maple.service.writter.CreditBillDBWritter02;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @author maplezhang
 * @date 2019/11/27
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig02 {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<CreditBill> flatFileItemReader(){
        FlatFileItemReader<CreditBill> reader = new FlatFileItemReader<>();
        Resource resource = new FileSystemResource("src\\main\\resources\\data\\ch02\\credit_bill_info_02.txt");
        reader.setResource(resource);
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(CreditBill.class);
        DefaultLineMapper<CreditBill> lineMapper = new DefaultLineMapper<>();
        lineMapper.setFieldSetMapper(fieldSetMapper);
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("accountId", "name", "txnAmount", "txnDate", "address");
        lineMapper.setLineTokenizer(tokenizer);
        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    public CreditBillProcess02 creditBillProcess02(){
        return new CreditBillProcess02();
    }

    @Bean
    public ItemWriter<CreditBill> creditBillDBWritter02(){
        return new CreditBillDBWritter02();
    }

    /**
     * 将 creditBill 写入数据库
     * @return
     */
    @Bean
    public Job creditBillPersistJob(){
        return jobBuilderFactory.get("creditBillPersistJob")
                .start(creditBillPersistStep())
                .build();
    }

    @Bean
    public Step creditBillPersistStep(){
        return stepBuilderFactory.get("creditBillPersistStep")
                .<CreditBill, CreditBill>chunk(2)
                .reader(flatFileItemReader())
                .processor(creditBillProcess02())
                .writer(creditBillDBWritter02())
                .build();
    }


}
