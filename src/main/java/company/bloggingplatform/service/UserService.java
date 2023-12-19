package company.bloggingplatform.service;


import company.bloggingplatform.dto.request.ChangePasswordRequestDto;
import company.bloggingplatform.dto.request.PostRequestDto;
import company.bloggingplatform.dto.request.UserRequestDto;
import company.bloggingplatform.dto.response.PostResponseDto;
import company.bloggingplatform.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAll();

    void delete(Long id);

    void update(Long id, UserRequestDto requestDto);

    UserResponseDto findById(Long id);

    void changePassword(ChangePasswordRequestDto dto);

}
