package com.ugo.dak;

import com.myzlab.k.KBuilder;
import com.myzlab.k.KFunction;
import static com.myzlab.k.KFunction.assertNotExists;
import static com.myzlab.k.KFunction.crypt;
import com.myzlab.k.KQuery;
import com.myzlab.k.KValues;
import com.ugo.k.generated.mappers.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import static com.ugo.k.generated.metadata.Tables.*;
import com.ugo.payloads.RegisterAppUserPayload;
import org.springframework.http.HttpStatus;

@Component
@RequiredArgsConstructor
public class AppUserDAK {

    private final KBuilder k;

    public void create(
        final RegisterAppUserPayload registerAppUserPayload
    ) {

        // Creamos un KValues con los valores del nuevo usuario
        final KValues userValues = KFunction.values()
            .append(
                registerAppUserPayload.getRoleId(),
                registerAppUserPayload.getName(),
                registerAppUserPayload.getPaternalSurname(),
                registerAppUserPayload.getMaternalSurname(),
                registerAppUserPayload.getEmail(),
                KFunction.crypt(registerAppUserPayload.getPassword())
            );

        //Insertamos el usuario en la tabla user
        k
        .insertInto(APP_USER)
        .columns(
            APP_USER.ROLE_ID,
            APP_USER.NAME,
            APP_USER.PATERNAL_SURNAME,
            APP_USER.MATERNAL_SURNAME,
            APP_USER.EMAIL,
            APP_USER.PASSWORD
        )
        .values(userValues)
        .execute();
    }
    
    public AppUser findByEmailToLogin(
        final String email,
        final String password
    ) {
        return
            k
            .select(
                APP_USER.ID,
                APP_USER.ROLE_ID,
                APP_USER.NAME,
                APP_USER.MATERNAL_SURNAME,
                APP_USER.PATERNAL_SURNAME,
                APP_USER.EMAIL_CONFIRMED,
                APP_USER.PASSWORD.eq(crypt(password, APP_USER.PASSWORD)).as("pswmatch")
            )
            .from(APP_USER)
            .where(APP_USER.EMAIL.eq(email))
            .single(AppUser.class);
    }
    
    public void assertNotExistsByEmail(
        final String email,
        final HttpStatus httpStatus,
        final String message
    ) {
        final KQuery kQuery =
            k
            .select1()
            .from(APP_USER)
            .where(APP_USER.EMAIL.eq(email));
        
        assertNotExists(k, kQuery, httpStatus, message);
    }
}
