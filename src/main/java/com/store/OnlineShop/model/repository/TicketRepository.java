package com.store.OnlineShop.model.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.store.OnlineShop.model.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer>{
	
//	@Modifying
//	@Query(value = "call newTicket()",nativeQuery = true)
//	public Ticket newTicket();
	
	@Procedure(procedureName = "newTicket", outputParameterName = "id")
	public int newTicket();
	
//	@Query(value = "call getLastTicket()",nativeQuery = true)
//	public int getLastTicket();
	
	@Procedure(procedureName = "totalPurchase", outputParameterName = "total")
	public float totalPurchase(@Param("ticket") Integer in);
	
	
	

}
