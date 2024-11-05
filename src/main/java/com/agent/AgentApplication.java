package com.agent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class AgentApplication {

    public static void main(String[] args) {
;
        SpringApplication.run(AgentApplication.class,args);
        log.info("启动完成");
    }
}
