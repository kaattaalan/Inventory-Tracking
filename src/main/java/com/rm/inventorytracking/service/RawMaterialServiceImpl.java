package com.rm.inventorytracking.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rm.inventorytracking.domain.RawMaterial;
import com.rm.inventorytracking.repository.RawMaterialRepository;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {

	@Autowired
	private RawMaterialRepository rawMaterialrepo;

	@Override
	public void addRawMaterial(RawMaterial rawMaterial) {
		rawMaterialrepo.save(rawMaterial);
	}
	
	
	Iterable<RawMaterial> getAllRawMaterials() {
		return rawMaterialrepo.findAll();

	}
	
	@Override
	public List<RawMaterial> getRawMaterialList() {
		List<RawMaterial> rawList = new ArrayList<RawMaterial>();
		Iterator iterator = getAllRawMaterials().iterator();

		while (iterator.hasNext()) {
			RawMaterial raw = (RawMaterial) iterator.next();
			rawList.add(raw);
		}
		return rawList;
	}

	@Override
	public void deleteRawMaterial(Long id) {
		rawMaterialrepo.delete(id);
	}

	@Override
	public RawMaterial getRawMaterialById(Long id) {
		return rawMaterialrepo.findOne(id);
	}

	@Override
	public boolean existsByType(String type) {
		return rawMaterialrepo.existsByType(type);
	}

}
