package com.smartcar.api.client.response.GmEngine;

import com.smartcar.api.client.response.ResponseBase;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GmEngineResponse extends ResponseBase {
    private GmActionResult actionResult;

}
