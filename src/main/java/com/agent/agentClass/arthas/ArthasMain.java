package com.agent.agentClass.arthas;

import cn.hutool.core.util.StrUtil;
import com.agent.util.ByteArrayUtil;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 黄勇铭
 * @CreateTime: 2024-11-05  18:18
 * @Description: 动态attch
 * @Version: 1.0
 */
public class ArthasMain {

    public static Instrumentation instrumentation;

    public static Map<String, Class> classMap = new HashMap<>();

    public static Map<String, ClassFileTransformer> transformerMap = new HashMap<>();

    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {
        // 为了保证 每次调用时的 Instrumentation都是一致，所以赋值到静态变量
        if (instrumentation == null) {
            instrumentation = inst;
        }

        System.out.println("动态attach啦啦啦啦啦");

        Class[] classes = inst.getAllLoadedClasses();
        for (Class classZ : classes) {
            classMap.put(classZ.getName(), classZ);
        }
        // 判断入参的方法名称做处理
        String[] array = StrUtil.split(agentArgs, ",");
        String methodName = array[0], classFilePath = array[1];
        if ("retransformClasses".equals(methodName)) {
            retransformClasses(classFilePath);
        }

        if ("removeTransformer".equals(methodName)) {
            removeTransformer(classFilePath);
        }
    }

    public static void retransformClasses(String filePath) throws Exception {
        ClassFileTransformer classFileTransformer = new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (!"cn/hutool/core/util/RandomUtil".equals(className)) {
                    return null;
                }
                return ByteArrayUtil.getBytes(filePath);
            }
        };
        instrumentation.addTransformer(classFileTransformer, true);
        // 保存classFileTransformer 在移除时使用
        transformerMap.put(filePath, classFileTransformer);

        String fileName = getName(filePath);
        for (String className : classMap.keySet()) {
            if (className.endsWith(fileName)) {
                Class classz = classMap.get(className);
                instrumentation.retransformClasses(classz);
                break;
            }
        }
    }

    /**
     * 移除 transformer
     */
    public static void removeTransformer(String filePath) throws UnmodifiableClassException {
        ClassFileTransformer classFileTransformer = transformerMap.get(filePath);
        instrumentation.removeTransformer(classFileTransformer);
        String fileName = getName(filePath);
        for (String className : classMap.keySet()) {
            if (className.endsWith(fileName)) {
                // 重新加载用于回滚
                Class clazz = classMap.get(className);
                instrumentation.retransformClasses(clazz);
                break;
            }
        }
    }

    /**
     * 通过入参获取调用的方法名
     *
     * @param filePath
     * @return
     */
    private static String getName(String filePath) {
        String[] array = StrUtil.split(filePath, "/");
        String[] classArray = array[array.length - 1].split("\\.");
        return classArray[0];
    }
}
