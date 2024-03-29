package com.ugo.k.generated.metadata;

import com.myzlab.k.KEdge;
import com.myzlab.k.KJoinDefinition;
import com.myzlab.k.KRow;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import static com.ugo.k.generated.metadata.Tables.*;
import com.ugo.k.generated.mappers.AppUser;
import com.ugo.k.generated.mappers.Role;
import java.time.LocalDateTime;

public class AppUserMetadata extends KTable {

	private static AppUserMetadata instance = null;

	public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
	public final KTableColumn ROLE_ID = new KTableColumn(this, "role_id", Long.class);
	public final KTableColumn NAME = new KTableColumn(this, "name", String.class);
	public final KTableColumn PATERNAL_SURNAME = new KTableColumn(this, "paternal_surname", String.class);
	public final KTableColumn MATERNAL_SURNAME = new KTableColumn(this, "maternal_surname", String.class);
	public final KTableColumn EMAIL = new KTableColumn(this, "email", String.class);
	public final KTableColumn PASSWORD = new KTableColumn(this, "password", String.class);
	public final KTableColumn EMAIL_CONFIRMED = new KTableColumn(this, "email_confirmed", Boolean.class);
	public final KTableColumn CREATED_AT = new KTableColumn(this, "created_at", LocalDateTime.class);
	public final KTableColumn UPDATED_AT = new KTableColumn(this, "updated_at", LocalDateTime.class);

	private AppUserMetadata() {
		super("ugo", "app_user", "au");
	}

	private AppUserMetadata(
		final String schema,
		final String name,
		final String alias
	) {
		super(schema, name, alias);
	}

	public static AppUserMetadata getInstance() {
		if (instance != null) {
			return instance;
		}

		instance = new AppUserMetadata();

		return instance;
	}

	public KJoinDefinition joinRole() {
		return ROLE.on(
			this.ROLE_ID.eq(ROLE.ID),
			KEdge.getInstance(AppUser.class, this.alias, Role.class, ROLE.getAlias(), "role")
		);
	}

	public KJoinDefinition joinExperience() {
		return EXPERIENCE.on(
			EXPERIENCE.APP_USER_ID.eq(this.ID)
		);
	}

	public KJoinDefinition joinBooking() {
		return BOOKING.on(
			BOOKING.APP_USER_ID.eq(this.ID)
		);
	}

	public KJoinDefinition joinAssessment() {
		return ASSESSMENT.on(
			ASSESSMENT.APP_USER_ID.eq(this.ID)
		);
	}

	public AppUserMetadata alias(
		final String alias
	) {
		return new AppUserMetadata(this.schema, this.name, alias);
	}

	@Override
	protected Class<? extends KRow> getKRowClass() {
		return AppUser.class;
	}
}
