package com.smartcar.api.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Smart Car API Engine Request Object Class")
public class ScEngineRequest {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "START|STOP")
    @ApiModelProperty(notes = "Start or Stop Engine", example = "START|STOP")
    private String action;
}
