package com.karu.counsellingsection.communication.messages.chat;

import com.karu.counsellingsection.communication.messages.chatroom.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatroomService chatroomService;

    public ChatMessage save(ChatMessage chatMessage) {
        String chatId = chatroomService.getChatroomId(chatMessage.getSenderId(),chatMessage.getReceiverId(),true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(String senderId, String receiverId) {
        Optional<String> chatId = chatroomService.getChatroomId(senderId,receiverId,false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
