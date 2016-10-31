package com.logBlock;

/**
 * Created by ChenTiantao on 2016/10/11.
 */
public class PatternLayout extends ch.qos.logback.classic.PatternLayout {
    static {
        defaultConverterMap.put("msg",MessageConverter.class.getName());
    }

}
