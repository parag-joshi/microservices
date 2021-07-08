package com.fis.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fis.book.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String>
{
	@Query("select b from Book b where b.bookId = :bookId")
	Optional<Book> findByBookId(String bookId);
	
}
