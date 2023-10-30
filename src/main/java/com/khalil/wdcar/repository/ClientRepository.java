package com.khalil.wdcar.repository;

import com.khalil.wdcar.beans.CarRentalBean;
import com.khalil.wdcar.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findAll(Specification spec, Pageable pageable);
    @Query(value = "select new com.khalil.wdcar.beans.CarRentalBean(c.adress, c.cin, c.city, c.country, c.firstName, c.lastName, c.licence, c.userName) from Client c " +
            "where lower(c.adress) like lower(concat('%',:label,'%')) or " +
            "lower(c.cin) like lower(concat('%',:label,'%')) or " +
            "lower(c.city) like lower(concat('%',:label,'%')) or " +
            "lower(c.country) like lower(concat('%',:label,'%')) or " +
            "lower(c.firstName) like lower(concat('%',:label,'%')) or " +
            "lower(c.lastName) like lower(concat('%',:label,'%')) or " +
            "lower(c.licence) like lower(concat('%',:label,'%')) or " +
            "lower(c.userName) like lower(concat('%',:label,'%'))")
    List<CarRentalBean> getSearch(@Param("label") String label);
}
