package com.store.OnlineShop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.OnlineShop.model.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer>{
	
	@Modifying
	@Query(value = "call updateDepartment(:id,:name)", nativeQuery = true)
	public void updateDepartment(@Param("id") int id,@Param("name") String name);

}
