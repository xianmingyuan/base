package com.example.base.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class IdWorkerTest {

    @Test
    public void nextId() {
        IdWorker idWorker = new IdWorker(0, 0);
        // 防止黑客猜到ID顺序可以休眠
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            log.info(Long.toBinaryString(id));
            log.info("{}", id);
        }
    }
}
