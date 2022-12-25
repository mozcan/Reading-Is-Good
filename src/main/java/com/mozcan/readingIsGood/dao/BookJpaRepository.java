package com.mozcan.readingIsGood.dao;

import com.mozcan.readingIsGood.model.entity.BookEntity;
import com.mozcan.readingIsGood.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM BookEntity u WHERE u.id=?1")
    Boolean isBookExist(Long id);

    @Query("SELECT u FROM BookEntity u WHERE u.id IN (?1) AND u.stock > 0")
    List<BookEntity> getBookList(List<Long> bookIds);

    @Modifying
    @Query("UPDATE BookEntity u SET u.stock=u.stock-1 WHERE u.id IN (?1)")
    void updateBookStock(List<Long> bookIds);
}
