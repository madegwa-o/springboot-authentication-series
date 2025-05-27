package com.karu.counsellingsection.communication.messages.myContacts;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class MyContact {

    @Id
    private String id;
    private String currentUserId;
    private String currentUserName;
    private String contactUserId;
    private String contactUserName;
    private String contactImageUrl;
}
