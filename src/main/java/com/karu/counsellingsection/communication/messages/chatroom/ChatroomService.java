package com.karu.counsellingsection.communication.messages.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatroomService {

    private final ChatroomRepository chatroomRepository;

    public Optional<String> getChatroomId(String senderId, String receiverId,boolean createNewChatroomIfNotExist) {
        return chatroomRepository.findBySenderIdAndReceiverId(senderId,receiverId)
                .map(Chatroom::getChatId)
                .or(() -> {
                    if (createNewChatroomIfNotExist) {
                        String chatId = createChatId(senderId, receiverId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChatId(String senderId, String receiverId) {
        String chatId = String.format("%s_%s", senderId, receiverId);

        List<Chatroom> chatrooms = List.of(
                Chatroom.builder()
                        .chatId(chatId)
                        .senderId(senderId)
                        .receiverId(receiverId)
                        .build(),

                Chatroom.builder()
                        .chatId(chatId)
                        .senderId(receiverId)
                        .receiverId(senderId)
                        .build()
        );

        chatroomRepository.saveAll(chatrooms);

        return chatId;
    }
}
