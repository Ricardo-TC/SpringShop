package com.store.OnlineShop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.store.OnlineShop.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>{
	
	@Modifying
	@Query(value = "call updateDepartment(:id,:name)", nativeQuery = true)
	public void updateDepartment(@Param("id") int id,@Param("name") String name);
	
	@Query(value = "select dep_id,dep_name from department where dep_name=:dep_name", nativeQuery = true)
	public Department getByName(@Param("dep_name") String dep_name);

}
