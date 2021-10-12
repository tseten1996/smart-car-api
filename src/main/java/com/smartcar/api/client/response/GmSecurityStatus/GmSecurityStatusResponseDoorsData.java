package com.smartcar.api.client.response.GmSecurityStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GmSecurityStatusResponseDoorsData {
    private String type;
    private ArrayList<GmSecurityStatusResponseDoorData> values;

}
