package com.ensat.services;

import com.ensat.entities.Agente;
import com.ensat.repositories.AgenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Product service implement.
 */
@Service
public class AgenteServiceImpl implements AgenteService {

    private AgenteRepository agenteRepository;

    @Autowired
    public void setProductRepository(AgenteRepository agenteRepository) {
        this.agenteRepository = agenteRepository;
    }

    @Override
    public Iterable<Agente> listAllAgentes() {
        return agenteRepository.findAll();
    }

    @Override
    public Agente getAgenteById(Integer id) {
        return agenteRepository.findOne(id);
    }

    @Override
    public Agente saveAgente(Agente agente) {
        return agenteRepository.save(agente);
    }

    @Override
    public void deleteAgente(Integer id) {
        agenteRepository.delete(id);
    }

    @Override
    public Page<Agente> findAll(Pageable pageable) {
        return agenteRepository.findAll(pageable);
    }

}
