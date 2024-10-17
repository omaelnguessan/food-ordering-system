package com.food.ordering.system.order.service.domain.dto.track;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Setter
public class TrackOrderQuery {
    @NotNull
    private final UUID orderTrackingId;

}