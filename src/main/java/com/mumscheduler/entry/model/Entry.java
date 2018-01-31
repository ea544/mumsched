package com.mumscheduler.entry.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


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
	private LocalDate endDate;
	private LocalDate startDate;
	
	
	public Entry(int fppNum, int mppNum, String entryName, LocalDate endDate, LocalDate startDate) {
		super();
		this.fppNum = fppNum;
		this.mppNum = mppNum;
		this.entryName = entryName;
		this.endDate = endDate;
		this.startDate = startDate;
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


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	
}
