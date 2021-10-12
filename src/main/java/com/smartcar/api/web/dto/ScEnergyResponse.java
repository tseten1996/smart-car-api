package com.smartcar.api.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Smart Car API Energy Response Object Class")
public class ScEnergyResponse {
    @ApiModelProperty(notes = "Battery or Fuel Percentage", example = "77.7")
    private Double percent;
}
