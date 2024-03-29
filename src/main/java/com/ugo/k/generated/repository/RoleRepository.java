package com.ugo.k.generated.repository;

import com.myzlab.k.KCrudRepository;
import com.myzlab.k.KBuilder;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import com.ugo.k.generated.mappers.Role;
import com.ugo.k.generated.metadata.RoleMetadata;
import static com.ugo.k.generated.metadata.Tables.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepository extends KCrudRepository<Role, Long> {

	private final KBuilder k;

	@Override
	public KTable getMetadata() {
		return ROLE;
	}

	@Override
	public Class getKRowClass() {
		return Role.class;
	}

	@Override
	public Class<?> getKMetadataClass() {
		return RoleMetadata.class;
	}

	@Override
	public KTableColumn getKTableColumnId() {
		return ROLE.ID;
	}

	@Override
	public KBuilder getK() {
		return k;
	}
}