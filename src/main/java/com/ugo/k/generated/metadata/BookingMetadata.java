package com.ugo.k.generated.metadata;

import com.myzlab.k.KEdge;
import com.myzlab.k.KJoinDefinition;
import com.myzlab.k.KRow;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;
import static com.ugo.k.generated.metadata.Tables.*;
import com.ugo.k.generated.mappers.AppUser;
import com.ugo.k.generated.mappers.Booking;
import com.ugo.k.generated.mappers.BookingStatus;
import com.ugo.k.generated.mappers.Experience;
import java.time.LocalDateTime;

public class BookingMetadata extends KTable {

	private static BookingMetadata instance = null;

	public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
	public final KTableColumn BOOKING_STATUS_ID = new KTableColumn(this, "booking_status_id", Long.class);
	public final KTableColumn EXPERIENCE_ID = new KTableColumn(this, "experience_id", Long.class);
	public final KTableColumn APP_USER_ID = new KTableColumn(this, "app_user_id", Long.class);
	public final KTableColumn CREATED_AT = new KTableColumn(this, "created_at", LocalDateTime.class);
	public final KTableColumn UPDATED_AT = new KTableColumn(this, "updated_at", LocalDateTime.class);

	private BookingMetadata() {
		super("ugo", "booking", "bo");
	}

	private BookingMetadata(
		final String schema,
		final String name,
		final String alias
	) {
		super(schema, name, alias);
	}

	public static BookingMetadata getInstance() {
		if (instance != null) {
			return instance;
		}

		instance = new BookingMetadata();

		return instance;
	}

	public KJoinDefinition joinBookingStatus() {
		return BOOKING_STATUS.on(
			this.BOOKING_STATUS_ID.eq(BOOKING_STATUS.ID),
			KEdge.getInstance(Booking.class, this.alias, BookingStatus.class, BOOKING_STATUS.getAlias(), "bookingStatus")
		);
	}

	public KJoinDefinition joinExperience() {
		return EXPERIENCE.on(
			this.EXPERIENCE_ID.eq(EXPERIENCE.ID),
			KEdge.getInstance(Booking.class, this.alias, Experience.class, EXPERIENCE.getAlias(), "experience")
		);
	}

	public KJoinDefinition joinAppUser() {
		return APP_USER.on(
			this.APP_USER_ID.eq(APP_USER.ID),
			KEdge.getInstance(Booking.class, this.alias, AppUser.class, APP_USER.getAlias(), "appUser")
		);
	}

	public BookingMetadata alias(
		final String alias
	) {
		return new BookingMetadata(this.schema, this.name, alias);
	}

	@Override
	protected Class<? extends KRow> getKRowClass() {
		return Booking.class;
	}
}
