package com.ugo.k.generated.mappers;

import com.myzlab.k.KRow;
import com.myzlab.k.annotations.Id;
import com.myzlab.k.annotations.KMetadata;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Assessment extends KRow {

	@Id
	@KMetadata(name = "ID")
	private Long id;

	@KMetadata(name = "APP_USER_ID")
	private Long appUserId;

	@KMetadata(name = "COMMENT")
	private String comment;

	@KMetadata(name = "RATING")
	private Integer rating;

	@KMetadata(name = "EXPERIENCE_ID")
	private Long experienceId;

	@KMetadata(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@KMetadata(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	private AppUser appUser;
	private Experience experience;

	public Assessment setId(Long id) {
		this.id = id;
		this.dirtyProperties.add("id");

		return this;
	}

	public Assessment setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
		this.dirtyProperties.add("appUserId");

		return this;
	}

	public Assessment setComment(String comment) {
		this.comment = comment;
		this.dirtyProperties.add("comment");

		return this;
	}

	public Assessment setRating(Integer rating) {
		this.rating = rating;
		this.dirtyProperties.add("rating");

		return this;
	}

	public Assessment setExperienceId(Long experienceId) {
		this.experienceId = experienceId;
		this.dirtyProperties.add("experienceId");

		return this;
	}

	public Assessment setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		this.dirtyProperties.add("createdAt");

		return this;
	}

	public Assessment setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
		this.dirtyProperties.add("updatedAt");

		return this;
	}

	public Assessment setAppUser(AppUser appUser) {
		this.appUser = appUser;

		return this;
	}

	public Assessment setExperience(Experience experience) {
		this.experience = experience;

		return this;
	}

	@Override
	public Object getPrimaryKeyValue() {
		return this.id;
	}

}
