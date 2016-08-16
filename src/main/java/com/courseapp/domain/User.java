package com.courseapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.courseapp.rest.validation.Phone;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "userId" }, callSuper = false)
@ToString(exclude = { "courses" })
@Builder
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Email(message = "{error.username.invalid}")
	@NotBlank(message = "{error.username.notblank}")
	private String userName;

	@NotBlank(message = "{error.fname.notblank}")
	private String fName;

	@NotBlank(message = "{error.lname.notblank}")
	private String lName;
	private String mName;

	// characters and max of 10 characters")
	@NotBlank(message = "{error.pword.notblank}")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{6,10})", message = "Password should have atleast 1 digit, 1 lowercase,1 special character and with a min length of 6 and max length of 10")
	private String password;

	// if we delete user - user to course relationship should be deleted as well
	@Getter(onMethod = @__( @JsonIgnore ))
	@ManyToMany(mappedBy = "registeredUsers", cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<>();

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
	@NotNull(message = "{error.dob.notnull}")
	private LocalDate dob;

	@Phone
	private String phoneNumber;

	private LocalDateTime createdDate;

	@Version
	private LocalDateTime updatedDate;

	@PrePersist
	protected void onCreate() {
		this.createdDate = LocalDateTime.now();
		this.updatedDate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = LocalDateTime.now();
	}

}
