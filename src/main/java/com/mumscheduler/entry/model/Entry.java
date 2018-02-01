package com.mumscheduler.entry.model;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Entry 
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotNull
	private int fppNum;
	private int mppNum;
	private String entryName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	//private LocalDate endDate;
	//private LocalDate startDate;
	
	//@Temporal(TemporalType.DATE)
	//private Date dateTest;
	
	public Entry() {}
	
	public Entry(int fppNum, int mppNum, String entryName, Date endDate, Date startDate) {
		super();
		this.fppNum = fppNum;
		this.mppNum = mppNum;
		this.entryName = entryName;
		this.endDate = endDate;
		this.startDate = startDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFppNum() {
		return fppNum;
	}


	public void setFppNum(int fppNum) {
		this.fppNum = fppNum;
	}


	public int getMppNum() {
		return mppNum;
	}


	public void setMppNum(int mppNum) {
		this.mppNum = mppNum;
	}


	public String getEntryName() {
		return entryName;
	}


	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
}
