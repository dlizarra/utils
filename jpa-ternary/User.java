package com.dlizarra.startuphub.user;

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
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dlizarra.startuphub.CustomUserDetails;
import com.dlizarra.startuphub.project.ProjectUserPosition;
import com.dlizarra.startuphub.role.Role;

@Entity
@Table(name = "users")
public class User {

	static final int MAX_LENGTH_USERNAME = 30;
	static final int MIN_LENGTH_PASSWORD = 8;
	static final int MAX_LENGTH_PASSWORD = 16;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true, length = MAX_LENGTH_USERNAME)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column
	private boolean enabled;

	@Column
	private LocalDateTime creationTime;

	@Column
	private LocalDateTime modificationTime;

	@OneToMany(mappedBy = "pk.user", fetch = FetchType.EAGER)
	private Set<ProjectUserPosition> projectUserPositions = new HashSet<ProjectUserPosition>();

	public User() {
	}

	/**
	 * Constructor used exclusively by {@link CustomUserDetails}}
	 *
	 * @param user
	 */
	public User(final User user) {
		this.id = user.id;
		this.username = user.username;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public Set<ProjectUserPosition> getProjectUserPositions() {
		return projectUserPositions;
	}

	public void setProjectUserPositions(final Set<ProjectUserPosition> projectUserPositions) {
		this.projectUserPositions = projectUserPositions;
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

}
