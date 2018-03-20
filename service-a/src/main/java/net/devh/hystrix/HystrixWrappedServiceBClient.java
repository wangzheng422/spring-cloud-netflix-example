package net.devh.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.devh.feign.ServiceBClient;

/**
 * User: Michael
 * Email: yidongnan@gmail.com
 * Date: 2016/6/3
 */
@Service
public class HystrixWrappedServiceBClient implements ServiceBClient {

    @Autowired
    private ServiceBClient serviceBClient;

    @Override
    @HystrixCommand(groupKey = "helloGroup", fallbackMethod = "fallBackCall",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "1"),
                //@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "5000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "1"),
                    @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "true"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "1"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "5000")
    })
    public String printServiceB() {
        return serviceBClient.printServiceB();
    }

    public String fallBackCall() {
        return "FAILED SERVICE B CALL! - FALLING BACK";
    }
}
