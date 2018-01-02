package com.frame.dao.lockingRecordDao;

import java.util.List;

import com.frame.entity.land.Land;
import com.frame.entity.lockingRecord.LockingRecord;

/**
 * @author chengbangguo
 *2018年1月1日 上午11:31:37 
 */
public interface LockingRecordDao {
	public LockingRecord saveLockingRecord(LockingRecord lockingRecord) throws Exception;
	
	public void deleteLockingRecord(Integer id) throws Exception;
	
	public LockingRecord findLockingRecordById(Integer id) throws Exception;

	public LockingRecord updateLockingRecord(LockingRecord lockingRecord) throws Exception;
	
	public List<LockingRecord> findAllLockingRecords() throws Exception;
}

