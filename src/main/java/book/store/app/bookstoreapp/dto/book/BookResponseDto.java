package book.store.app.bookstoreapp.dto.book;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
    private List<Long> categoryIds;
}
