package com.ensat.repositories;

import com.ensat.entities.Agente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AgenteRepository extends CrudRepository<Agente, Integer> {

    Page<Agente> findAll(Pageable pageable);
}
