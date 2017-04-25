package com.ensat.services;

import com.ensat.entities.Centro;
import com.ensat.repositories.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Centro service implement.
 */
@Service
public class CentroServiceImpl implements CentroService {

    private CentroRepository centroRepository;

    @Autowired
    public void setProductRepository(CentroRepository centroRepository) {
        this.centroRepository = centroRepository;
    }

    @Override
    public Iterable<Centro> listAllCentros() {
        return centroRepository.findAll();
    }

    @Override
    public Centro getCentroById(Integer id) {
        return centroRepository.findOne(id);
    }

    @Override
    public Centro saveCentro(Centro centro) {
        return centroRepository.save(centro);
    }

    @Override
    public void deleteCentro(Integer id) {
        centroRepository.delete(id);
    }

    @Override
    public Page<Centro> findAll(Pageable pageable) {
        return centroRepository.findAll(pageable);
    }

}
