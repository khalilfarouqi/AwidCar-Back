package com.khalil.wdcar.entity;

import com.khalil.wdcar.entity.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="NotificationGroups")
public class NotificationGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String object;

    private String body;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private Boolean notificationWeb;

    private Date dateEnvoy;
}
