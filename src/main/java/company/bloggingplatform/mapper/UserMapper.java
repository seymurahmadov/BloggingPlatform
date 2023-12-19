package company.bloggingplatform.mapper;

import company.bloggingplatform.dto.request.CommentRequestDto;
import company.bloggingplatform.dto.request.JwtSignInRequestDto;
import company.bloggingplatform.dto.request.UserRequestDto;
import company.bloggingplatform.dto.response.CommentResponseDto;
import company.bloggingplatform.dto.response.UserResponseDto;
import company.bloggingplatform.entity.CommentEntity;
import company.bloggingplatform.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {
    UserEntity mapRequestDtoToEntity(UserRequestDto userRequest);

    List<UserResponseDto> mapEntityListToResponseList(List<UserEntity> userEntities);

    UserResponseDto mapEntityToResponseDto(UserEntity userEntity);

    void update(@MappingTarget UserEntity userEntity, UserRequestDto userRequestDto);

}
