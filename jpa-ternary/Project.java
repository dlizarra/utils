package com.dlizarra.startuphub.project;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Project {

	static final int MAX_LENGTH_NAME = 100;
	static final int MAX_LENGTH_DESCRIPTION = 1000;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = MAX_LENGTH_NAME)
	private String name;

	@Column(length = MAX_LENGTH_DESCRIPTION)
	private String description;

	@Column
	private LocalDateTime creationTime;

	@Column
	private LocalDateTime modificationTime;

	@OneToMany(mappedBy = "pk.project", fetch = FetchType.EAGER)
	private Set<ProjectUserPosition> projectUserPositions = new HashSet<ProjectUserPosition>();

	@PrePersist
	public void prePersist() {
		creationTime = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		modificationTime = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(final LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(final LocalDateTime modificationTime) {
		this.modificationTime = modificationTime;
	}

	public Set<ProjectUserPosition> getProjectUserPositions() {
		return projectUserPositions;
	}

	public void setProjectUserPositions(final Set<ProjectUserPosition> projectUserPositions) {
		this.projectUserPositions = projectUserPositions;
	}
}
