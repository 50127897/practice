package com.practice.repository;

import com.practice.entity.Message;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author hzq
 * @date 2019/12/20
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(nativeQuery = true,value = "select * from t_message where to_member = :toMember")
    List<Message> select(@Param("toMember")Integer toMember);
}
