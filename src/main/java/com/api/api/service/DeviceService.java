package com.api.api.service;

import com.api.api.model.Device;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    private final List<Device> devices = new ArrayList<>();

    public DeviceService() {
        inicializarDevices();
    }

    private void inicializarDevices() {
        crear(new Device(null, "iPhone 15 Pro", "Apple", new BigDecimal("999.99"), "iOS 17",
            LocalDateTime.of(2023, 9, 22, 0, 0), "https://example.com/iphone15pro.jpg"));
        crear(new Device(null, "Samsung Galaxy S24", "Samsung", new BigDecimal("899.99"), "Android 14",
            LocalDateTime.of(2024, 1, 17, 0, 0), "https://example.com/galaxys24.jpg"));
        crear(new Device(null, "Google Pixel 8", "Google", new BigDecimal("699.99"), "Android 14",
            LocalDateTime.of(2023, 10, 12, 0, 0), "https://example.com/pixel8.jpg"));
        crear(new Device(null, "OnePlus 12", "OnePlus", new BigDecimal("799.99"), "Android 14",
            LocalDateTime.of(2024, 1, 23, 0, 0), "https://example.com/oneplus12.jpg"));
        crear(new Device(null, "Xiaomi 14 Pro", "Xiaomi", new BigDecimal("749.99"), "Android 14",
            LocalDateTime.of(2024, 2, 25, 0, 0), "https://example.com/xiaomi14pro.jpg"));
    }

    public List<Device> obtenerTodos() {
        return new ArrayList<>(devices);
    }

    public Optional<Device> obtenerPorId(UUID id) {
        return devices.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    public Optional<Device> editarPrecio(UUID id, BigDecimal nuevoPrecio) {
        return obtenerPorId(id).map(device -> {
            device.setPrice(nuevoPrecio);
            return device;
        });
    }

    public boolean eliminar(UUID id) {
        return devices.removeIf(d -> d.getId().equals(id));
    }

    private Device crear(Device device) {
        device.setId(UUID.randomUUID());
        devices.add(device);
        return device;
    }
}
