package com.agent.attach;


import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @Author: 黄勇铭
 * @CreateTime: 2024-11-05  17:54
 * @Description: attach
 * @Version: 1.0
 */
public class MyAttach {

    public static void main(String[] args) throws IOException, AttachNotSupportedException {

        VirtualMachine virtualMachine= VirtualMachine.attach(String.valueOf(55222));
    }
}
