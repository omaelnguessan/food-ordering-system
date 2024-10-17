package com.food.ordering.system.order.service.domain.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class OrderAddress {
    @Max(value = 50)
    @NotNull
    private final String street;
    @Max(value = 10)
    @NotNull
    private final String postalCode;
    @Max(value = 50)
    @NotNull
    private final String city;
}
