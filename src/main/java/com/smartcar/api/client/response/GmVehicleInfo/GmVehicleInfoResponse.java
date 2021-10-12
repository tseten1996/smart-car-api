package com.smartcar.api.client.response.GmVehicleInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smartcar.api.client.response.ResponseBase;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class GmVehicleInfoResponse extends ResponseBase {
    private GmVehicleInfoData data;

}
