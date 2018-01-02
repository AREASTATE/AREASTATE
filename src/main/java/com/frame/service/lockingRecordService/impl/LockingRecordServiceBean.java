package com.frame.service.lockingRecordService.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.dao.lockingRecordDao.LockingRecordDao;
import com.frame.dao.logDao.LogDao;
import com.frame.entity.lockingRecord.LockingRecord;
import com.frame.service.lockingRecordService.LockingRecordService;

/**
 * @author chengbangguo
 *2018年1月1日 上午11:31:03 
 */
@Service("lockingRecordService")
public class LockingRecordServiceBean implements LockingRecordService{

	@Autowired
	private LockingRecordDao lockingRecordDao;
	@Autowired
	private LogDao logDao;
	
	@Override
	public LockingRecord saveLockingRecord(LockingRecord lockingRecord,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			return lockingRecordDao.saveLockingRecord(lockingRecord);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteLockingRecord(Integer id, HttpServletRequest request) {
		try {
			this.lockingRecordDao.deleteLockingRecord(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LockingRecord findLockingRecordById(Integer id,
			HttpServletRequest request) {
		try {
			return this.lockingRecordDao.findLockingRecordById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LockingRecord updateLockingRecord(LockingRecord lockingRecord,
			HttpServletRequest request) {
		try {
			lockingRecordDao.updateLockingRecord(lockingRecord);
			return lockingRecord;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<LockingRecord> findAllLockingRecords(HttpServletRequest request) {
		try {
			return this.lockingRecordDao.findAllLockingRecords();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

