package com.ugo.k.generated.mappers;

import com.myzlab.k.KRow;
import com.myzlab.k.annotations.Id;
import com.myzlab.k.annotations.KMetadata;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Role extends KRow {

	@Id
	@KMetadata(name = "ID")
	private Long id;

	@KMetadata(name = "NAME")
	private String name;

	@KMetadata(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@KMetadata(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	public Role setId(Long id) {
		this.id = id;
		this.dirtyProperties.add("id");

		return this;
	}

	public Role setName(String name) {
		this.name = name;
		this.dirtyProperties.add("name");

		return this;
	}

	public Role setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		this.dirtyProperties.add("createdAt");

		return this;
	}

	public Role setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
		this.dirtyProperties.add("updatedAt");

		return this;
	}

	@Override
	public Object getPrimaryKeyValue() {
		return this.id;
	}

}
