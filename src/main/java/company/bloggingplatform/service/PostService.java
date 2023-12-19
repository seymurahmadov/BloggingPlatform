package company.bloggingplatform.service;

import company.bloggingplatform.dto.request.PostRequestDto;
import company.bloggingplatform.dto.response.PostResponseDto;

import java.util.List;

public interface PostService {
    List<PostResponseDto> getAll();

     void create (PostRequestDto requestDto);

    void delete(Long id);

    void update(Long id, PostRequestDto requestDto);

    PostResponseDto findById(Long id);
}
