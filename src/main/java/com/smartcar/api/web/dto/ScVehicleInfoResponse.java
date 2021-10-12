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
@ApiModel(description = "Smart Car API Vehicle Info Object Class")
public class ScVehicleInfoResponse {
    @ApiModelProperty(notes = "Vehicle Identification Number", example = "1213231")
    private String vin;
    @ApiModelProperty(notes = "Vehicle Color", example = "Metallic Silver")
    private String color;
    @ApiModelProperty(notes = "Vehicle Door Count", example = "4")
    private Integer doorCount;
    @ApiModelProperty(notes = "Vehicle Drive Train", example = "v8")
    private String driveTrain;
}
