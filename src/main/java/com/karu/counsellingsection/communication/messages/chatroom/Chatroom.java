package com.karu.counsellingsection.communication.messages.chatroom;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Chatroom {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String receiverId;
}
