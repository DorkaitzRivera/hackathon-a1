package com.api.api.controller;

import com.api.api.model.Device;
import com.api.api.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
@CrossOrigin(origins = "*")
@Tag(name = "Dispositivos", description = "API para gestión del catálogo de dispositivos móviles")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Operation(summary = "Listar dispositivos",
               description = "Retorna la lista completa de dispositivos del catálogo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de dispositivos obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<Device>> listarDispositivos() {
        return ResponseEntity.ok(deviceService.obtenerTodos());
    }

    @Operation(summary = "Obtener dispositivo por ID",
               description = "Retorna el detalle de un dispositivo específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dispositivo encontrado"),
        @ApiResponse(responseCode = "404", description = "Dispositivo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Device> obtenerDispositivo(
            @Parameter(description = "ID del dispositivo")
            @PathVariable UUID id) {
        return deviceService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Editar precio de dispositivo",
               description = "Actualiza el precio de un dispositivo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Precio actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Dispositivo no encontrado")
    })
    @PatchMapping("/{id}/precio")
    public ResponseEntity<Device> editarPrecio(
            @Parameter(description = "ID del dispositivo")
            @PathVariable UUID id,
            @Parameter(description = "Nuevo precio del dispositivo")
            @RequestBody Map<String, BigDecimal> precio) {
        return deviceService.editarPrecio(id, precio.get("precio"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar dispositivo",
               description = "Elimina un dispositivo del catálogo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Dispositivo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Dispositivo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDispositivo(
            @Parameter(description = "ID del dispositivo a eliminar")
            @PathVariable UUID id) {
        if (deviceService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
