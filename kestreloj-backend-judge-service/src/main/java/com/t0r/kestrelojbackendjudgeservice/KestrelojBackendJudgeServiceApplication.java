package com.t0r.kestrelojbackendjudgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.t0r")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.t0r.kestrelojbackendserviceclient.service"})
public class KestrelojBackendJudgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KestrelojBackendJudgeServiceApplication.class, args);
    }

}
