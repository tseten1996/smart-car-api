package com.smartcar.api.client.response.GmVehicleInfo;

import com.smartcar.api.client.response.ResponseBaseData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GmVehicleInfoData {
    private ResponseBaseData vin;
    private ResponseBaseData color;
    private ResponseBaseData fourDoorSedan;
    private ResponseBaseData twoDoorCoupe;
    private ResponseBaseData driveTrain;
}
