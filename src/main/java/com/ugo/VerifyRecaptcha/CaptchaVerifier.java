package com.ugo.VerifyRecaptcha;

import com.ugo.services.AuthService;
import org.springframework.web.client.RestTemplate;

public class CaptchaVerifier {

    //Clave secreta de reCAPTCHA proporcionada por google
   private static final String secretKey = "6Lc8i6wpAAAAAFYjhqo8gFVhFqAsbx-bLraE8AG0";

    //Metodo para verificar el reCAPTCHA utilizando la API de Google reCAPTCHA
    public static boolean verificarCaptcha(String captcha){
        // Construir la URL de la API de verificación de reCAPTCHA
        String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + secretKey + "&response=" + captcha;

        //Realizar una solicitud POST a la API de verificacion de reCAPTCHA
        RestTemplate restTemplate = new RestTemplate();
        CaptchaResponse response = restTemplate.postForObject(url, null, CaptchaResponse.class);

        //Verificar si la respuesta de la API indica que el reCAPTCHA es valido
        return response.isSucces();
    }
    //Clase para representar la respuesta de la API de verificacion de reCAPTCHA
    private static class CaptchaResponse{
        private boolean success;

        public boolean isSucces(){
            return success;
        }

        public void SetSuccess(boolean success){
            this.success = success;
        }
    }

}
