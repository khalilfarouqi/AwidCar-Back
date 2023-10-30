package com.khalil.wdcar.repository;

import com.khalil.wdcar.beans.CarRentalBean;
import com.khalil.wdcar.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findAll(Specification spec, Pageable pageable);
    List<Car> findAll(Specification spec);
    @Query(value = "select new com.khalil.wdcar.beans.CarRentalBean(c.name, c.brande, c.style) from Car c where lower(c.name) like lower(concat('%',:label,'%')) or lower(c.brande) like lower(concat('%',:label,'%')) or lower(c.style) like lower(concat('%',:label,'%'))")
    List<CarRentalBean> getSearch(@Param("label") String label);

}
