package com.smartcar.api.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Smart Car API Security Object Class")
public class ScSecurityStatusResponse {
    @ApiModelProperty(notes = "Security response for list of doors", example = "\"doorSecurityResponses\":[{\"location\":\"frontLeft\",\"locked\":true},{\"location\":\"backRight\",\"locked\":true},{\"location\":\"backLeft\",\"locked\":true},{\"location\":\"frontRight\",\"locked\":false}]")
    private ArrayList<ScSecurityDoorResponse> doorSecurityResponses;

    @Data
    @Builder
    @ApiModel(description = "Smart Car API Security Door Response Object Class")
    public static class ScSecurityDoorResponse {
        @ApiModelProperty(notes = "door location on vehicle", example = "frontLeft")
        private String location;
        @ApiModelProperty(notes = "vehicle door is locked or not", example = "True")
        private Boolean locked;
    }
}
