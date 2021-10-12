package com.smartcar.api;

import com.smartcar.api.service.VehicleService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
class ApiControllerTestConfiguration {

	@MockBean
	private VehicleService vehicleService;

}
