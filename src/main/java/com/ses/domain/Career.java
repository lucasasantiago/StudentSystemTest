package com.ses.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "career")
@SequenceGenerator(name="my_seq", initialValue=1, allocationSize=100)
public class Career {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
	@Column(name = "id_career", nullable = false)
	private Long id_career;
	
	@Column(name = "name", nullable = false, unique= true)
	private String name;
	
	@OneToMany(mappedBy = "career", fetch = FetchType.EAGER)
	private List<Student> students;
	
	public Long getId_career() {
		return id_career;
	}
	public void setId_career(Long id_career) {
		this.id_career = id_career;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
