package com.courseapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Course extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseId;
	private String courseName;
	private String author;
	
	@Enumerated(EnumType.STRING)
	private SkillLevel level;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "course_user", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
	private List<User> registeredUsers = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="course", cascade=CascadeType.ALL)
	private List<Topic> topics = new ArrayList<>();

	public SkillLevel getLevel() {
		return level;
	}
	
	public void setLevel(SkillLevel level) {
		this.level = level;
	}
	
	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<User> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(List<User> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
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
		Course other = (Course) obj;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		return true;
	}

}
