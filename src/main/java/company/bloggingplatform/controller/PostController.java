package company.bloggingplatform.controller;

import company.bloggingplatform.dto.request.PostRequestDto;
import company.bloggingplatform.dto.response.PostResponseDto;
import company.bloggingplatform.exception.handler.SuccessDetails;
import company.bloggingplatform.service.impl.PostServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
@SecurityRequirement(name = "Bearer Authentication")
public class PostController {

    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<SuccessDetails<List<PostResponseDto>>> getAll(){
        return  ResponseEntity.ok(new SuccessDetails<>(postService.getAll(), HttpStatus.OK.value(), true));

    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PostRequestDto requestDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder errorMsg = new StringBuilder("Validation error(s): ");
            for (FieldError error : errors) {
                errorMsg.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(errorMsg);
        }

        postService.create(requestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Post created succesfully",HttpStatus.OK.value(), true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDetails<PostResponseDto>> findById(@PathVariable Long id){
        return  ResponseEntity.ok(new SuccessDetails<>(postService.findById(id), HttpStatus.OK.value(), true));
    }

    @GetMapping("/search-by/{title}")
    public ResponseEntity<SuccessDetails<List<PostResponseDto>>> findByTitle(@PathVariable String title){
        return  ResponseEntity.ok(new SuccessDetails<>(postService.findByTitle(title), HttpStatus.OK.value(), true));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessDetails<String>> delete(@PathVariable Long id){
        postService.delete(id);
        return ResponseEntity.ok(new SuccessDetails<>("Post deleted Successfully!",HttpStatus.OK.value(),true));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessDetails<String>> update(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.update(id, requestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Post update Successfully!",HttpStatus.OK.value(),true));
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PostRequestDto requestDto,
//                                         @AuthenticationPrincipal UserDetails userDetails) throws NotFoundException {
//
//        // Get the current user's username
//        String username = userDetails.getUsername();
//        // Check if the user is the author of the blog post or has admin role
//        if (postService.isUserAuthorizedToUpdate(id, username)) {
//            // Perform the update operation
//            postService.update(id, requestDto);
//            return ResponseEntity.ok("Blog post updated successfully.");
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized to update the blog post.");
//        }
    }


