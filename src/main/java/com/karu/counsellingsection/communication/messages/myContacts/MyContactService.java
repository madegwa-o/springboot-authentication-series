package com.karu.counsellingsection.communication.messages.myContacts;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyContactService {
    private final MyContactRepository myContactRepository;

    public List<MyContact> findMyContacts(String userId) {
       return myContactRepository.findByCurrentUserId(userId);
    }

    public MyContact addContact(MyContact myContact) {
        MyContact senderContactExists = myContactRepository
                .findByCurrentUserIdAndContactUserId(
                        myContact.getCurrentUserId(),
                        myContact.getContactUserId()
                );

        MyContact receiverContactExists = myContactRepository
                .findByCurrentUserIdAndContactUserId(
                        myContact.getCurrentUserId(),
                        myContact.getContactUserId()
                );

        if (senderContactExists != null && receiverContactExists != null) {
            log.info("senderContactExists={}, receiverContactExists={}", senderContactExists, receiverContactExists);
            return myContact;
        }else {

            myContactRepository.save(myContact);

            // invert the details for the other user

            MyContact receiverContact =  MyContact.builder()
                    .contactUserId(myContact.getCurrentUserId())
                    .contactUserName(myContact.getCurrentUserName())

                    .currentUserId(myContact.getContactUserId())
                    .currentUserName(myContact.getContactUserName())
                    .build();
            myContactRepository.save(receiverContact);

        }
        return myContact;
    }


}
