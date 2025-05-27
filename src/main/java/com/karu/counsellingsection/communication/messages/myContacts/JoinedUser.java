package com.karu.counsellingsection.communication.messages.myContacts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinedUser {

    private String userId;
    private String username;
    private  Status status;
}

