package com.simscale.catalog.rest.utils;

import com.simscale.catalog.rest.config.EndpointConfig;

import java.net.URI;
import java.net.URISyntaxException;

public class URIPathBinder {
    
    public static URI resourceLocationBuilder(String endpoint, Long id) throws URISyntaxException {
        return new URI(EndpointConfig.USERS_SINGLE_RESOURCE.replace("{id}", String.valueOf(id)));
    } 
}
