package com.ugo.dak;

import com.myzlab.k.KBuilder;
import com.myzlab.k.KFunction;
import static com.myzlab.k.KFunction.crypt;
import com.myzlab.k.KValues;
import com.ugo.k.generated.mappers.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import static com.ugo.k.generated.metadata.Tables.*;

@Component
@RequiredArgsConstructor
public class AppUserDAK {

    private final KBuilder k;

    public void create(
        final AppUser appUser
    ) {

        // Creamos un KValues con los valores del nuevo usuario
        final KValues userValues = KFunction.values()
            .append(
                appUser.getRoleId(),
                appUser.getName(),
                appUser.getPaternalSurname(),
                appUser.getMaternalSurname(),
                appUser.getEmail(),
                KFunction.crypt(appUser.getPassword())
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
}
