package book.store.app.bookstoreapp.controller;

import book.store.app.bookstoreapp.dto.book.BookDtoNoCategory;
import book.store.app.bookstoreapp.dto.category.CategoryResponseDto;
import book.store.app.bookstoreapp.dto.category.CreateCategoryRequestDto;
import book.store.app.bookstoreapp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "Managing categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all categories",
            description = "Get a list of all available categories in the library's collection")
    public List<CategoryResponseDto> getAllCategories(@ParameterObject Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new category. <ONLY FOR ADMIN ROLE>",
            description
                    = "Add a new category to the library's collection with the provided details")
    @ApiResponse(responseCode = "201", description = "Category created",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponseDto.class))})
    public CategoryResponseDto creatCategory(@RequestBody @Valid
                                             CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update category. <ONLY FOR ADMIN ROLE>",
            description = "Update information about the specific book category "
                    + "in the library's collection")
    @ApiResponse(responseCode = "200", description
            = "Category was updated", content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponseDto.class))})
    public CategoryResponseDto updateCategory(@PathVariable Long id,
                                              @RequestBody @Valid
                                              CreateCategoryRequestDto createCategoryRequestDto) {
        return categoryService.update(id, createCategoryRequestDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get category by id",
            description = "Get category in the library's collection by id")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping("/{id}/books")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get list of books by category id",
            description = "Get ist of books from the library's collection by category id")
    public List<BookDtoNoCategory> getBooksByCategoryId(@PathVariable Long id) {
        return categoryService.getBooksByCategoriesId(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete category. <ONLY FOR ADMIN ROLE>",
            description = "Remove category from the library's collection")
    @ApiResponse(responseCode = "204",
            description = "Category deleted")
    public void deleteBook(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
