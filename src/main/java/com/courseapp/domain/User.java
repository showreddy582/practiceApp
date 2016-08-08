package com.courseapp.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.courseapp.rest.validation.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class User extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;

	@Email(message="{error.username.invalid}")
	@NotBlank(message="{error.username.notblank}")
	private String userName;
	
	@NotBlank(message="{error.fname.notblank}")
	private String fName;
	
	@NotBlank(message="{error.lname.notblank}")
	private String lName;
	private String mName;
	
	//@Length(min=6, max=10,message="Password length should have a minimum of 6 characters and max of 10 characters")
	@NotBlank(message="{error.pword.notblank}")
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,10})", message="Password should have atleast 1 digit, 1 lowercase, 1 uppercase and with a min length of 6 and max length of 10")
	private String password;
	
	@JsonIgnore
	@ManyToMany(mappedBy="registeredUsers")
	private List<Course> courses = new ArrayList<>();
	
	//@DateTimeFormat(pattern="MM/dd/yyyy")
    //@NotNull
	//@Past(message="DOB can not be in future")
	private LocalDate dob;
	
	@Phone
	private String phoneNumber;
	
	public User(){
		
	}
	
	public User(Long userId, String userName, String fName, String lName, String mName, String password, LocalDate dob,
			String phoneNumber, LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.fName = fName;
		this.lName = lName;
		this.mName = mName;
		this.password = password;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.setCreatedDate(createdDate);
		this.setUpdatedDate(updatedDate);
	}

	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public LocalDate getDob() {
		return dob;
	}
	
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", fName=" + fName + ", lName=" + lName
				+ ", mName=" + mName + ", password=" + password + ", dob=" + dob + ", phoneNumber=" + phoneNumber + "]";
	}

	
}
