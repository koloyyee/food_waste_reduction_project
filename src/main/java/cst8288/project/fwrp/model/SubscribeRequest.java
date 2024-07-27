package cst8288.project.fwrp.model;

public record SubscribeRequest(Long itemId, Long userId) {
		public SubscribeRequest {
				if (itemId == null || userId == null) {
						throw new IllegalArgumentException("itemId and userId must not be null");
				}
		}
}
