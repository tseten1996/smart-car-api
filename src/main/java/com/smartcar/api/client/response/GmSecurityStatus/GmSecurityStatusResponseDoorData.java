package com.smartcar.api.client.response.GmSecurityStatus;

import com.smartcar.api.client.response.ResponseBaseData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GmSecurityStatusResponseDoorData {
    private ResponseBaseData location;
    private ResponseBaseData locked;
}
