package com.ugo.k.generated.repository;

import com.myzlab.k.KCrudRepository;
import com.myzlab.k.KBuilder;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import com.ugo.k.generated.mappers.BookingStatus;
import com.ugo.k.generated.metadata.BookingStatusMetadata;
import static com.ugo.k.generated.metadata.Tables.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookingStatusRepository extends KCrudRepository<BookingStatus, Long> {

	private final KBuilder k;

	@Override
	public KTable getMetadata() {
		return BOOKING_STATUS;
	}

	@Override
	public Class getKRowClass() {
		return BookingStatus.class;
	}

	@Override
	public Class<?> getKMetadataClass() {
		return BookingStatusMetadata.class;
	}

	@Override
	public KTableColumn getKTableColumnId() {
		return BOOKING_STATUS.ID;
	}

	@Override
	public KBuilder getK() {
		return k;
	}
}