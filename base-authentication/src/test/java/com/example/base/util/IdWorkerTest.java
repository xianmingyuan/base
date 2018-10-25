package com.example.base.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class IdWorkerTest {

    @Test
    public void nextId() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2018-10-25 00:00:00");
        IdWorker idWorker = new IdWorker(date.getTime(), 0, 0);
        // 防止黑客猜到ID顺序可以休眠
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            log.info(Long.toBinaryString(id));
            log.info("{}", id);
        }
    }
}
