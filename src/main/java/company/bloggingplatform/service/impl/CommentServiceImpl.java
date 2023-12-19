package company.bloggingplatform.service.impl;

import company.bloggingplatform.dto.request.CommentRequestDto;
import company.bloggingplatform.dto.response.CommentResponseDto;
import company.bloggingplatform.entity.CommentEntity;
import company.bloggingplatform.entity.PostEntity;
import company.bloggingplatform.entity.UserEntity;
import company.bloggingplatform.exception.MethodArgumentNotValidException;
import company.bloggingplatform.mapper.CommentMapper;
import company.bloggingplatform.repository.CommentRepository;
import company.bloggingplatform.repository.PostRepository;
import company.bloggingplatform.repository.UserRepository;
import company.bloggingplatform.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentResponseDto> getAll() {
        List<CommentEntity> all = commentRepository.findAll();
        return commentMapper.mapEntityListToResponseList(all);
    }

    @Override
    public void create(CommentRequestDto requestDto){
        if(requestDto == null){
            throw new MethodArgumentNotValidException(("Data Arguments is not valid!"));
        }

        UserEntity userEntity = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        PostEntity postEntity = postRepository.findById(requestDto.getPostId()).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));

        CommentEntity commentEntity = commentMapper.mapRequestDtoToEntity(requestDto);
        commentEntity.setUserEntity(userEntity);
        commentEntity.setPostEntity(postEntity);
        commentRepository.save(commentEntity);
    }

    @Override
    public void delete(Long id){
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        commentRepository.delete(commentEntity);
    }

    @Override
    public void update(Long id, CommentRequestDto commentRequestDto){
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        commentMapper.update(commentEntity, commentRequestDto);
        commentRepository.save(commentEntity);
    }
}
