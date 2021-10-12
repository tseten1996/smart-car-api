package com.smartcar.api.client.response.GmSecurityStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GmSecurityStatusResponseData {
    private GmSecurityStatusResponseDoorsData doors;

}
