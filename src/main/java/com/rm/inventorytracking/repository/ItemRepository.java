package com.rm.inventorytracking.repository;

import com.rm.inventorytracking.domain.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * dataya erişim objemiz
 */

public interface ItemRepository extends CrudRepository<Item,Long> {
}
