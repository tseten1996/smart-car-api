package com.smartcar.api.client.response.GmEnergy;

import com.smartcar.api.client.response.ResponseBaseData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GmEnergyResponseData {
    private ResponseBaseData tankLevel;
    private ResponseBaseData batteryLevel;
}
