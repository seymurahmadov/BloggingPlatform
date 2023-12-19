package company.bloggingplatform.controller;

import company.bloggingplatform.dto.request.CommentRequestDto;
import company.bloggingplatform.dto.response.CommentResponseDto;
import company.bloggingplatform.exception.handler.SuccessDetails;
import company.bloggingplatform.service.impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
@SecurityRequirement(name = "Bearer Authentication")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<SuccessDetails<List<CommentResponseDto>>> getAll() {
        return  ResponseEntity.ok(new SuccessDetails<>(commentService.getAll(), HttpStatus.OK.value(), true));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CommentRequestDto commentRequestDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder errorMsg = new StringBuilder("Validation error(s): ");
            for (FieldError error : errors) {
                errorMsg.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(errorMsg);
        }

        commentService.create(commentRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Comment created succesfully",HttpStatus.OK.value(), true));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessDetails<String>> delete(@PathVariable Long id){
        commentService.delete(id);
        return ResponseEntity.ok(new SuccessDetails<>("Comment deleted Successfully!",HttpStatus.OK.value(),true));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessDetails<String>> update(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto){
        commentService.update(id, commentRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Comment updated Successfully!",HttpStatus.OK.value(),true));

    }
}
