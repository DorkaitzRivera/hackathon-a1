package com.api.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private UUID id;
    private String name;
    private String brand;
    private BigDecimal price;
    private String operating_system;
    private LocalDateTime release_date;
    private String image_url;
}
