package com.karu.counsellingsection.communication.messages.myContacts;


import com.karu.counsellingsection.communication.messages.myContacts.dtos.SavedContactConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MyContactController {

    private final SimpMessagingTemplate template;
    private final MyContactService myContactService;

    @MessageMapping("/user.joinedUser")
    @SendTo("/public/topic")
    public JoinedUser userJoinedUser(@Payload JoinedUser joinedUser, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", joinedUser.getUsername());
        return joinedUser;
    }

    @MessageMapping("/user.addContact")
    public void userAddContact(@Payload MyContact myContact) {
        MyContact savedContact = myContactService.addContact(myContact);
        log.info("savedContact={}", savedContact);

        template.convertAndSendToUser(myContact.getCurrentUserId(),"/queue/messages",
                SavedContactConfirmation.builder().message("contact added successfully").build()
        );
    }

    @GetMapping("/my-contacts/{userId}")
    public ResponseEntity<List<MyContact>> findMyContacts(@PathVariable String userId) {
        log.info("new user fetching contacts userId={}", userId);
        return ResponseEntity.ok(myContactService.findMyContacts(userId));
    }

}
