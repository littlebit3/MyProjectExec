package com.vt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
//        org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration.class
}) //(exclude = ShardingSphereAutoConfiguration.class)
@MapperScan("com.vt.mapper")
public class DynamicAndShardingApplication {


    public static void main(String[] args) {
//        SpringApplicationBuilder builder = new SpringApplicationBuilder();
//        builder.main(DyShardingApplication.class);
//        builder.allowCircularReferences(false);
//        builder.run(args);
        SpringApplication.run(DynamicAndShardingApplication.class, args);
    }
}
