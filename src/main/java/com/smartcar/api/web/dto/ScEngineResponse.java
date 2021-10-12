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
@ApiModel(description ="Smart Car API Engine Response Object Class")
public class ScEngineResponse {
    @ApiModelProperty(notes = "Engine Status", example = "success|failure")
    private String status;
}
