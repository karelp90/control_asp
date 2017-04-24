package com.ensat.services;

import com.ensat.entities.Agente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgenteService {

    Iterable<Agente> listAllAgentes();

    Agente getAgenteById(Integer id);

    Agente saveAgente(Agente agente);

    void deleteAgente(Integer id);

    Page<Agente> findAll(Pageable pageable);

}
