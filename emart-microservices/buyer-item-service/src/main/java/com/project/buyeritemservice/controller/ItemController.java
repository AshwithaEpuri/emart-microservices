package com.project.buyeritemservice.controller;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.buyeritemservice.pojo.ItemPojo;
import com.project.buyeritemservice.service.ItemService;

@RestController
@RequestMapping("emart")
@CrossOrigin
///Fetching all Items through an endpoint ///
public class ItemController {
	static Logger LOG = Logger.getLogger(ItemController.class.getClass());
	@Autowired
	ItemService itemService;

	@GetMapping("item/all")
	List<ItemPojo> getAllItems() {
		LOG.info("Entered end point \'emart/item/all \' ");
		LOG.info("Exited end point \'emart/item/all \' ");
		return itemService.getAllItems();
	}
/// Fetching Particular Item through an Id///
	@GetMapping("/item/{itemId}")
	ItemPojo getItem(@PathVariable("itemId") Integer itemId) {
		LOG.info("Entered end point \'emart/item/{itemId} \' ");
		LOG.info("Exited end point \'emart/item/{itemId} \' ");
		return itemService.getItem(itemId);
	}
/// Adding Item ///
	@PostMapping("/items")
	ItemPojo addItem(@RequestBody ItemPojo itemPojo) {
		LOG.info("Entered end point \'emart/items/ \' ");
		LOG.info("Exited end point \'emart/items/ \' ");
		return itemService.addItem(itemPojo);
	}

}
