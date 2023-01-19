package test.smyrnov.autoconfigure;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.LeaseInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.Application;

public class ExampleEurekaClient {

    private static ApplicationInfoManager applicationInfoManager;
    private static EurekaClient eurekaClient;

    private static synchronized ApplicationInfoManager initializeApplicationInfoManager(EurekaInstanceConfig instanceConfig) {
        if (applicationInfoManager == null) {
            InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();
            applicationInfoManager = new ApplicationInfoManager(instanceConfig, instanceInfo);
        }

        return applicationInfoManager;
    }

    private static synchronized EurekaClient initializeEurekaClient(ApplicationInfoManager applicationInfoManager, EurekaClientConfig clientConfig) {
        if (eurekaClient == null) {
            eurekaClient = new DiscoveryClient(applicationInfoManager, clientConfig);
        }

        return eurekaClient;
    }


    public void sendRequestToServiceUsingEureka(EurekaClient eurekaClient) {
        try {
            var app = new Application();
            app.setName("finally");
            app.addInstance(new InstanceInfo(
                    "finally",
                    "standalone",
                    "group",
                    "127.0.0.1",
                    "",
                    new InstanceInfo.PortWrapper(true, 8081),
                    null,
                    "localhost:8081/test",
                    "",
                    "",
                    "",
                    "localhost:8081",
                    "",
                    1,
                    new MyDataCenterInfo(DataCenterInfo.Name.MyOwn),
                    "localhost",
                    InstanceInfo.InstanceStatus.UP,
                    null,
                    InstanceInfo.InstanceStatus.UP,
                    new LeaseInfo(30, 90, System.currentTimeMillis(), null, 0, 0, System.currentTimeMillis()),
                    false,
                    null,
                    System.currentTimeMillis(),
                    0L,
                    InstanceInfo.ActionType.ADDED,
                    ""
            ));
            eurekaClient.getApplications().addApplication(app);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ExampleEurekaClient sampleClient = new ExampleEurekaClient();

        DefaultEurekaClientConfig conf = new DefaultEurekaClientConfig();
        // create the client
        ApplicationInfoManager applicationInfoManager = initializeApplicationInfoManager(new MyDataCenterInstanceConfig("eureka"));
        EurekaClient client = initializeEurekaClient(applicationInfoManager, conf);
        // use the client
        sampleClient.sendRequestToServiceUsingEureka(client);
        Thread.sleep(100000);

        // shutdown the client
        eurekaClient.shutdown();
    }

}
