package com.frame.service.lockingRecordService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.frame.entity.lockingRecord.LockingRecord;

/**
 * @author chengbangguo
 *2018年1月1日 上午11:30:44 
 */
public interface LockingRecordService {
	public LockingRecord  saveLockingRecord(LockingRecord lockingRecord, HttpServletRequest request);
	
	public void deleteLockingRecord(Integer id, HttpServletRequest request);
	
	public LockingRecord findLockingRecordById(Integer id, HttpServletRequest request);
	
	public LockingRecord updateLockingRecord(LockingRecord lockingRecord, HttpServletRequest request);
	
	public List<LockingRecord> findAllLockingRecordsByUserId(HttpServletRequest request);
	
	public List<LockingRecord> findAllLockingRecords(HttpServletRequest request);
	
	public List<LockingRecord> findLockingRecordsByLandId(Integer landId, Integer displayDay, HttpServletRequest request);
	
	public LockingRecord abolishLockingRecord(Integer id, HttpServletRequest request);
}

