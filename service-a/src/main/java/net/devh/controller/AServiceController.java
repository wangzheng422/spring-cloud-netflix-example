package net.devh.controller;

import net.devh.hystrix.HystrixWrappedServiceBClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * User: Michael
 * Email: yidongnan@gmail.com
 * Date: 2016/6/3
 */
@RefreshScope
@RestController
@Api
public class AServiceController {
    @Autowired
    private Registration registration;       // 服务注册

    @Autowired
    private Tracer tracer;
    @Value("${name:unknown}")
    private String name;

    @Autowired
    private HystrixWrappedServiceBClient serviceBClient;
    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printServiceA() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        
        if (list != null && list.size() > 0) {
            ServiceInstance serviceInstance = list.get(0);
            return "zipkin traceid: " + tracer.getCurrentSpan().traceIdString() + " :<br/>" + serviceInstance.getServiceId() + " (" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ")" + "===>name:" + name + "<br/>" + serviceBClient.printServiceB();
        }

        return "service A can't find service instance<br/>";

    }
}
