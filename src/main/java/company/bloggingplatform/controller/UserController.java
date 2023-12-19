package company.bloggingplatform.controller;

import company.bloggingplatform.dto.request.ChangePasswordRequestDto;
import company.bloggingplatform.dto.request.UserRequestDto;
import company.bloggingplatform.dto.response.UserResponseDto;
import company.bloggingplatform.exception.MethodArgumentNotValidException;
import company.bloggingplatform.exception.handler.SuccessDetails;
import company.bloggingplatform.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SuccessDetails<List<UserResponseDto>>> getAll(){
        return  ResponseEntity.ok(new SuccessDetails<>(userService.getAll(), HttpStatus.OK.value(), true));

    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDetails<UserResponseDto>> findById(@PathVariable Long id){
        return  ResponseEntity.ok(new SuccessDetails<>(userService.findById(id), HttpStatus.OK.value(), true));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessDetails<String>> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok(new SuccessDetails<>("User deleted Successfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessDetails<String>> update(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        userService.update(id, requestDto);
        return ResponseEntity.ok(new SuccessDetails<>("User update Successfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/password-update")
    public ResponseEntity<SuccessDetails<String>> changePassword(@Valid @RequestBody ChangePasswordRequestDto changePasswordRequest) throws MethodArgumentNotValidException {
        userService.changePassword(changePasswordRequest);
        return ResponseEntity.ok(new SuccessDetails<>("Password changed  successfully!", HttpStatus.OK.value(), true));
    }
}
