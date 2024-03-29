package com.ugo.k.generated.repository;

import com.myzlab.k.KCrudRepository;
import com.myzlab.k.KBuilder;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import com.ugo.k.generated.mappers.Param;
import com.ugo.k.generated.metadata.ParamMetadata;
import static com.ugo.k.generated.metadata.Tables.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ParamRepository extends KCrudRepository<Param, Long> {

	private final KBuilder k;

	@Override
	public KTable getMetadata() {
		return PARAM;
	}

	@Override
	public Class getKRowClass() {
		return Param.class;
	}

	@Override
	public Class<?> getKMetadataClass() {
		return ParamMetadata.class;
	}

	@Override
	public KTableColumn getKTableColumnId() {
		return PARAM.ID;
	}

	@Override
	public KBuilder getK() {
		return k;
	}
}