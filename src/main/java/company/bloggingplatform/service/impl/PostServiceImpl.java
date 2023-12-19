package company.bloggingplatform.service.impl;

import company.bloggingplatform.dto.request.PostRequestDto;
import company.bloggingplatform.dto.response.PostResponseDto;
import company.bloggingplatform.entity.PostEntity;
import company.bloggingplatform.entity.UserEntity;
import company.bloggingplatform.enumuration.Role;
import company.bloggingplatform.exception.MethodArgumentNotValidException;
import company.bloggingplatform.mapper.PostMapper;
import company.bloggingplatform.repository.CommentRepository;
import company.bloggingplatform.repository.PostRepository;
import company.bloggingplatform.repository.UserRepository;
import company.bloggingplatform.service.PostService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository,
                           CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<PostResponseDto> getAll(){

        List<PostEntity> all = postRepository.findAll();

        return postMapper.mapEntityListToResponseList(all);
    }

    @Override
    public void create (PostRequestDto requestDto){
        if(requestDto == null){
            throw new MethodArgumentNotValidException(("Data Arguments is not valid!"));
        }
        UserEntity userEntity = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new NoSuchElementException("User not found with ID: " + requestDto.getUserId()));
        PostEntity postEntity = postMapper.mapRequestDtoToEntity(requestDto);
        postEntity.setUserEntity(userEntity);

        postRepository.save(postEntity);
    }

    @Override
    public PostResponseDto findById(Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
       return postMapper.mapEntityToResponseDto(postEntity);
    }

    @Override
    public List<PostResponseDto> findByTitle(String title) {
        List<PostEntity> byTitle = postRepository.findByTitle(title);
       return postMapper.mapEntityListToResponseList(byTitle);
    }

    @Override
    public void delete(Long id){

        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        postRepository.delete(postEntity);
    }

    @Override
    public void update(Long id, PostRequestDto requestDto){
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        postMapper.update(postEntity, requestDto);
        postRepository.save(postEntity);
    }



    public boolean isUserAuthorizedToUpdate(Long postId, String mail) throws NotFoundException {
        PostEntity existingBlogPost = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Blog post not found with id: " + postId));

        UserEntity userEntity = existingBlogPost.getUserEntity();
        if (userEntity.getMail().equals(mail)) {
            return true; // User is the author of the blog post
        }

        // Check if the user has admin role
        UserEntity user = userRepository.findUsersEntityByMail(mail);

        return user.getRole().equals(Role.ADMIN);
    }
}
