package com.ugo.services;

import com.myzlab.k.DynamicObject;
import com.myzlab.k.helper.KExceptionHelper;
import com.ugo.dak.AppUserDAK;
import com.ugo.helpers.ValidatorHelper;
import com.ugo.k.generated.mappers.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AppUserDAK appUserDAK;
    
    public ResponseEntity login(
        final String email,
        final String password,
        final String captcha
    ) {
        // Validar que los campos no estén vacíos
        ValidatorHelper.assertNotNullNotEmpty(email, "El email es obligatorio");
        ValidatorHelper.assertNotNullNotEmpty(password, "El password es obligatorio");
        ValidatorHelper.assertNotNullNotEmpty(captcha, "El captcha es obligatorio");

        // Buscar al usuario por su correo electrónico y contraseña
        final AppUser appUser = appUserDAK.findByEmailToLogin(email.toLowerCase(), password);

        // Verificar si el usuario no existe o la contraseña no coincide
        if (appUser.isNull() || !appUser.getBoolean("pswmatch")) {
            throw KExceptionHelper.badRequest("Credenciales inválidas");
        }
        // Verificar si el correo electrónico del usuario no está confirmado
        if (appUser.getEmailConfirmed().equals(Boolean.FALSE)) {
            return DynamicObject.create()
                .add("emailConfirmed", false)
                .buildResponse();
        }
        
        return DynamicObject.create()
            .add("emailConfirmed", true)
            .add("name", appUser.getName())
            .add("paternalSurname", appUser.getPaternalSurname())
            .add("maternalSurname", appUser.getMaternalSurname())
        .buildResponse();
    }
}