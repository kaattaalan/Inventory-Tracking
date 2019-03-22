package com.rm.inventorytracking.service;

import java.util.List;

import com.rm.inventorytracking.domain.RawMaterial;

public interface RawMaterialService {

	void addRawMaterial(RawMaterial rawMaterial);
	
	List<RawMaterial> getRawMaterialList();
}
