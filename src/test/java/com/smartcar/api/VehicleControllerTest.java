package com.smartcar.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcar.api.exception.BadRequestException;
import com.smartcar.api.exception.NotFoundException;
import com.smartcar.api.exception.ServiceOutageException;
import com.smartcar.api.service.VehicleService;
import com.smartcar.api.web.VehicleController;
import com.smartcar.api.web.dto.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VehicleController.class)
@ActiveProfiles("test")
@Import({ApiControllerTestConfiguration.class})
public class VehicleControllerTest {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getVehicleInformation_success() throws Exception {
        ScVehicleInfoResponse vehicleInfoResponse = ScVehicleInfoResponse.builder()
                .color("Green")
                .doorCount(4)
                .driveTrain("v6")
                .vin("1234567")
                .build();

        Mockito.when(vehicleService.getVehicleInformation("4567")).thenReturn(vehicleInfoResponse);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.color", is("Green")))
                .andExpect(jsonPath("$.doorCount", is(4)))
                .andExpect(jsonPath("$.driveTrain", is("v6")))
                .andExpect(jsonPath("$.vin", is("1234567")));
    }

    @Test
    public void getVehicleInformation_vehicleNotFound() throws Exception {

        Mockito.when(vehicleService.getVehicleInformation("4567")).thenThrow(new NotFoundException("Vehicle Id 4567 is not found"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getVehicleInformation_badRequest() throws Exception {

        Mockito.when(vehicleService.getVehicleInformation("4567")).thenThrow(new BadRequestException("getVehicleInfo" + ": Bad Request"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getVehicleInformation_outOfService() throws Exception {

        Mockito.when(vehicleService.getVehicleInformation("4567")).thenThrow(new ServiceOutageException("getVehicleInfo" + " : Service Not Available"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void getFuelEnergy_success() throws Exception {
        ScEnergyResponse scFuelEnergyResponse = ScEnergyResponse.builder()
                .percent(30.5)
                .build();

        Mockito.when(vehicleService.getEnergyStatus("4567", "FUEL")).thenReturn(scFuelEnergyResponse);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/fuel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.percent", is(30.5)));
    }

    @Test
    public void getFuelEnergy_vehicleNotFound() throws Exception {

        Mockito.when(vehicleService.getEnergyStatus("4567", "FUEL")).thenThrow(new NotFoundException("Vehicle Id 4567 is not found"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/fuel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getFuelEnergy_badRequest() throws Exception {

        Mockito.when(vehicleService.getEnergyStatus("4567", "FUEL")).thenThrow(new BadRequestException("getEnergy" + ": Bad Request"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/fuel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getFuelEnergy_outOfService() throws Exception {

        Mockito.when(vehicleService.getEnergyStatus("4567", "FUEL")).thenThrow(new ServiceOutageException("getEnergy" + " : Service Not Available"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/fuel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void getBatteryEnergy_success() throws Exception {
        ScEnergyResponse scBatteryEnergyResponse = ScEnergyResponse.builder()
                .percent(30.5)
                .build();

        Mockito.when(vehicleService.getEnergyStatus("4567", "BATTERY")).thenReturn(scBatteryEnergyResponse);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/battery")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.percent", is(30.5)));
    }

    @Test
    public void getBatteryEnergy_vehicleNotFound() throws Exception {

        Mockito.when(vehicleService.getEnergyStatus("4567", "BATTERY")).thenThrow(new NotFoundException("Vehicle Id 4567 is not found"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/battery")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getBatteryEnergy_badRequest() throws Exception {

        Mockito.when(vehicleService.getEnergyStatus("4567", "BATTERY")).thenThrow(new BadRequestException("getEnergy" + ": Bad Request"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/battery")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getBatteryEnergy_outOfService() throws Exception {

        Mockito.when(vehicleService.getEnergyStatus("4567", "BATTERY")).thenThrow(new ServiceOutageException("getEnergy" + " : Service Not Available"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/battery")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void getVehicleSecurity_success() throws Exception {
        ArrayList<ScSecurityStatusResponse.ScSecurityDoorResponse> doorList = new ArrayList<>();
        doorList.add(ScSecurityStatusResponse.ScSecurityDoorResponse.builder().locked(false).location("topLeft").build());
        doorList.add(ScSecurityStatusResponse.ScSecurityDoorResponse.builder().locked(true).location("topRight").build());
        doorList.add(ScSecurityStatusResponse.ScSecurityDoorResponse.builder().locked(false).location("backLeft").build());
        doorList.add(ScSecurityStatusResponse.ScSecurityDoorResponse.builder().locked(true).location("backRight").build());

        ScSecurityStatusResponse scSecurityStatusResponse = ScSecurityStatusResponse.builder()
                .doorSecurityResponses(doorList)
                .build();

        Mockito.when(vehicleService.getSecurityStatus("4567")).thenReturn(scSecurityStatusResponse);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/doors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorSecurityResponses", Matchers.hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorSecurityResponses[0].location", Matchers.is("topLeft")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorSecurityResponses[1].location", Matchers.is("topRight")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorSecurityResponses[2].location", Matchers.is("backLeft")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorSecurityResponses[3].location", Matchers.is("backRight")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorSecurityResponses[0].locked", Matchers.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorSecurityResponses[1].locked", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorSecurityResponses[2].locked", Matchers.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorSecurityResponses[3].locked", Matchers.is(true)));
    }

    @Test
    public void getVehicleSecurity_vehicleNotFound() throws Exception {

        Mockito.when(vehicleService.getSecurityStatus("4567")).thenThrow(new NotFoundException("Vehicle Id 4567 is not found"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/doors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getVehicleSecurity_badRequest() throws Exception {

        Mockito.when(vehicleService.getSecurityStatus("4567")).thenThrow(new BadRequestException("getVehicleSecurity" + ": Bad Request"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/doors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getVehicleSecurity_outOfService() throws Exception {

        Mockito.when(vehicleService.getSecurityStatus("4567")).thenThrow(new ServiceOutageException("getVehicleSecurity" + " : Service Not Available"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/vehicles/4567/doors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void startVehicleById_success() throws Exception {
        ScEngineResponse scEngineResponse = ScEngineResponse.builder()
                .status("success")
                .build();

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("START")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "START")).thenReturn(scEngineResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is("success")));
    }

    @Test
    public void startVehicleById_error() throws Exception {
        ScEngineResponse scEngineResponse = ScEngineResponse.builder()
                .status("error")
                .build();

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("START")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "START")).thenReturn(scEngineResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is("error")));
    }

    @Test
    public void startVehicleById_vehicleNotFound() throws Exception {

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("START")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "START")).thenThrow(new NotFoundException("Vehicle Id 4567 is not found"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound());
    }

    @Test
    public void startVehicleById_badRequest() throws Exception {

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("START")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "START")).thenThrow(new BadRequestException("actionEngineService" + ": Bad Request"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void startVehicleById_outOfService() throws Exception {

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("START")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "START")).thenThrow(new ServiceOutageException("actionEngineService" + " : Service Not Available"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void stopVehicleById_success() throws Exception {
        ScEngineResponse scEngineResponse = ScEngineResponse.builder()
                .status("success")
                .build();

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("STOP")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "STOP")).thenReturn(scEngineResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is("success")));
    }

    @Test
    public void stopVehicleById_error() throws Exception {
        ScEngineResponse scEngineResponse = ScEngineResponse.builder()
                .status("error")
                .build();

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("STOP")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "STOP")).thenReturn(scEngineResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", is("error")));
    }

    @Test
    public void stopVehicleById_vehicleNotFound() throws Exception {

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("STOP")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "STOP")).thenThrow(new NotFoundException("Vehicle Id 4567 is not found"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound());
    }

    @Test
    public void stopVehicleById_badRequest() throws Exception {

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("STOP")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "STOP")).thenThrow(new BadRequestException("actionEngineService" + ": Bad Request"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void stopVehicleById_outOfService() throws Exception {

        ScEngineRequest scEngineRequest = ScEngineRequest.builder()
                .action("STOP")
                .build();

        Mockito.when(vehicleService.commitEngineAction("4567", "STOP")).thenThrow(new ServiceOutageException("actionEngineService" + " : Service Not Available"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/vehicles/4567/engine")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(scEngineRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isInternalServerError());
    }


}
