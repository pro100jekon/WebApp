package test.smyrnov.eureka;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EurekaRegistrationBean {

    @Value("${spring.application.name}")
    String name;

    @PostConstruct
    void registerServiceInEureka() {
        System.setProperty("eureka.client.props", "eureka");
        EurekaInstanceConfig config = new MyDataCenterInstanceConfig("myEureka");
        EurekaConfigBasedInstanceInfoProvider provider = new EurekaConfigBasedInstanceInfoProvider(config);
        ApplicationInfoManager manager = new ApplicationInfoManager(config, provider.get());
        manager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
        DiscoveryClient client = new DiscoveryClient(manager, new DefaultEurekaClientConfig("myEureka"));
        waitForRegistrationWithEureka(client);
        System.out.println("I am registered, bitchez");
    }

    private void waitForRegistrationWithEureka(EurekaClient eurekaClient) {
        InstanceInfo nextServerInfo = null;
        while (nextServerInfo == null) {
            try {
                nextServerInfo = eurekaClient.getApplication(name).getInstances().get(0);
            } catch (Throwable e) {
                System.out.println("Waiting ... verifying service registration with eureka ...");

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
