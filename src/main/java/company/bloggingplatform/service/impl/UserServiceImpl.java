package company.bloggingplatform.service.impl;

import company.bloggingplatform.dto.request.ChangePasswordRequestDto;
import company.bloggingplatform.dto.request.UserRequestDto;
import company.bloggingplatform.dto.response.UserResponseDto;
import company.bloggingplatform.entity.UserEntity;
import company.bloggingplatform.exception.MethodArgumentNotValidException;
import company.bloggingplatform.mapper.UserMapper;
import company.bloggingplatform.repository.UserRepository;
import company.bloggingplatform.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<UserEntity> all = userRepository.findAll();
        return userMapper.mapEntityListToResponseList(all);

    }

    @Override
    public UserResponseDto findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        return userMapper.mapEntityToResponseDto(userEntity);
    }

    @Override
    public void delete(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        userRepository.delete(userEntity);
    }

    @Override
    public void update(Long id, UserRequestDto requestDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        userMapper.update(userEntity, requestDto);
        userRepository.save(userEntity);
    }

    @Override
    public void changePassword(ChangePasswordRequestDto dto) {
        UserEntity usersEntityByMail = userRepository.findUsersEntityByMail(dto.getMail());

        if (passwordEncoder.matches(dto.getOldPassword(), usersEntityByMail.getPassword())) {
            usersEntityByMail.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        }else {
            throw new MethodArgumentNotValidException("Invalid credentials");
        }

    }


}
