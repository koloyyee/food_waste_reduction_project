package cst8288.project.fwrp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import cst8288.project.fwrp.dao.ItemDaoImpl;
import cst8288.project.fwrp.model.Item;
import cst8288.project.fwrp.model.TransactionType;

/*************************************************************************************************************
 * File Name: ItemService.java Description: This file contains the ItemService
 * class. This class is used to perform CRUD operations on the Item table in the
 * database.
 * <hr>
 * An abstract layer between DAO and controller. <br>
 * 
 * Consumer allowed methods: getSurplusItems, getItemById, orderSurplusItems <br>
 * Charitable Organization allowed methods: getDonationItems, getDonationItemById, orderDonationItems <br>
 * Retailer allowed methods: All methods<br>
 * 
 ************************************************************************************************************/
public class ItemService {
	private ItemDaoImpl itemDaoImpl;
	
	public ItemService() {
		this.itemDaoImpl = new ItemDaoImpl();
	}
	
	/**
	 * Get all surplus items
	 * 
	 * @return List of surplus items
	 * @throws SQLException 
	 */
	public List<Item>getSurplusItems() throws SQLException {
		return itemDaoImpl.findSurplus();
	}
	
	/**
	 * Get any item by id
	 * 
	 * Controller needs to check 
	 * @param id Item id
	 * @return Item or empty item.
	 * @throws SQLException
	 */
	public Optional<Item> getItemById(Long id) throws SQLException {
		return itemDaoImpl.find(id);
	}
	
	/**
	 * Order surplus items
	 * 
	 * @param id       Item id
	 * @param quantity Quantity
	 * @return boolean
	 * @throws SQLException
	 */
	public int orderSurplusItem(Long userId, Long itemId, int quantity, double itemPrice) throws SQLException {
		return itemDaoImpl.orderItem(userId, itemId, quantity, itemPrice, TransactionType.Purchased );
	}
}
