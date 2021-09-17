package org.home.on.test.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.thing.context.ContextInitialized;

@Configuration
@Import(value = { ContextInitialized.class })
public class AppContextTest {

}
