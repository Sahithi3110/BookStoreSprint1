package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.OrderDetails;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails, Integer> {

	@Query("SELECT od FROM  OrderDetails od")
	public List<OrderDetails> listAllOrders();

	public List<OrderDetails> findByOrderByBook();

	@Query(value = "SELECT book_id FROM review GROUP BY book_id ORDER BY count(*) desc LIMIT 1", nativeQuery = true)
	public int listBestSellingBook();

}
