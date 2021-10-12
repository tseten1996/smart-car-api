package com.smartcar.api.service;

import com.smartcar.api.client.GmApiClient;
import com.smartcar.api.client.response.GmEnergy.GmEnergyResponse;
import com.smartcar.api.client.response.GmEnergy.GmEnergyResponseData;
import com.smartcar.api.client.response.GmEngine.GmEngineResponse;
import com.smartcar.api.client.response.GmEngine.GmActionResult;
import com.smartcar.api.client.response.GmSecurityStatus.GmSecurityStatusResponse;
import com.smartcar.api.client.response.GmSecurityStatus.GmSecurityStatusResponseData;
import com.smartcar.api.client.response.GmSecurityStatus.GmSecurityStatusResponseDoorData;
import com.smartcar.api.client.response.GmVehicleInfo.GmVehicleInfoResponse;
import com.smartcar.api.client.response.GmVehicleInfo.GmVehicleInfoData;
import com.smartcar.api.web.dto.ScEnergyResponse;
import com.smartcar.api.web.dto.ScEngineResponse;
import com.smartcar.api.web.dto.ScSecurityStatusResponse;
import com.smartcar.api.web.dto.ScSecurityStatusResponse.ScSecurityDoorResponse;
import com.smartcar.api.web.dto.ScVehicleInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final GmApiClient gmApiClient;

    public ScVehicleInfoResponse getVehicleInformation(String vehicleId) {
        GmVehicleInfoResponse gmResponse = gmApiClient.getVehicleInformation(vehicleId);
        GmVehicleInfoData vehicleData = gmResponse.getData();
        ScVehicleInfoResponse scResponse = ScVehicleInfoResponse.builder()
                .color(vehicleData.getColor() != null ? vehicleData.getColor().getValue() : null)
                .driveTrain(vehicleData.getDriveTrain() != null ? vehicleData.getDriveTrain().getValue() : null)
                .vin(vehicleData.getVin() != null ? vehicleData.getVin().getValue() : null)
                .build();
        if(vehicleData.getFourDoorSedan() != null && vehicleData.getFourDoorSedan().getValue().equalsIgnoreCase("True")) {
            scResponse.setDoorCount(4);
        } else if (vehicleData.getTwoDoorCoupe() != null && vehicleData.getTwoDoorCoupe().getValue().equalsIgnoreCase("True")) {
            scResponse.setDoorCount(2);
        }
        return scResponse;
    }

    public ScSecurityStatusResponse getSecurityStatus(String vehicleId) {
        GmSecurityStatusResponse gmResponse =  gmApiClient.getSecurityStatus(vehicleId);
        GmSecurityStatusResponseData securityData = gmResponse.getData();
        ArrayList<GmSecurityStatusResponseDoorData> gmDoorDataList = securityData.getDoors().getValues();
        ArrayList<ScSecurityDoorResponse> scDoorDataList = gmDoorDataList.stream()
                    .map(door ->
                            ScSecurityDoorResponse.builder()
                                    .location(door.getLocation().getValue())
                                    .locked(door.getLocked().getValue().equalsIgnoreCase("True"))
                                    .build()
                    ).collect(Collectors.toCollection(ArrayList::new));
        ScSecurityStatusResponse scResponse = ScSecurityStatusResponse.builder()
                .doorSecurityResponses(scDoorDataList)
                .build();
        return scResponse;
    }

    public ScEnergyResponse getEnergyStatus(String vehicleId, String energyType) {
        GmEnergyResponse gmResponse = gmApiClient.getEnergyStatus(vehicleId);
        GmEnergyResponseData energyData = gmResponse.getData();
        Double energyLevel = null;

        if(energyType.equals("FUEL") && energyData.getTankLevel() != null && energyData.getTankLevel().getType().equals("Number")) {
            energyLevel = Double.parseDouble(energyData.getTankLevel().getValue());
        }

        if (energyType.equals("BATTERY") && energyData.getBatteryLevel() != null && energyData.getBatteryLevel().getType().equals("Number")) {
            energyLevel = Double.parseDouble(energyData.getBatteryLevel().getValue());
        }

        ScEnergyResponse scResponse = ScEnergyResponse.builder()
                .percent(energyLevel)
                .build();
        return scResponse;
    }

    public ScEngineResponse commitEngineAction(String vehicleId, String actionEngine) {
        GmEngineResponse gmResponse =  gmApiClient.commitEngineAction(vehicleId, actionEngine + "_VEHICLE");
        GmActionResult engineResult = gmResponse.getActionResult();
        String engineResultStatus = engineResult.getStatus();
        if(engineResultStatus.equals("EXECUTED")) {
            engineResultStatus = "success";
        } else if (engineResultStatus.equals("FAILED")) {
            engineResultStatus = "error";
        }
        ScEngineResponse scResponse = ScEngineResponse.builder()
                .status(engineResultStatus)
                .build();
        return scResponse;
    }

}
