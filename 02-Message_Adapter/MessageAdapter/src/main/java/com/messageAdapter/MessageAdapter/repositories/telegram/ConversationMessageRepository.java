package com.messageAdapter.MessageAdapter.repositories.telegram;

import com.messageAdapter.MessageAdapter.entities.telegram.ConversationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationMessageRepository extends JpaRepository<ConversationMessage, String> {
}
