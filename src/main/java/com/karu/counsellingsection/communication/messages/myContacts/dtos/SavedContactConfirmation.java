package com.karu.counsellingsection.communication.messages.myContacts.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavedContactConfirmation {
    private String message;
    private String senderId;
    private String receiverId;
}
