package com.gauro.restservices.springbootbuildingblocks.restservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * @author Chandra
 */
@AllArgsConstructor
@Getter
@Builder
public class CustomErrorDetails {
    private Date timestamp;
    private String message;
    private String errorDetails;

}
