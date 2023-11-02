package com.khalil.wdcar.repository;

import com.khalil.wdcar.entity.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Page<Photo> findAll(Specification spec, Pageable pageable);
    @Query(value = "select p.id from Photo p order by p.id desc")
    List<Long> getLastId();
}
