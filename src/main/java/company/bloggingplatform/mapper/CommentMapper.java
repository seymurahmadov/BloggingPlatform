package company.bloggingplatform.mapper;

import company.bloggingplatform.dto.request.CommentRequestDto;
import company.bloggingplatform.dto.response.CommentResponseDto;
import company.bloggingplatform.entity.CommentEntity;
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
public interface CommentMapper {

    CommentEntity mapRequestDtoToEntity(CommentRequestDto commentRequestDto);

    List<CommentResponseDto> mapEntityListToResponseList(List<CommentEntity> commentEntities);

    CommentResponseDto mapEntityToResponseDto(CommentEntity commentEntity);

    void update(@MappingTarget CommentEntity commentEntity, CommentRequestDto commentRequestDto);
}
