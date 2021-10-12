package com.smartcar.api.client;

import com.smartcar.api.client.response.GmEnergy.GmEnergyResponse;
import com.smartcar.api.client.response.GmEngine.GmEngineResponse;
import com.smartcar.api.client.response.GmSecurityStatus.GmSecurityStatusResponse;
import com.smartcar.api.client.response.GmVehicleInfo.GmVehicleInfoResponse;
import com.smartcar.api.client.response.ResponseBase;
import com.smartcar.api.exception.BadRequestException;
import com.smartcar.api.exception.NotFoundException;
import com.smartcar.api.exception.ServiceOutageException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GmApiClient {
    private final GmApi gmApi;
    private static final String RESPONSE_FORMAT = "JSON";

    Logger logger = LoggerFactory.getLogger(GmApiClient.class);

    public GmVehicleInfoResponse getVehicleInformation(String vehicleId) {
        GmVehicleInfoResponse response = gmApi.getVehicleInformation(vehicleId, RESPONSE_FORMAT);
        evaluateGmClientCodes(response);
        return response;
    }

    public GmSecurityStatusResponse getSecurityStatus(String vehicleId) {
        GmSecurityStatusResponse response = gmApi.getSecurityStatus(vehicleId, RESPONSE_FORMAT);
        evaluateGmClientCodes(response);
        return response;
    }

    public GmEnergyResponse getEnergyStatus(String vehicleId) {
        GmEnergyResponse response = gmApi.getEnergyStatus(vehicleId, RESPONSE_FORMAT);
        evaluateGmClientCodes(response);
        return response;
    }

    public GmEngineResponse commitEngineAction(String vehicleId, String actionEngine) {
        GmEngineResponse response = gmApi.commitEngineAction(vehicleId, RESPONSE_FORMAT, actionEngine);
        evaluateGmClientCodes(response);
        return response;
    }

    private void evaluateGmClientCodes(ResponseBase response) {
        int statusCode = Integer.parseInt(response.getStatus());
        String service = response.getService();
        String reason = response.getReason();
        if(statusCode == Response.SC_OK) {
            return;
        } else if (statusCode == Response.SC_NOT_FOUND) {
            logger.error(service + " : Not Found");
            throw new NotFoundException(reason);
        } else if (statusCode == Response.SC_BAD_REQUEST) {
            logger.error(service + " : Bad Request");
            throw new BadRequestException(service + ": Bad Request");
        } else if (statusCode >= 500) {
            logger.error(service + " : Service Not Available");
            throw new ServiceOutageException(service + ": Service Not Available");
        }
    }

}
