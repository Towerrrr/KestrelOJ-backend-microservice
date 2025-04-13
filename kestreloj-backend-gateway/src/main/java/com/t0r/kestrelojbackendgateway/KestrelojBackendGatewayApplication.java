package com.t0r.kestrelojbackendgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KestrelojBackendGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(KestrelojBackendGatewayApplication.class, args);
    }

}
