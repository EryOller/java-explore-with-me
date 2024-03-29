package ru.practicum.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.comment.model.CommentStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentStatusUpdateRequest {

    @NotNull
    @NotEmpty
    private List<Long> commentIds;

    @NotNull
    private CommentStatus status;
}