package com.agent.agentClass;

import cn.hutool.core.util.RandomUtil;
import com.agent.util.ByteArrayUtil;

import java.io.*;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

public class AgentMainClass {

    public static void premain(String agentArgs, Instrumentation inst) throws Exception {
        System.out.println("最初返回值:" + MyTest.messsagess());
        String path = "C:/ideaProject/JavaAgentStudy/src/main/java/com/agent/doc/MyTest.class";
        byte[] bytes = ByteArrayUtil.getBytes(path);
        ClassDefinition classDefinition = new ClassDefinition(MyTest.class, bytes);
        inst.redefineClasses(classDefinition);

        System.out.println("修改后返回值:" + MyTest.messsagess());
        String rollBackPath = "C:/ideaProject/JavaAgentStudy/src/main/java/com/agent/doc/OrginMyTest.class";
        byte[] rollbackBytes = ByteArrayUtil.getBytes(rollBackPath);
        ClassDefinition classDefinition2 = new ClassDefinition(MyTest.class, rollbackBytes);
        inst.redefineClasses(classDefinition2);
        System.out.println("回滚后返回值:" + MyTest.messsagess());
    }


}
