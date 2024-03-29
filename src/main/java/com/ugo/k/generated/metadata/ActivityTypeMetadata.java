package com.ugo.k.generated.metadata;

import com.myzlab.k.KJoinDefinition;
import com.myzlab.k.KRow;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import static com.ugo.k.generated.metadata.Tables.*;
import com.ugo.k.generated.mappers.ActivityType;
import java.time.LocalDateTime;

public class ActivityTypeMetadata extends KTable {

	private static ActivityTypeMetadata instance = null;

	public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
	public final KTableColumn NAME = new KTableColumn(this, "name", String.class);
	public final KTableColumn CREATED_AT = new KTableColumn(this, "created_at", LocalDateTime.class);
	public final KTableColumn UPDATED_AT = new KTableColumn(this, "updated_at", LocalDateTime.class);

	private ActivityTypeMetadata() {
		super("ugo", "activity_type", "at");
	}

	private ActivityTypeMetadata(
		final String schema,
		final String name,
		final String alias
	) {
		super(schema, name, alias);
	}

	public static ActivityTypeMetadata getInstance() {
		if (instance != null) {
			return instance;
		}

		instance = new ActivityTypeMetadata();

		return instance;
	}

	public KJoinDefinition joinExperience() {
		return EXPERIENCE.on(
			EXPERIENCE.ACTIVITY_TYPE_ID.eq(this.ID)
		);
	}

	public ActivityTypeMetadata alias(
		final String alias
	) {
		return new ActivityTypeMetadata(this.schema, this.name, alias);
	}

	@Override
	protected Class<? extends KRow> getKRowClass() {
		return ActivityType.class;
	}
}
