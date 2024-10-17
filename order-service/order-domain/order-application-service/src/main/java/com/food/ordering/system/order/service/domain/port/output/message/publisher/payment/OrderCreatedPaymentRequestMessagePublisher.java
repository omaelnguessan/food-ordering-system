package com.food.ordering.system.order.service.domain.port.output.message.publisher.payment;

import com.food.ordering.system.domain.event.published.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
