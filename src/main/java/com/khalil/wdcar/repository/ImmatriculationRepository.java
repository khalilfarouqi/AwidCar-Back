package com.khalil.wdcar.repository;

import com.khalil.wdcar.entity.Immatriculation;
import com.khalil.wdcar.entity.enums.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImmatriculationRepository extends JpaRepository<Immatriculation, Long> {
    Page<Immatriculation> findAll(Specification spec, Pageable pageable);
    @Query(value = "select i.id from Immatriculation i order by i.id desc")
    List<Long> getLastId();
    boolean existsByCarNumberAndSeriesAndPrefectureRefId(int carNumber, Series series, Long prefectureRefId);
}
