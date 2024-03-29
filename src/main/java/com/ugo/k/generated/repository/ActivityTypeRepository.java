package com.ugo.k.generated.repository;

import com.myzlab.k.KCrudRepository;
import com.myzlab.k.KBuilder;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import com.ugo.k.generated.mappers.ActivityType;
import com.ugo.k.generated.metadata.ActivityTypeMetadata;
import static com.ugo.k.generated.metadata.Tables.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ActivityTypeRepository extends KCrudRepository<ActivityType, Long> {

	private final KBuilder k;

	@Override
	public KTable getMetadata() {
		return ACTIVITY_TYPE;
	}

	@Override
	public Class getKRowClass() {
		return ActivityType.class;
	}

	@Override
	public Class<?> getKMetadataClass() {
		return ActivityTypeMetadata.class;
	}

	@Override
	public KTableColumn getKTableColumnId() {
		return ACTIVITY_TYPE.ID;
	}

	@Override
	public KBuilder getK() {
		return k;
	}
}