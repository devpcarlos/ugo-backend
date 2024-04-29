package com.ugo.payloads;

import com.myzlab.k.validator.PayloadValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterExperiencePayload extends PayloadValidator {


    @NotBlank(message = "El campo location es obligatorio")
    private String location;

    @NotBlank(message = "El campo availability es obligatorio")
    private String availability;

    @NotNull(message = "El campo price es obligatorio")
    private Double price;

    @NotNull( message = "El activityType es obligatorio")
    private Long activityTypeId;

    @NotNull( message = "El appUser es obligatorio")
    private Long appUserId;

}
