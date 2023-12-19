package company.bloggingplatform.mapper;

import company.bloggingplatform.dto.request.PostRequestDto;
import company.bloggingplatform.dto.response.PostResponseDto;
import company.bloggingplatform.entity.PostEntity;
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
public interface PostMapper {

    PostEntity mapRequestDtoToEntity(PostRequestDto postRequestDto);

    List<PostResponseDto> mapEntityListToResponseList(List<PostEntity> postEntities);

    PostResponseDto mapEntityToResponseDto(PostEntity postEntity);

    void update(@MappingTarget PostEntity postEntity, PostRequestDto postRequestDto);

}
