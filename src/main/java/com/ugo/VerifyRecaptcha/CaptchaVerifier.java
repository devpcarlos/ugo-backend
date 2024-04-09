package com.ugo.VerifyRecaptcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class CaptchaVerifier {

    //Clave secreta de reCAPTCHA proporcionada por google
    private final String secretKey;

    public  CaptchaVerifier(@Value("${google.recaptcha.secretKey}") String secretKey){
        this.secretKey=secretKey;
    }

    //Metodo para verificar el reCAPTCHA utilizando la API de Google reCAPTCHA
    public final boolean verificarCaptcha(String captcha){
        // Construir la URL de la API de verificación de reCAPTCHA
        String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + secretKey + "&response=" + captcha;

        //Realizar una solicitud POST a la API de verificacion de reCAPTCHA
        RestTemplate restTemplate = new RestTemplate();
        CaptchaResponse response = restTemplate.postForObject(url, null, CaptchaResponse.class);

        //Verificar si la respuesta de la API indica que el reCAPTCHA es valido
        return response.isSuccess();
    }
    //Clase para representar la respuesta de la API de verificacion de reCAPTCHA
    private static class CaptchaResponse{
        private boolean success;

        public boolean isSuccess(){
            return success;
        }

        public void setSuccess(boolean success){
            this.success = success;
        }
    }

}
