package com.practice.service;

import com.practice.entity.Announce;
import com.practice.entity.Message;
import com.practice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzq
 * @date 2019/12/20
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessageToAll(Announce announce) {
        announce.insertOrUpdate();
    }

    public void sendToSomeBody(Integer toId, String title, String content, Integer fromId) {
        Message message = new Message();
        message.setToMember(toId);
        message.setTitle(title);
        message.setContent(content);
        message.setFromMember(fromId);
        messageRepository.save(message);
    }


    public void send(List<Integer> ids, String title, String content, Integer fromId) {
        ids.forEach(id -> {
            Message message = new Message();
            message.setToMember(id);
            message.setTitle(title);
            message.setContent(content);
            message.setFromMember(fromId);
            messageRepository.save(message);
        });
    }

}
