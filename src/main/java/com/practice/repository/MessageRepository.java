package com.practice.repository;

import com.practice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hzq
 * @date 2019/12/20
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

}
