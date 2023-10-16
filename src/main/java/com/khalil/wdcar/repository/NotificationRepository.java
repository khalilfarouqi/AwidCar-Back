package com.khalil.wdcar.repository;

import com.khalil.wdcar.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findAll(Specification spec, Pageable pageable);
    List<Notification> getByNotificationGroupId(Long id);
    List<Notification> getByClientId(Long id);
}
