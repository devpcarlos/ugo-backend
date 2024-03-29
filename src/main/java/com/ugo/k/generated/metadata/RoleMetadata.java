package com.ugo.k.generated.metadata;

import com.myzlab.k.KJoinDefinition;
import com.myzlab.k.KRow;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import static com.ugo.k.generated.metadata.Tables.*;
import com.ugo.k.generated.mappers.Role;
import java.time.LocalDateTime;

public class RoleMetadata extends KTable {

	private static RoleMetadata instance = null;

	public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
	public final KTableColumn NAME = new KTableColumn(this, "name", String.class);
	public final KTableColumn CREATED_AT = new KTableColumn(this, "created_at", LocalDateTime.class);
	public final KTableColumn UPDATED_AT = new KTableColumn(this, "updated_at", LocalDateTime.class);

	private RoleMetadata() {
		super("ugo", "role", "ro");
	}

	private RoleMetadata(
		final String schema,
		final String name,
		final String alias
	) {
		super(schema, name, alias);
	}

	public static RoleMetadata getInstance() {
		if (instance != null) {
			return instance;
		}

		instance = new RoleMetadata();

		return instance;
	}

	public KJoinDefinition joinAppUser() {
		return APP_USER.on(
			APP_USER.ROLE_ID.eq(this.ID)
		);
	}

	public RoleMetadata alias(
		final String alias
	) {
		return new RoleMetadata(this.schema, this.name, alias);
	}

	@Override
	protected Class<? extends KRow> getKRowClass() {
		return Role.class;
	}
}
