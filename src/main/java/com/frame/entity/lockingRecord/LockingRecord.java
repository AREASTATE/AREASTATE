package com.frame.entity.lockingRecord;

import java.util.Date;

import com.frame.entity.land.Land;

/**
 * @author chengbangguo
 *2018年1月1日 上午9:38:07 
 */
public class LockingRecord {
	//主键
	private Integer id;
	
	//申请人
	private String applicant;
	
	//申请使用日期
	private Date lockDate;
	
	//申请时间段
	private String timeQuantum;
	
	//申请场地
	private Land land;
	
	//提交时间
	private Date submitDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public String getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(String timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
}

