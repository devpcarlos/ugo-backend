package com.ugo.k.generated.repository;

import com.myzlab.k.KCrudRepository;
import com.myzlab.k.KBuilder;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import com.ugo.k.generated.mappers.Experience;
import com.ugo.k.generated.metadata.ExperienceMetadata;
import static com.ugo.k.generated.metadata.Tables.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExperienceRepository extends KCrudRepository<Experience, Long> {

	private final KBuilder k;

	@Override
	public KTable getMetadata() {
		return EXPERIENCE;
	}

	@Override
	public Class getKRowClass() {
		return Experience.class;
	}

	@Override
	public Class<?> getKMetadataClass() {
		return ExperienceMetadata.class;
	}

	@Override
	public KTableColumn getKTableColumnId() {
		return EXPERIENCE.ID;
	}

	@Override
	public KBuilder getK() {
		return k;
	}
}