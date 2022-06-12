package com.liaozibo.study.design.pattern.builder;

import org.junit.jupiter.api.Test;

public class ResourcePoolConfigTest {

    @Test
    public void test() {
        ResourcePoolConfig resourcePoolConfig = new ResourcePoolConfig.Builder()
                .setName("test")
                .setMaxTotal(32)
                .setMaxIdle(8)
                .setMinIdle(4)
                .build();
    }
}
