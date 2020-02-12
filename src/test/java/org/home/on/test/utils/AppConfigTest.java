package org.home.on.test.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.thing.utils.AppConfig;

@Configuration
@Import(value = { AppConfig.class })
public class AppConfigTest {

}
