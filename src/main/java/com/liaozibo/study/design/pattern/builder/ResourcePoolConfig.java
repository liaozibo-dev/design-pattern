package com.liaozibo.study.design.pattern.builder;

import org.apache.commons.lang3.StringUtils;

/**
 * Builder 模式（又称建造者模式、构建者模式、生成器模式）
 * 创建型设计模式
 *
 * 资源池配置类
 * */
public class ResourcePoolConfig {
    private String name; // 资源池名称（必填）
    private int maxTotal; // 最大线程数（非必填）
    private int maxIdle; // 最大空闲线程数（非必填）
    private int minIdle; // 最小空闲线程数（非必填）

    // 将构造器设置成私有，避免外部调用
    private ResourcePoolConfig(Builder builder) {
        name = builder.name;
        maxTotal = builder.maxTotal;
        maxIdle = builder.maxIdle;
        minIdle = builder.minIdle;
    }

    public String getName() {
        return name;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public static class Builder {
        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 4;
        private static final int DEFAULT_MIN_IDLE = 2;

        public ResourcePoolConfig build() {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("资源池名称不能为空");
            }
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("最大空闲线程数不能大于最大线程数");
            }
            if (minIdle > maxIdle) {
                throw new IllegalArgumentException("最小空闲线程不能大于最大空闲线程数");
            }
            return new ResourcePoolConfig(this);
        }

        public Builder setName(String name) {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("资源池名称不能为空");
            }
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("最大线程数必须大于 0");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setMaxIdle(int maxIdle) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("最大空闲线程数必须大于等于 0");
            }
            this.maxIdle = maxIdle;
            return this;
        }

        public Builder setMinIdle(int minIdle) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("最小空闲线程数必须大于等于 0");
            }
            this.minIdle = minIdle;
            return this;
        }
    }
}
