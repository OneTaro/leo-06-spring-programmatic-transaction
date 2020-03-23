package com.spring.transaction.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @ClassName ProgrammaticTransaction
 * @Description 编程式事务演示
 * @Author wangss
 * @date 2020.03.23 21:36
 * @Version 1.0
 */
@Slf4j
@Component
public class ProgrammaticTransaction implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("COUNT BEFORE TRANSACTION: {}", getCount());

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES ( 1, 'AAA' )");
                log.info("COUNT IN TRANSACTION: {}", getCount());
                status.setRollbackOnly();
            }
        });

        log.info("COUNT AFTER TRANSACTION: {}", getCount());
    }

    private long getCount() {
        return (long) jdbcTemplate.queryForList("SELECT COUNT(1) AS CNT FROM FOO").get(0).get("CNT");
    }
}
