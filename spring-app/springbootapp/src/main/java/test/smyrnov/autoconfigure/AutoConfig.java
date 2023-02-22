package test.smyrnov.autoconfigure;

import com.netflix.discovery.DiscoveryManager;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AutoConfig {

    @PostConstruct
    public void setUpDiscovery() {

    }
}
