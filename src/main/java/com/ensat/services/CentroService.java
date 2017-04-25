package com.ensat.services;

import com.ensat.entities.Centro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by admin on 05/04/2017.
 */
public interface CentroService {

    Iterable<Centro> listAllCentros();

    Centro getCentroById(Integer id);

    Centro saveCentro(Centro centro);

    void deleteCentro(Integer id);
    
    Page<Centro> findAll(Pageable pageable);
}
