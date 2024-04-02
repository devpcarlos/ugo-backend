package com.ugo.dak;

import com.myzlab.k.KAlgorithm;
import com.myzlab.k.KBuilder;
import com.myzlab.k.KFunction;
import com.myzlab.k.KValues;
import com.myzlab.k.sql.algorithms.KMd5;
import com.ugo.k.generated.mappers.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.myzlab.k.KFunction.genSalt;
import static com.ugo.k.generated.metadata.Tables.APP_USER;

@Component
@RequiredArgsConstructor
public class UserRepository {

    private  final KBuilder k;

    public void registerUser (AppUser appUser){

        // Creamos un KValues con los valores del nuevo usuario
        final KValues userValues = KFunction.values()
                .append(appUser.getRoleId(),
                        appUser.getName(),
                        appUser.getPaternalSurname(),
                        appUser.getMaternalSurname(),
                        appUser.getEmail(),
                        KFunction.crypt(appUser.getPassword()),
                        appUser.getEmailConfirmed(),
                        LocalDateTime.now());

        //Insertamos el usuario en la tabla user
        k.insertInto(APP_USER)
                .columns(
                        APP_USER.ROLE_ID,
                        APP_USER.NAME,
                        APP_USER.PATERNAL_SURNAME,
                        APP_USER.MATERNAL_SURNAME,
                        APP_USER.EMAIL,
                        APP_USER.PASSWORD,
                        APP_USER.EMAIL_CONFIRMED,
                        APP_USER.CREATED_AT
                )
                .values(userValues )
                .execute();
    }
}
