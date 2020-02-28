package br.com.thing.resources;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.thing.entity.BaseEntity;
import br.com.thing.utils.ResourceBase;

import java.io.Serializable;
import java.util.List;

public abstract class GenericService<T extends BaseEntity<ID>, ID extends Serializable> implements ResourceBase {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	protected JpaRepository<T, ID> genericRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<T> findAll() {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug("Requesting all records.");
		}

		return this.genericRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public T insert(@RequestBody T entity) {
		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(String.format("Saving the entity [%s].", entity));
		}

		entity.setId(null);
		
		return this.genericRepository.save(entity);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody T entity) {
		this.LOGGER.debug(String.format("Request to update the record [%s].", entity));

		this.genericRepository.save(entity);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestBody T entity) {
		this.LOGGER.debug(String.format("Request to delete the record [%s].", entity));

		this.genericRepository.delete(entity);
	}

}
