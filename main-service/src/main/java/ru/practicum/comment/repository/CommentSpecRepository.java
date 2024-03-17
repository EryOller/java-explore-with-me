package ru.practicum.comment.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.practicum.comment.model.Comment;
import ru.practicum.comment.model.CommentStatus;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentSpecRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    static Specification<Comment> hasText(String text) {
        return (event, query, cb) -> Optional.ofNullable(text)
                .map(t -> cb.like(cb.lower(event.get("text")), "%" + t.toLowerCase() + "%"))
                .orElse(cb.isTrue(cb.literal(true)));
    }

    static Specification<Comment> hasEvents(List<Long> events) {
        return (event, query, cb) -> {
            if (events == null || events.isEmpty()) {
                return cb.isTrue(cb.literal(true));
            } else {
                CriteriaBuilder.In<Long> eventIds = cb.in(event.get("event"));
                for (long eventId : events) {
                    eventIds.value(eventId);
                }
                return eventIds;
            }
        };
    }

    static Specification<Comment> hasUsers(List<Long> users) {
        return (event, query, cb) -> {
            if (users == null || users.isEmpty()) {
                return cb.isTrue(cb.literal(true));
            } else {
                CriteriaBuilder.In<Long> userIds = cb.in(event.get("commenter"));
                for (long userId : users) {
                    userIds.value(userId);
                }
                return userIds;
            }
        };
    }

    static Specification<Comment> hasStatuses(List<CommentStatus> statuses) {
        return (event, query, cb) -> {
            if (statuses == null || statuses.isEmpty()) {
                return cb.isTrue(cb.literal(true));
            } else {
                CriteriaBuilder.In<CommentStatus> commentStatuses = cb.in(event.get("status"));
                for (CommentStatus status : statuses) {
                    commentStatuses.value(status);
                }
                return commentStatuses;
            }
        };
    }

    static Specification<Comment> hasRangeStart(LocalDateTime rangeStart) {
        return (event, query, cb) -> Optional.ofNullable(rangeStart)
                .map(t -> cb.greaterThan(event.get("createdOn"), rangeStart))
                .orElse(cb.isTrue(cb.literal(true)));
    }

    static Specification<Comment> hasRangeEnd(LocalDateTime rangeEnd) {
        return (event, query, cb) -> Optional.ofNullable(rangeEnd)
                .map(t -> cb.lessThan(event.get("createdOn"), rangeEnd))
                .orElse(cb.isTrue(cb.literal(true)));
    }
}