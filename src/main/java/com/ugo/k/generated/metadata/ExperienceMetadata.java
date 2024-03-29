package com.ugo.k.generated.metadata;

import com.myzlab.k.KEdge;
import com.myzlab.k.KJoinDefinition;
import com.myzlab.k.KRow;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import static com.ugo.k.generated.metadata.Tables.*;
import com.ugo.k.generated.mappers.ActivityType;
import com.ugo.k.generated.mappers.AppUser;
import com.ugo.k.generated.mappers.Experience;
import java.time.LocalDateTime;

public class ExperienceMetadata extends KTable {

	private static ExperienceMetadata instance = null;

	public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
	public final KTableColumn LOCATION = new KTableColumn(this, "location", String.class);
	public final KTableColumn AVAILABILITY = new KTableColumn(this, "availability", String.class);
	public final KTableColumn PRICE = new KTableColumn(this, "price", Double.class);
	public final KTableColumn ACTIVITY_TYPE_ID = new KTableColumn(this, "activity_type_id", Long.class);
	public final KTableColumn CREATED_AT = new KTableColumn(this, "created_at", LocalDateTime.class);
	public final KTableColumn UPDATED_AT = new KTableColumn(this, "updated_at", LocalDateTime.class);
	public final KTableColumn APP_USER_ID = new KTableColumn(this, "app_user_id", Long.class);

	private ExperienceMetadata() {
		super("ugo", "experience", "ex");
	}

	private ExperienceMetadata(
		final String schema,
		final String name,
		final String alias
	) {
		super(schema, name, alias);
	}

	public static ExperienceMetadata getInstance() {
		if (instance != null) {
			return instance;
		}

		instance = new ExperienceMetadata();

		return instance;
	}

	public KJoinDefinition joinActivityType() {
		return ACTIVITY_TYPE.on(
			this.ACTIVITY_TYPE_ID.eq(ACTIVITY_TYPE.ID),
			KEdge.getInstance(Experience.class, this.alias, ActivityType.class, ACTIVITY_TYPE.getAlias(), "activityType")
		);
	}

	public KJoinDefinition joinAppUser() {
		return APP_USER.on(
			this.APP_USER_ID.eq(APP_USER.ID),
			KEdge.getInstance(Experience.class, this.alias, AppUser.class, APP_USER.getAlias(), "appUser")
		);
	}

	public KJoinDefinition joinBooking() {
		return BOOKING.on(
			BOOKING.EXPERIENCE_ID.eq(this.ID)
		);
	}

	public KJoinDefinition joinAssessment() {
		return ASSESSMENT.on(
			ASSESSMENT.EXPERIENCE_ID.eq(this.ID)
		);
	}

	public ExperienceMetadata alias(
		final String alias
	) {
		return new ExperienceMetadata(this.schema, this.name, alias);
	}

	@Override
	protected Class<? extends KRow> getKRowClass() {
		return Experience.class;
	}
}
