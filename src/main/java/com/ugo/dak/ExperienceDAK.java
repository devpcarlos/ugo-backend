package com.ugo.dak;

import com.myzlab.k.*;
import com.myzlab.k.optional.KOptionalCollection;
import com.ugo.k.generated.mappers.AppUser;
import com.ugo.k.generated.mappers.Experience;
import com.ugo.payloads.RegisterExperiencePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import static com.myzlab.k.KFunction.*;
import java.util.List;

import static com.ugo.k.generated.metadata.Tables.*;

@Component
@RequiredArgsConstructor
public class ExperienceDAK {

    private final KBuilder K;
    public void createExperience(
            final RegisterExperiencePayload registerExperiencePayload,
            final Long userId
            ){

        final KValues experienceValues = values()
                .append(
                        registerExperiencePayload.getLocation(),
                        registerExperiencePayload.getAvailability(),
                        registerExperiencePayload.getPrice(),
                        userId,
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

    public KCollection fullList(
            final Long userId){
      final KCollection List = K
                .select(EXPERIENCE.LOCATION,
                        EXPERIENCE.AVAILABILITY,
                        EXPERIENCE.PRICE)
                .from(EXPERIENCE)
                .where(EXPERIENCE.APP_USER_ID.eq(optional(userId)))
                .multiple();
      return List;
    }
}

