package com.ugo.k.generated.metadata;

import com.myzlab.k.KJoinDefinition;
import com.myzlab.k.KRow;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import static com.ugo.k.generated.metadata.Tables.*;
import com.ugo.k.generated.mappers.BookingStatus;
import java.time.LocalDateTime;

public class BookingStatusMetadata extends KTable {

	private static BookingStatusMetadata instance = null;

	public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
	public final KTableColumn NAME = new KTableColumn(this, "name", String.class);
	public final KTableColumn CREATED_AT = new KTableColumn(this, "created_at", LocalDateTime.class);
	public final KTableColumn UPDATED_AT = new KTableColumn(this, "updated_at", LocalDateTime.class);

	private BookingStatusMetadata() {
		super("ugo", "booking_status", "bs");
	}

	private BookingStatusMetadata(
		final String schema,
		final String name,
		final String alias
	) {
		super(schema, name, alias);
	}

	public static BookingStatusMetadata getInstance() {
		if (instance != null) {
			return instance;
		}

		instance = new BookingStatusMetadata();

		return instance;
	}

	public KJoinDefinition joinBooking() {
		return BOOKING.on(
			BOOKING.BOOKING_STATUS_ID.eq(this.ID)
		);
	}

	public BookingStatusMetadata alias(
		final String alias
	) {
		return new BookingStatusMetadata(this.schema, this.name, alias);
	}

	@Override
	protected Class<? extends KRow> getKRowClass() {
		return BookingStatus.class;
	}
}
