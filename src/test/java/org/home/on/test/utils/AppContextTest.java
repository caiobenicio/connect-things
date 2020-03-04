package org.home.on.test.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.thing.utils.AppContext;

@Configuration
@Import(value = { AppContext.class })
public class AppContextTest {

}
