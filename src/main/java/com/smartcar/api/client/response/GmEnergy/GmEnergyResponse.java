package com.smartcar.api.client.response.GmEnergy;

import com.smartcar.api.client.response.ResponseBase;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GmEnergyResponse extends ResponseBase {
    private GmEnergyResponseData data;

}
