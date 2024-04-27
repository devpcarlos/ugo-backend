package com.ugo.services;

import com.myzlab.k.DynamicObject;
import com.myzlab.k.helper.KExceptionHelper;
import com.ugo.VerifyRecaptcha.CaptchaVerifier;
import com.ugo.dak.AppUserDAK;
import com.ugo.helpers.ValidatorHelper;
import com.ugo.k.generated.mappers.AppUser;
import com.ugo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AppUserDAK appUserDAK;
    private final JwtTokenProvider jwtTokenProvider;
    private final CaptchaVerifier captchaVerifier;
    
    public ResponseEntity login(
        final String email,
        final String password,
        final String captcha
    ) {
        // Validar que los campos no estén vacíos
        ValidatorHelper.assertNotNullNotEmpty(email, "El email es obligatorio");
        ValidatorHelper.assertNotNullNotEmpty(password, "El password es obligatorio");
        ValidatorHelper.assertNotNullNotEmpty(captcha, "El captcha es obligatorio");

        //Verificar el reCAPTCHA utilizando la API de Google reCAPTCHA
       final boolean captchaValido = captchaVerifier.verificarCaptcha(captcha);

        if (!captchaValido){
            throw KExceptionHelper.badRequest("Captcha invalido");
        }
        // Buscar al usuario por su correo electrónico y contraseña
        final AppUser appUser = appUserDAK.findByEmailToLogin(email.toLowerCase(), password);

        // Verificar si el usuario no existe o la contraseña no coincide
        if (appUser.isNull() || !appUser.getBoolean("pswmatch")) {
            throw KExceptionHelper.badRequest("Credenciales inválidas");
        }

        final String token = jwtTokenProvider.generateToken(email, appUser.getId());

        return DynamicObject.create()
            .add("emailConfirmed", appUser.getEmailConfirmed())
            .add("name", appUser.getName())
            .add("paternalSurname", appUser.getPaternalSurname())
            .add("maternalSurname", appUser.getMaternalSurname())
            .add("token", token)
        .buildResponse();
    }
}
