package com.courseapp.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "topicId" }, callSuper = false)
@ToString
@Builder
public class Topic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long topicId;

	private String name;
	private LocalTime duration;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	private LocalDateTime createdDate;

	@Version
	private LocalDateTime updatedDate;
	
	@PrePersist
	protected void onCreate(){
		this.createdDate = LocalDateTime.now();
		this.updatedDate = LocalDateTime.now();
	}
	
	@PreUpdate
    protected void onUpdate() {
		this.updatedDate = LocalDateTime.now();
    }


}
