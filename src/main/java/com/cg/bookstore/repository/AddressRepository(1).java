package com.cg.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bookstore.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
