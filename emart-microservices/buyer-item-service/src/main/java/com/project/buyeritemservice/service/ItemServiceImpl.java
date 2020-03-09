package com.project.buyeritemservice.service;

import java.util.ArrayList;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.buyeritemservice.dao.ItemDao;
import com.project.buyeritemservice.entity.CategoryEntity;
import com.project.buyeritemservice.entity.ItemEntity;
import com.project.buyeritemservice.entity.SellerSignupEntity;
import com.project.buyeritemservice.entity.SubCategoryEntity;
import com.project.buyeritemservice.pojo.ItemPojo;
import com.project.buyeritemservice.pojo.SellerSignupPojo;
import com.project.buyeritemservice.pojo.SubCategoryPojo;
import com.project.buyeritemservice.pojo.CategoryPojo;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemDao itemDao;
/// Getting Particular Item ///
	@Override
	public ItemPojo getItem(int itemId) {
		ItemPojo itemPojo = null;
		Optional result = itemDao.findById(itemId);
		if (result.isPresent()) {
			ItemEntity itemEntity = (ItemEntity) result.get();
			SubCategoryEntity subCategoryEntity = itemEntity.getSubCategoryId();
			CategoryEntity categoryEntity = subCategoryEntity.getCategoryId();
			SellerSignupEntity sellerSignupEntity = itemEntity.getSellerId();

			CategoryPojo categoryPojo = new CategoryPojo(categoryEntity.getId(), categoryEntity.getName(),
					categoryEntity.getBrief());
			SubCategoryPojo subCategoryPojo = new SubCategoryPojo(subCategoryEntity.getId(),
					subCategoryEntity.getName(), categoryPojo, subCategoryEntity.getBrief(),
					subCategoryEntity.getGstPercent());
			SellerSignupPojo sellerSignupPojo = new SellerSignupPojo(sellerSignupEntity.getId(),
					sellerSignupEntity.getUsername(), sellerSignupEntity.getPassword(), sellerSignupEntity.getCompany(),
					sellerSignupEntity.getBrief(), sellerSignupEntity.getGst(), sellerSignupEntity.getAddress(),
					sellerSignupEntity.getEmail(), sellerSignupEntity.getWebsite(), sellerSignupEntity.getContact());
			itemPojo = new ItemPojo(itemEntity.getId(), itemEntity.getName(), subCategoryPojo, itemEntity.getPrice(),
					itemEntity.getDescription(), itemEntity.getStock(), itemEntity.getRemarks(), itemEntity.getImage(),
					sellerSignupPojo);
		}
		return itemPojo;
	}
/// Retriving All Items///
	@Override
	public List<ItemPojo> getAllItems() {
		List<ItemPojo> allItemPojo = new ArrayList();
		Iterable<ItemEntity> allItemEntity = itemDao.findAll();
		Iterator<ItemEntity> itr = allItemEntity.iterator();

		while (itr.hasNext()) {

			ItemEntity itemEntity = (ItemEntity) itr.next();
			SubCategoryEntity subCategoryEntity = itemEntity.getSubCategoryId();
			CategoryEntity categoryEntity = subCategoryEntity.getCategoryId();
			SellerSignupEntity sellerSignupEntity = itemEntity.getSellerId();

			CategoryPojo categoryPojo = new CategoryPojo(categoryEntity.getId(), categoryEntity.getName(),
					categoryEntity.getBrief());
			SubCategoryPojo subCategoryPojo = new SubCategoryPojo(subCategoryEntity.getId(),
					subCategoryEntity.getName(), categoryPojo, subCategoryEntity.getBrief(),
					subCategoryEntity.getGstPercent());
			SellerSignupPojo sellerSignupPojo = new SellerSignupPojo(sellerSignupEntity.getId(),
					sellerSignupEntity.getUsername(), sellerSignupEntity.getPassword(), sellerSignupEntity.getCompany(),
					sellerSignupEntity.getBrief(), sellerSignupEntity.getGst(), sellerSignupEntity.getAddress(),
					sellerSignupEntity.getEmail(), sellerSignupEntity.getWebsite(), sellerSignupEntity.getContact());

			ItemPojo itemPojo = new ItemPojo(itemEntity.getId(), itemEntity.getName(), subCategoryPojo,
					itemEntity.getPrice(), itemEntity.getDescription(), itemEntity.getStock(), itemEntity.getRemarks(),
					itemEntity.getImage(), sellerSignupPojo);
			allItemPojo.add(itemPojo);
		}

		return allItemPojo;

	}

	public ItemPojo addItem(ItemPojo itemPojo) {
		return null;
	}
}
