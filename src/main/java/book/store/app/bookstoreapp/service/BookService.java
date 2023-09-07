package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.book.BookResponseDto;
import book.store.app.bookstoreapp.dto.book.BookSearchParametersDto;
import book.store.app.bookstoreapp.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto product);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto findById(Long id);

    BookResponseDto update(Long id, CreateBookRequestDto requestDto);

    List<BookResponseDto> search(BookSearchParametersDto bookSearchParameters);

    void delete(Long id);
}
