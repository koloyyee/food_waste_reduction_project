package cst8288.project.fwrp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import cst8288.project.fwrp.dao.ItemDaoImpl;
import cst8288.project.fwrp.dao.SubscriptionDao;
import cst8288.project.fwrp.model.Item;
import cst8288.project.fwrp.model.SubscribedItem;
import cst8288.project.fwrp.model.TransactionType;
import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;

/*************************************************************************************************************
 * File Name: ItemService.java Description: This file contains the ItemService
 * class. This class is used to perform CRUD operations on the Item table in the
 * database.
 * <hr>
 * An abstract layer between DAO and controller. <br>
 * 
 * Consumer allowed methods: getSurplusItems, getItemById, orderSurplusItems
 * <br>
 * Charitable Organization allowed methods: getDonationItems,
 * getDonationItemById, orderDonationItems <br>
 * Retailer allowed methods: All methods<br>
 * 
 ************************************************************************************************************/
public class ItemService {
	private static Logger log = LoggerFactory.getLogger();
	private ItemDaoImpl itemDaoImpl;
	private SubscriptionDao subscriptionDao;


	public ItemService() {
		this.itemDaoImpl = new ItemDaoImpl();
		this.subscriptionDao = new SubscriptionDao();
	}
	
	/**
	 * Get all items
	 * 
	 * @return List of items
	 * @throws SQLException
	 */
	public List<Item> getItems() throws SQLException {
		return itemDaoImpl.findAll();
	}

	/**
	 * Get all surplus items
	 * 
	 * @return List of surplus items
	 * @throws SQLException
	 */
	public List<Item> getSurplusItems() throws SQLException {
		return itemDaoImpl.findSurplus();
	}

	/**
	 * Get any item by id
	 * 
	 * Controller needs to check
	 * 
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
	public int orderSurplusItem(Long userId, Long itemId, int quantity, double discountedPrice) throws SQLException {
		return itemDaoImpl.orderItem(userId, itemId, quantity, discountedPrice, TransactionType.Purchased);
	}

	/**
	 * Retailer allowed method Toggle item donation status
	 * 
	 * @param item Item
	 * @return int
	 */
	public int toggleDonationItem(Item item) throws SQLException {
		return itemDaoImpl.update(item.getId(), item);
	}

	/**
	 * Retailer allowed method Mark surplus item Trigger Email notification from
	 * Retailer to Consumer
	 */
	public int markSurplusItem(Item item) throws SQLException {
		// update the item with surplus status as true

		int updatedRow = itemDaoImpl.update(item.getId(), item);


		if (updatedRow == 1) {
			Optional<SubscribedItem> items =  subscriptionDao.find(item.getId());
			if (items.isPresent()) {
//			 send email notification to consumer
//			 email successfully sent update notification table
				items.get().notifySubscribers("Discounted Item", "Discounted Item: " + item.getName() + " is now on sale at " + item.getDiscountRate() * 100 + "% off.");
			}

		}

		return updatedRow;
	}

	public int unmarkSurplusItem(Item item) throws SQLException {
		return itemDaoImpl.update(item.getId(), item);
	}

	/**
	 * Retailer allowed method Toggle item available status
	 * 
	 * @param item Item
	 * @return int
	 */
	public int toggleAvailableItem(Item item) throws SQLException {
		return itemDaoImpl.update(item.getId(), item);
	}

	public int subscribeItem(Long itemId, Long userId) throws SQLException {
			var item = itemDaoImpl.find(itemId).orElse(null);
				if (item == null) {
					throw new RuntimeException("User or Item not found");
				} 
				return subscriptionDao.save(item.getId(), userId);
	}

	public int unsubscribeItem(Long itemId, Long userId) throws SQLException {
			var item = itemDaoImpl.find(itemId).orElse(null);
				if (item == null) {
					throw new RuntimeException("User or Item not found");
				} 
				return subscriptionDao.delete(item.getId(), userId);
	}

	public SubscribedItem findSubscribedItem(Long itemId) throws SQLException {
		var item =  subscriptionDao.find(itemId);
		return item.orElseThrow(() -> new RuntimeException("Item not found"));
	}

	public List<Item> getSubscribedItems(Long userId) throws SQLException {
		return	subscriptionDao.findUserSubcribed(userId);
	}

	public int deleteItem(Long itemId) throws SQLException {
		return itemDaoImpl.delete(itemId);
	}

	public Item create(Item item) throws SQLException {
        return itemDaoImpl.save(item);
	}

	public int updateItem(Item item) throws SQLException {
		return itemDaoImpl.update(item.getId(), item);
	}
	
}
