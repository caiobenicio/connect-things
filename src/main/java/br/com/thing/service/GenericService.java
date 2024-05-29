package br.com.thing.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.thing.entity.BaseEntity;

public abstract class GenericService<T extends BaseEntity<ID>, ID extends Serializable> implements ServiceMap {

	//private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	protected JpaRepository<T, ID> genericRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<T> findAll() {
		// if (this.LOGGER.isDebugEnabled()) {
		// 	this.LOGGER.debug("Requesting all records.");
		// }

		return this.genericRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public T insert(@RequestBody T entity) {
		entity.setId(null);		
		return this.genericRepository.save(entity);
	}
	
//	@RequestMapping(method = RequestMethod.PUT)
//	public void update(@RequestBody T entity) {
//		this.LOGGER.debug(String.format("Request to update the record [%s].", entity));
//
//		this.genericRepository.save(entity);
//	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody @Validated T entityObject, Errors errors) {
		//this.LOGGER.debug(String.format("Request to update the record [%s].", entityObject));

		if (entityObject == null) {
			//this.LOGGER.error("Entity can not be null.");
			//message.AddField("mensagem", "Não encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		if (entityObject.getId() == null) {
			//this.LOGGER.error("ID can not be null.");
			//message.AddField("mensagem", "Não encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		this.genericRepository.save(entityObject);

		//message.AddField("mensagem", "Salvo com sucesso");

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}    	

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestBody T entity) {
		this.genericRepository.delete(entity);
	}

}
