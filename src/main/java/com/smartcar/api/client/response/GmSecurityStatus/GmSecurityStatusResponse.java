package com.smartcar.api.client.response.GmSecurityStatus;

import com.smartcar.api.client.response.ResponseBase;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GmSecurityStatusResponse extends ResponseBase {
    private GmSecurityStatusResponseData data;

}
