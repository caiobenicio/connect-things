package org.home.on.test.repositories;

import java.util.List;

import org.apache.log4j.Logger;
import org.home.on.test.utils.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.thing.entity.ScheduleEntity;
import br.com.thing.repository.ScheduleRepository;

public class AgendaRepositoryTest extends AbstractTest {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);

    @Autowired
    private ScheduleRepository agendaRepository;

    @Test
    public void findAllTest() {
        List<ScheduleEntity> agenda = this.agendaRepository.findAll();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Test FindAll(): " + agenda);
        }
    }

    @Test
    public void buscarAgendasAbertasTest() {
       // List<ScheduleEntity> agendas = this.agendaRepository.buscarAgendasAbertas(new Date());
//
  //      LOGGER.info(agendas);
    }

}
