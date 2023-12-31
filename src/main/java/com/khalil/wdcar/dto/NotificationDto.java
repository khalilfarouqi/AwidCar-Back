package com.khalil.wdcar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDto implements Serializable {
    private Long id;
    private String object;
    private String body;
    private Boolean isRead;
    private Boolean notificationWeb;
    private ClientDto client;
    private NotificationGroupDto notificationGroup;
}
