package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.practicum.RequestDto;
import ru.practicum.model.Request;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RequestMapper {

    @Mapping(source = "app.name", target = "app")
    RequestDto toRequestDto(Request request);

    @Mapping(source = "app", target = "app.name")
    Request toRequest(RequestDto requestDto);
}
