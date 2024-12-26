package com.buddydim.chat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Chat f SET f.chatStatus = :chatStatus, f.content = :content WHERE f.id = :id")
    void updateFeedbackStatusAndContentById(final Long id,
                                            final ChatStatus chatStatus,
                                            final String content);
}
