/**
 * 
 */
package com.rm.inventorytracking.repository;

import org.springframework.data.repository.CrudRepository;

import com.rm.inventorytracking.domain.PurchaseDetails;

/**
 * @author appu.b
 *
 */
public interface PurchaseDetailsRepository extends CrudRepository<PurchaseDetails, Long> {

}
