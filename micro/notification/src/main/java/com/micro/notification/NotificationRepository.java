package com.micro.notification;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * NotificationRepository
 */
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}