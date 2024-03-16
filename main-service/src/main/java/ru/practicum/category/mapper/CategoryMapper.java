package ru.practicum.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.model.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);

    @Mapping(target = "id", ignore = true)
    Category newCategoryDtoToCategory(NewCategoryDto newCategoryDto);

    Category categoryDtoToCategory(CategoryDto categoryDto);
}