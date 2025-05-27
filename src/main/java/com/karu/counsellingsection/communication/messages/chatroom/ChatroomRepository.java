package com.karu.counsellingsection.communication.messages.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatroomRepository extends MongoRepository<Chatroom, String> {

    Optional<Chatroom> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
