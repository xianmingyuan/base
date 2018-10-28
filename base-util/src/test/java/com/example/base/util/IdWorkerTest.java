package com.example.base.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class IdWorkerTest {

    @Test
    public void nextId() {
        long l = System.currentTimeMillis();
        System.out.println("l = " + l);
        IdWorker idWorker = new IdWorker(System.currentTimeMillis(), 1, 1);
        int size = 10000000;
        Set<Long> set = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            long id = idWorker.nextId();
            if (set.contains(id)) {
                Assert.fail();
            }
            set.add(id);
        }
    }
}