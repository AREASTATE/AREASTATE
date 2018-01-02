package com.frame.service.lockingRecordService.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.dao.lockingRecordDao.LockingRecordDao;
import com.frame.dao.logDao.LogDao;
import com.frame.entity.lockingRecord.LockingRecord;
import com.frame.entity.user.User;
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
		HttpSession session = request.getSession();
		try {
			Object obj = session.getAttribute("user");
			if(obj instanceof User){
				User user = (User) obj;
				//设置当前用户
				lockingRecord.setUser(user);
				//设置提交时间
				lockingRecord.setSubmitDate(new Date());
				//设置用户电话
				lockingRecord.setUserTel(user.getTel());
			} else {
				return null;
			}
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

	@Override
	public List<LockingRecord> findLockingRecordsByLandId(Integer landId, Integer displayDay) {
		try {
			return this.lockingRecordDao.findLockingRecordsByLandId(landId,displayDay);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

