package net.devh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
public class ServiceB1Controller {
    
    @Autowired
    private Registration registration;       // 服务注册
    
    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${msg:unknown}")
    private String msg;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printServiceB() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        
        if (list != null && list.size() > 0) {
            ServiceInstance serviceInstance = list.get(0);
            return serviceInstance.getServiceId() + " (" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ")" + "===>Say " + msg;
        }

        return "service B can't find service instance<br/>";
    }
}
