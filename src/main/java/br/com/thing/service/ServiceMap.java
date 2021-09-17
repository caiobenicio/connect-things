package br.com.thing.service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(consumes = "application/json", produces = "application/json")
public interface ServiceMap {
}
