package com.tadaah.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tadaah.freelancer.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
