package br.com.thing.context;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.thing.service.ServicePaths;
import br.com.thing.utils.AppProperty;
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
public class ContextInitialized {

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
	private AppProperty prop;

	@Autowired
	private ScheduleTask agendador;

	@PostConstruct
	public void onStartup() throws Exception {

		// cria as permissoes eo usuario
		creatRoleAndUser();

		// inicializa o broker mqtt
		if (prop.getBrokerEnabled())
			MqttConnection.getInstance().connect();

		// busca os agendamento de comando em aberta no banco
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
		return new ApiInfoBuilder().title(prop.getAppName()).description(prop.getAppDescription()).version("").build();
	}

	@Bean
	public AppProperty getApplicationProperty() {
		return AppProperty.getinstance();
	}

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder(getApplicationProperty().getSecret());
	}

	public void creatRoleAndUser() {
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
	}

}
