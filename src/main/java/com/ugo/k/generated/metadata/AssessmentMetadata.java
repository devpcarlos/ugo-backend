package com.ugo.k.generated.metadata;

import com.myzlab.k.KEdge;
import com.myzlab.k.KJoinDefinition;
import com.myzlab.k.KRow;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import static com.ugo.k.generated.metadata.Tables.*;
import com.ugo.k.generated.mappers.AppUser;
import com.ugo.k.generated.mappers.Assessment;
import com.ugo.k.generated.mappers.Experience;
import java.time.LocalDateTime;

public class AssessmentMetadata extends KTable {

	private static AssessmentMetadata instance = null;

	public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
	public final KTableColumn APP_USER_ID = new KTableColumn(this, "app_user_id", Long.class);
	public final KTableColumn COMMENT = new KTableColumn(this, "comment", String.class);
	public final KTableColumn RATING = new KTableColumn(this, "rating", Integer.class);
	public final KTableColumn EXPERIENCE_ID = new KTableColumn(this, "experience_id", Long.class);
	public final KTableColumn CREATED_AT = new KTableColumn(this, "created_at", LocalDateTime.class);
	public final KTableColumn UPDATED_AT = new KTableColumn(this, "updated_at", LocalDateTime.class);

	private AssessmentMetadata() {
		super("ugo", "assessment", "as");
	}

	private AssessmentMetadata(
		final String schema,
		final String name,
		final String alias
	) {
		super(schema, name, alias);
	}

	public static AssessmentMetadata getInstance() {
		if (instance != null) {
			return instance;
		}

		instance = new AssessmentMetadata();

		return instance;
	}

	public KJoinDefinition joinAppUser() {
		return APP_USER.on(
			this.APP_USER_ID.eq(APP_USER.ID),
			KEdge.getInstance(Assessment.class, this.alias, AppUser.class, APP_USER.getAlias(), "appUser")
		);
	}

	public KJoinDefinition joinExperience() {
		return EXPERIENCE.on(
			this.EXPERIENCE_ID.eq(EXPERIENCE.ID),
			KEdge.getInstance(Assessment.class, this.alias, Experience.class, EXPERIENCE.getAlias(), "experience")
		);
	}

	public AssessmentMetadata alias(
		final String alias
	) {
		return new AssessmentMetadata(this.schema, this.name, alias);
	}

	@Override
	protected Class<? extends KRow> getKRowClass() {
		return Assessment.class;
	}
}
