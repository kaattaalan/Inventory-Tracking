/**
 * 
 */
package com.rm.inventorytracking.repository;

import org.springframework.data.repository.CrudRepository;

import com.rm.inventorytracking.domain.RawMaterial;

/**
 * @author appu.b
 *
 */
public interface RawMaterialRepository extends CrudRepository<RawMaterial, Long> {
}
