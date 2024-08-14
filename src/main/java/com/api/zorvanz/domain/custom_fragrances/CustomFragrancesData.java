package com.api.zorvanz.domain.custom_fragrances;

public record CustomFragrancesData(
        Long customFragranceId,
        Long customerId,
        String name,
        String description,
        Double price,
        String preferences
) {
}
