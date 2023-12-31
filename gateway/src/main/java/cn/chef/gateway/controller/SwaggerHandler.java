package cn.chef.gateway.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.List;
import java.util.Optional;

/**
 * swagger的数据接口
 * 在访问swagger-ui中会拉去此接口的数据
 * @author whx
 * @date 2022/4/22
 */
@RestController
@RequestMapping("/swagger-resources")

public class SwaggerHandler {

    private final SecurityConfiguration securityConfiguration;
    private final UiConfiguration uiConfiguration;
    private final SwaggerResourcesProvider swaggerResourcesProvider;

    public SwaggerHandler(SecurityConfiguration securityConfiguration, UiConfiguration uiConfiguration, SwaggerResourcesProvider swaggerResourcesProvider) {
        this.securityConfiguration = securityConfiguration;
        this.uiConfiguration = uiConfiguration;
        this.swaggerResourcesProvider = swaggerResourcesProvider;
    }

    @GetMapping("/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration(){
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping("configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration(){
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()),HttpStatus.OK));
    }

    @GetMapping
    public Mono<ResponseEntity<List<SwaggerResource>>> swaggerResources(){
        return Mono.just((new ResponseEntity<>(swaggerResourcesProvider.get(),HttpStatus.OK)));
    }
}
