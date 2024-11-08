package com.agent.agentClass.arthas;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @Author: 黄勇铭
 * @CreateTime: 2024-11-05  19:03
 * @Description: arthcasTestMain
 * @Version: 1.0
 */
public class ArthasMainTest {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        VirtualMachine virtualMachine= VirtualMachine.attach("24212");
        String agentArgs = "param1=value1&param2=value2";
        virtualMachine.loadAgent("C:/ideaProject/JavaAgentStudy/target/JavaAgentStudy-1.0-SNAPSHOT-jar-with-dependencies.jar",agentArgs);
    }
}
