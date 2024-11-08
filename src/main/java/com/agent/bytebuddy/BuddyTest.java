/**
 * @BelongsProject: JavaAgentStudy
 * @BelongsPackage: com.agent.bytebuddy
 * @Author: 黄勇铭
 * @CreateTime: 2024-11-08  13:45
 * @Description: TODO
 * @Version: 1.0
 */
package com.agent.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class BuddyTest {


    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        BuddyTest test=new BuddyTest();
        test.myTest();

    }

    public void  myTest() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ByteBuddy test=  new ByteBuddy();
        test.subclass(BaseTiger.class).make()
                                      .load(this.getClass().getClassLoader()).getLoaded().newInstance().sleeping(200);


        ByteBuddy test2 = new ByteBuddy();
        Class<? extends BaseTiger> loaded = test2.subclass(BaseTiger.class).defineMethod("dinner", String.class, Modifier.PUBLIC)
                .withParameter(Integer.class).withParameter(String.class)
                .intercept(FixedValue.value("myReturnValue")).make().load(this.getClass().getClassLoader()).getLoaded();

        Object value = loaded.getMethod("dinner", Integer.class, String.class)
                .invoke(loaded.newInstance(), 2022, "apple");

        System.out.println(value);

    }
}

