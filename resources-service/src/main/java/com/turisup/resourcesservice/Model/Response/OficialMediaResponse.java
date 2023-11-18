package com.turisup.resourcesservice.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OficialMediaResponse {
    Long id;
    String url;
    Long userId;
    String userName;
}
