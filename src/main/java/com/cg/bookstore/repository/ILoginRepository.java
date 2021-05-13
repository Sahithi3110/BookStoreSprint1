package com.cg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.UserClass;

@Repository
public interface ILoginRepository extends JpaRepository<UserClass, Integer>{

	
	@Query(value="insert into UserClass values(:user)",nativeQuery=true)
	public Optional<UserClass> addUser(UserClass user);
    
    
    @Query(value="delete from UserClass l where l=(:user)",nativeQuery=true)
	public List<UserClass>  removeUser(UserClass user);
    
    
    @Query(value="select count(user_id) from User_class",nativeQuery=true)
	public int userCount();
	
	
	
	/*public UserClass addUser(UserClass user);
	public UserClass removeUser(UserClass user);
	public UserClass validateUser(UserClass user);*/
}
