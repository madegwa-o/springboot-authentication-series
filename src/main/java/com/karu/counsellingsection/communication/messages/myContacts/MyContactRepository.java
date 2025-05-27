package com.karu.counsellingsection.communication.messages.myContacts;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MyContactRepository  extends MongoRepository<MyContact,String> {
    MyContact findByCurrentUserIdAndContactUserId(String currentUserId, String contactUserId);

    List<MyContact> findByCurrentUserId(String userId);
}
