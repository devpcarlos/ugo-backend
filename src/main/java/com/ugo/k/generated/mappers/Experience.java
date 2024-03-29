package com.ugo.k.generated.mappers;

import com.myzlab.k.KRow;
import com.myzlab.k.annotations.Id;
import com.myzlab.k.annotations.KMetadata;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Experience extends KRow {

	@Id
	@KMetadata(name = "ID")
	private Long id;

	@KMetadata(name = "LOCATION")
	private String location;

	@KMetadata(name = "AVAILABILITY")
	private String availability;

	@KMetadata(name = "PRICE")
	private Double price;

	@KMetadata(name = "ACTIVITY_TYPE_ID")
	private Long activityTypeId;

	@KMetadata(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@KMetadata(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	@KMetadata(name = "APP_USER_ID")
	private Long appUserId;

	private ActivityType activityType;
	private AppUser appUser;

	public Experience setId(Long id) {
		this.id = id;
		this.dirtyProperties.add("id");

		return this;
	}

	public Experience setLocation(String location) {
		this.location = location;
		this.dirtyProperties.add("location");

		return this;
	}

	public Experience setAvailability(String availability) {
		this.availability = availability;
		this.dirtyProperties.add("availability");

		return this;
	}

	public Experience setPrice(Double price) {
		this.price = price;
		this.dirtyProperties.add("price");

		return this;
	}

	public Experience setActivityTypeId(Long activityTypeId) {
		this.activityTypeId = activityTypeId;
		this.dirtyProperties.add("activityTypeId");

		return this;
	}

	public Experience setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		this.dirtyProperties.add("createdAt");

		return this;
	}

	public Experience setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
		this.dirtyProperties.add("updatedAt");

		return this;
	}

	public Experience setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
		this.dirtyProperties.add("appUserId");

		return this;
	}

	public Experience setActivityType(ActivityType activityType) {
		this.activityType = activityType;

		return this;
	}

	public Experience setAppUser(AppUser appUser) {
		this.appUser = appUser;

		return this;
	}

	@Override
	public Object getPrimaryKeyValue() {
		return this.id;
	}

}
