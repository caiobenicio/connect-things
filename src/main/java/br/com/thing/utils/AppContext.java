package br.com.thing.utils;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import br.com.thing.AppMain;
import br.com.thing.entity.Client;
import br.com.thing.entity.ClientPermission;
import br.com.thing.entity.ClientPermissionKey;
import br.com.thing.entity.Permission;
import br.com.thing.mqtt.MqttConnection;
import br.com.thing.repository.ClientPermissionRepository;
import br.com.thing.repository.ClientRepository;
import br.com.thing.repository.PermissionRepository;
import br.com.thing.repository.ScheduleRepository;
import br.com.thing.schedule.ScheduleTask;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SuppressWarnings("deprecation")
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
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientPermissionRepository clientPermissionRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ScheduleTask agendador;

	@PostConstruct
	public void onStartup() throws Exception {

		Permission p1 = new Permission(1L, "ROLE_ADMIN");
		Permission p2 = new Permission(2L, "ROLE_USER");
		Permission p3 = new Permission(3L, "ROLE_SHADOW");
		List<Permission> permissions = Arrays.asList(p1, p2, p3);
		this.permissionRepository.saveAll(permissions);

		Client on = new Client("home on ", "on@hotmail.com", passwordEncoder.encode("123"));
		on = this.clientRepository.saveAndFlush(on);
				
		ClientPermissionKey userPermKey = new ClientPermissionKey();
		userPermKey.setPermissionId(p2.getId());
		userPermKey.setClientId(on.getId());
		
		ClientPermission userPermission = new ClientPermission();
		userPermission.setId(userPermKey);
		
		this.clientPermissionRepository.save(userPermission);
		
		// inicializa mqtt 
		MqttConnection.getInstance().connect(null, brokerMqtt);

		//busca agenda aberta no banco
		scheduleRepository.buscarAgendasAbertas().stream().forEach(a -> {
			agendador.agendamento(a);
		});

	}
	
	@PreDestroy
	public void onShutdown() {
		System.out.println("desligou");
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex(ServicePaths.ROOT_PATH + "/.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(appName).description(appDescription).version(appVersion).build();
	}

	@Bean
	public ApplicationProperty getApplicationProperty() {
		return new ApplicationProperty();
	}

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder(getApplicationProperty().getSecret());
	}

}
