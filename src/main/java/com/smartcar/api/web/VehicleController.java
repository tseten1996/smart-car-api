package com.smartcar.api.web;

import com.smartcar.api.service.VehicleService;
import com.smartcar.api.web.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@CrossOrigin
@Validated
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping(
            value = "/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiOperation(value = "Display Vehicle info by Vehicle Id", response = ResponseEntity.class)
    public ResponseEntity<ScVehicleInfoResponse> getVehicleInfoById(@PathVariable("id") String id) {
        ScVehicleInfoResponse vehicleInfo = vehicleService.getVehicleInformation(id);
        return ResponseEntity.ok(vehicleInfo);
    }

    @GetMapping(
            value = "/{id}/doors",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiOperation(value = "Display Door Security Status of Vehicle by Vehicle Id", response = ResponseEntity.class)
    public ResponseEntity<ScSecurityStatusResponse> getVehicleDoorSecurityStatusById(@PathVariable("id") String id) {
        ScSecurityStatusResponse securityInfo = vehicleService.getSecurityStatus(id);
        return ResponseEntity.ok(securityInfo);
    }

    @GetMapping(
            value = "/{id}/fuel",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiOperation(value = "Display Vehicle Fuel Status by Vehicle Id", response = ResponseEntity.class)
    public ResponseEntity<ScEnergyResponse> getVehicleFuelStatusById(@PathVariable("id") String id) {
        ScEnergyResponse fuelInfo = vehicleService.getEnergyStatus(id, "FUEL");
        return ResponseEntity.ok(fuelInfo);
    }

    @GetMapping(
            value = "/{id}/battery",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiOperation(value = "Display Vehicle Battery Status by Vehicle Id", response = ResponseEntity.class)
    public ResponseEntity<ScEnergyResponse> getVehicleBatteryStatusById(@PathVariable("id") String id) {
        ScEnergyResponse batteryInfo = vehicleService.getEnergyStatus(id, "BATTERY");
        return ResponseEntity.ok(batteryInfo);
    }

    @PostMapping(
            value = "/{id}/engine",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiOperation(value = "Start or Stop Vehicle by Vehicle Id", response = ResponseEntity.class)
    public ResponseEntity<ScEngineResponse> startOrStopVehicleById(@PathVariable("id") String id, @Valid @RequestBody ScEngineRequest request) {
        ScEngineResponse engineActionInfo = vehicleService.commitEngineAction(id, request.getAction());
        return ResponseEntity.ok(engineActionInfo);
    }

}
