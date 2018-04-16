package com.vince.aop;

/**
 * 切面的实现
 */
public class LogAdvice implements Advice {
    @Override
    public void BeforeAdvice() {
        System.out.println("start time:"+System.currentTimeMillis());
    }

    @Override
    public void AfterAdvice() {
        System.out.println("end time:"+System.currentTimeMillis());
    }
}
