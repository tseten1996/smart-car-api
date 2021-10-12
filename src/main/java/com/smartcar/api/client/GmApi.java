package com.smartcar.api.client;

import com.smartcar.api.client.response.GmEnergy.GmEnergyResponse;
import com.smartcar.api.client.response.GmEngine.GmEngineResponse;
import com.smartcar.api.client.response.GmSecurityStatus.GmSecurityStatusResponse;
import com.smartcar.api.client.response.GmVehicleInfo.GmVehicleInfoResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface GmApi {

    @RequestLine("POST /getVehicleInfoService")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    GmVehicleInfoResponse getVehicleInformation(@Param("id") String vehicleId, @Param("responseType") String responseType);

    @RequestLine("POST /getSecurityStatusService")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    GmSecurityStatusResponse getSecurityStatus(@Param("id") String vehicleId, @Param("responseType") String responseType );

    @RequestLine("POST /getEnergyService")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    GmEnergyResponse getEnergyStatus(@Param("id") String vehicleId, @Param("responseType") String responseType );

    @RequestLine("POST /actionEngineService")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    GmEngineResponse commitEngineAction(@Param("id") String vehicleId, @Param("responseType") String responseType, @Param("command") String actionEngine);

}
