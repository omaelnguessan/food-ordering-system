package com.food.ordering.system.order.service.domain.port.output.message.publisher.restaurantapproval;

import com.food.ordering.system.domain.event.published.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
