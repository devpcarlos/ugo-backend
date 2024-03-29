package com.ugo.k.generated.mappers;

import com.myzlab.k.KRow;
import com.myzlab.k.annotations.Id;
import com.myzlab.k.annotations.KMetadata;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Booking extends KRow {

	@Id
	@KMetadata(name = "ID")
	private Long id;

	@KMetadata(name = "BOOKING_STATUS_ID")
	private Long bookingStatusId;

	@KMetadata(name = "EXPERIENCE_ID")
	private Long experienceId;

	@KMetadata(name = "APP_USER_ID")
	private Long appUserId;

	@KMetadata(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@KMetadata(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	private BookingStatus bookingStatus;
	private Experience experience;
	private AppUser appUser;

	public Booking setId(Long id) {
		this.id = id;
		this.dirtyProperties.add("id");

		return this;
	}

	public Booking setBookingStatusId(Long bookingStatusId) {
		this.bookingStatusId = bookingStatusId;
		this.dirtyProperties.add("bookingStatusId");

		return this;
	}

	public Booking setExperienceId(Long experienceId) {
		this.experienceId = experienceId;
		this.dirtyProperties.add("experienceId");

		return this;
	}

	public Booking setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
		this.dirtyProperties.add("appUserId");

		return this;
	}

	public Booking setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		this.dirtyProperties.add("createdAt");

		return this;
	}

	public Booking setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
		this.dirtyProperties.add("updatedAt");

		return this;
	}

	public Booking setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;

		return this;
	}

	public Booking setExperience(Experience experience) {
		this.experience = experience;

		return this;
	}

	public Booking setAppUser(AppUser appUser) {
		this.appUser = appUser;

		return this;
	}

	@Override
	public Object getPrimaryKeyValue() {
		return this.id;
	}

}
