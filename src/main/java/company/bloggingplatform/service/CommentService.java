package company.bloggingplatform.service;

import company.bloggingplatform.dto.request.CommentRequestDto;
import company.bloggingplatform.dto.response.CommentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getAll();

    void create(CommentRequestDto requestDto);

    void delete(Long id);

    void update(Long id, CommentRequestDto commentRequestDto);
}
