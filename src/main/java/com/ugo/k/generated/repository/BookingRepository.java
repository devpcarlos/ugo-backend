package com.ugo.k.generated.repository;

import com.myzlab.k.KCrudRepository;
import com.myzlab.k.KBuilder;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import com.ugo.k.generated.mappers.Booking;
import com.ugo.k.generated.metadata.BookingMetadata;
import static com.ugo.k.generated.metadata.Tables.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookingRepository extends KCrudRepository<Booking, Long> {

	private final KBuilder k;

	@Override
	public KTable getMetadata() {
		return BOOKING;
	}

	@Override
	public Class getKRowClass() {
		return Booking.class;
	}

	@Override
	public Class<?> getKMetadataClass() {
		return BookingMetadata.class;
	}

	@Override
	public KTableColumn getKTableColumnId() {
		return BOOKING.ID;
	}

	@Override
	public KBuilder getK() {
		return k;
	}
}