package cn.chef.doctor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.chef.doctor.mapper")
@EnableSwagger2
@EnableCaching
//todo 分页/缓存
public class DoctorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoctorApplication.class,args);
    }
}
