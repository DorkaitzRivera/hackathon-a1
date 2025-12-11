package com.api.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request para actualizar campos de un dispositivo. Solo se actualizarán los campos presentes en el request.")
public class UpdateDeviceRequest {

    @Schema(description = "Nuevo precio del dispositivo", example = "799.99")
    private BigDecimal price;

}
