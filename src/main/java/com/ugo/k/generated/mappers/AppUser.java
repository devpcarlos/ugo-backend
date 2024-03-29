package com.ugo.k.generated.mappers;

import com.myzlab.k.KRow;
import com.myzlab.k.annotations.Id;
import com.myzlab.k.annotations.KMetadata;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class AppUser extends KRow {

	@Id
	@KMetadata(name = "ID")
	private Long id;

	@KMetadata(name = "ROLE_ID")
	private Long roleId;

	@KMetadata(name = "NAME")
	private String name;

	@KMetadata(name = "PATERNAL_SURNAME")
	private String paternalSurname;

	@KMetadata(name = "MATERNAL_SURNAME")
	private String maternalSurname;

	@KMetadata(name = "EMAIL")
	private String email;

	@KMetadata(name = "PASSWORD")
	private String password;

	@KMetadata(name = "EMAIL_CONFIRMED")
	private Boolean emailConfirmed;

	@KMetadata(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@KMetadata(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	private Role role;

	public AppUser setId(Long id) {
		this.id = id;
		this.dirtyProperties.add("id");

		return this;
	}

	public AppUser setRoleId(Long roleId) {
		this.roleId = roleId;
		this.dirtyProperties.add("roleId");

		return this;
	}

	public AppUser setName(String name) {
		this.name = name;
		this.dirtyProperties.add("name");

		return this;
	}

	public AppUser setPaternalSurname(String paternalSurname) {
		this.paternalSurname = paternalSurname;
		this.dirtyProperties.add("paternalSurname");

		return this;
	}

	public AppUser setMaternalSurname(String maternalSurname) {
		this.maternalSurname = maternalSurname;
		this.dirtyProperties.add("maternalSurname");

		return this;
	}

	public AppUser setEmail(String email) {
		this.email = email;
		this.dirtyProperties.add("email");

		return this;
	}

	public AppUser setPassword(String password) {
		this.password = password;
		this.dirtyProperties.add("password");

		return this;
	}

	public AppUser setEmailConfirmed(Boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
		this.dirtyProperties.add("emailConfirmed");

		return this;
	}

	public AppUser setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		this.dirtyProperties.add("createdAt");

		return this;
	}

	public AppUser setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
		this.dirtyProperties.add("updatedAt");

		return this;
	}

	public AppUser setRole(Role role) {
		this.role = role;

		return this;
	}

	@Override
	public Object getPrimaryKeyValue() {
		return this.id;
	}

}
