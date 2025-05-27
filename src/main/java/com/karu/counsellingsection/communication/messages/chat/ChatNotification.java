package com.karu.counsellingsection.communication.messages.chat;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {

    @Id
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private Date timestamp;
}
