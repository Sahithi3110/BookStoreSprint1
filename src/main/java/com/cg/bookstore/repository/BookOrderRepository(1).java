package com.cg.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bookstore.entities.BookOrder;

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer>{

}
