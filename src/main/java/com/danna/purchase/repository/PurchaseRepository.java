package com.danna.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danna.purchase.model.Purchase;

/**
 * @author Cristiane Danna
 */

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {	
}
