package org.home.on.test.repositories;

import java.util.List;

import org.apache.log4j.Logger;
import org.home.on.test.utils.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.thing.entity.Home;
import br.com.thing.repository.ResidenciaRepository;

public class ResidenciaRepositoryTest extends AbstractTest {
    private static final Logger LOGGER = Logger.getLogger(ResidenciaRepositoryTest.class);

    @Autowired
    public ResidenciaRepository residenciaRepository;

    @Test
    public void findAllTest() {
        List<Home> rsd = this.residenciaRepository.findAll();

        LOGGER.info(rsd);
    }

    @Test
    public void findByQtdmorador() {
    //    List<ResidenciaEntity> rsd = this.residenciaRepository.findByQtdmorador(2);

        if (LOGGER.isInfoEnabled()) {
       //     LOGGER.info("Test FindAll(): " + rsd);
        }
    }


    //-------------------Create a User--------------------------------------------------------
}

