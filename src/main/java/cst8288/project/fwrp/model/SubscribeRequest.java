package cst8288.project.fwrp.model;

/**
 * SubscribeRequest class represents a request to subscribe to an item.
 * used by Consumer and Charitable Organization.
 */
public record SubscribeRequest(Long itemId, Long userId) {
		public SubscribeRequest {
				if (itemId == null || userId == null) {
						throw new IllegalArgumentException("itemId and userId must not be null");
				}
		}
}
