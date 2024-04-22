package com.ugo.dak;

import com.myzlab.k.KBuilder;
import com.myzlab.k.KFunction;
import com.myzlab.k.KValues;
import com.ugo.payloads.RegisterExperiencePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ugo.k.generated.metadata.Tables.EXPERIENCE;

@Component
@RequiredArgsConstructor
public class ExperienceDAK {

    private final KBuilder K;
    public void createExperience(
            final RegisterExperiencePayload registerExperiencePayload
            ){

        final KValues experienceValues = KFunction.values()
                .append(
                        registerExperiencePayload.getLocation(),
                        registerExperiencePayload.getAvailability(),
                        registerExperiencePayload.getPrice(),
                        registerExperiencePayload.getAppUserId(),
                        registerExperiencePayload.getActivityTypeId()
                );

        K
                .insertInto(EXPERIENCE)
                .columns(
                        EXPERIENCE.LOCATION,
                        EXPERIENCE.AVAILABILITY,
                        EXPERIENCE.PRICE,
                        EXPERIENCE.APP_USER_ID,
                        EXPERIENCE.ACTIVITY_TYPE_ID
                ).values(experienceValues)
                .execute();
    }

    public void ListAll (  ){
        K
                .select(EXPERIENCE.LOCATION,
                        EXPERIENCE.AVAILABILITY,
                        EXPERIENCE.PRICE,
                        EXPERIENCE.ACTIVITY_TYPE_ID
                        ).from(EXPERIENCE)
                .multiple();
    }

}
