package com.khalil.wdcar.repository;

import com.khalil.wdcar.entity.Immatriculation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImmatriculationRepository extends JpaRepository<Immatriculation, Long> {
    Page<Immatriculation> findAll(Specification spec, Pageable pageable);
}
