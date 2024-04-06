package com.ugo.payloads;

import com.myzlab.k.validator.PayloadValidator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterAppUserPayload extends PayloadValidator {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 63, message = "El nombre puede contener máximo 63 caracteres")
    private String name;
    
    @Size(max = 63, message = "El apellido paterno puede contener máximo 63 caracteres")
    private String paternalSurname;
    
    @Size(max = 63, message = "El apellido materno puede contener máximo 63 caracteres")
    private String maternalSurname;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email no válido")
    @Size(max = 255, message = "El email puede contener máximo 255 caracteres")
    private String email;
    
    @NotBlank(message = "El password es obligatorio")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-\\[{}\\]:;',?/*~$^+=<>.]).{8,14}$", message = "Formato de password no válido")
    private String password;
    
    @NotNull( message = "El rolId es obligatorio")
    private Long roleId;
}
