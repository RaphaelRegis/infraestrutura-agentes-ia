package com.messageAdapter.MessageAdapter.entities.telegram;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ConversationMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID messageID;
    private String chatID;
    private String message;
    private Boolean fromUser;
    private LocalDateTime messageTimestamp;



}
