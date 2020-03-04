package br.com.thing.utils;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import br.com.thing.AppMain;
import br.com.thing.entity.Permission;
import br.com.thing.repository.PermissionRepository;
import br.com.thing.repository.ScheduleRepository;
import br.com.thing.schedule.ScheduleTask;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = AppMain.class)
public class AppContext {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.description}")
    private String appDescription;

    @Value("${spring.application.version}")
    private String appVersion;
    
    @Value("${broker.mqtt}")
    private String brokerMqtt;


    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ScheduleTask agendador;
    
    @PostConstruct
    public void onStartup() throws Exception {


    	if(permissionRepository.findById(1L) != null 
    			&& permissionRepository.findById(2L) != null) {
    		
        	Permission p1 = new Permission(1L, "ROLE_ADMIN");
        	Permission p2 = new Permission(2L, "ROLE_USER");

        	List<Permission> permissions = Arrays.asList(p1, p2);
        	
        	permissionRepository.saveAll(permissions);
    	}
    	
    	//InitMqtt.getinstance().connect(brokerMqtt);

    	
    	scheduleRepository.buscarAgendasAbertas().stream().
    		forEach(a -> { 
    			agendador.agendamento(a);
    		}
    	);
    	
    }
    
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select().paths(PathSelectors.regex(ResourcePaths.ROOT_PATH + "/.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(appName).description(appDescription).version(appVersion).build();
    }
    
    @Bean(name = "applicationProperty")
    public ApplicationProperty getApplicationProperty() {
        return new ApplicationProperty();
    }

    @Bean(name = "passwordEncoder")
    public StandardPasswordEncoder getStandardPasswordEncoder() {
        return new StandardPasswordEncoder(getApplicationProperty().getSecret());
    }

}
