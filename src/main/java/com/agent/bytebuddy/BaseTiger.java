/**
 * @BelongsProject: JavaAgentStudy
 * @BelongsPackage: com.agent.bytebuddy
 * @Author: 黄勇铭
 * @CreateTime: 2024-11-08  13:52
 * @Description: TODO
 * @Version: 1.0
 */
package com.agent.bytebuddy;

public class BaseTiger {

    public String eat(int num, String food) {
        String decr = num + "Tiger eating " + food;
        System.out.println(decr);
        return decr;
    }

    public String sleeping(int num) {
        String decr = num + "Tiger sleeping ";
        System.out.println(decr);
        return decr;
    }
}