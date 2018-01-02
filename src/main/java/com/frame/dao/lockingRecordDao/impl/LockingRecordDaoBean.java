package com.frame.dao.lockingRecordDao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.dao.lockingRecordDao.LockingRecordDao;
import com.frame.entity.lockingRecord.LockingRecord;

/**
 * @author chengbangguo
 *2018年1月1日 上午11:31:58 
 */
@Repository("lockingRecordDao")
public class LockingRecordDaoBean implements LockingRecordDao{
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	private static final String namespace = "com.frame.dao.lockingRecordDao.lockingRecordDaoMapper";

	@Override
	public LockingRecord saveLockingRecord(LockingRecord lockingRecord) throws Exception {
		String addSql = namespace + ".addLockingRecord";
		sqlSessionTemplate.insert(addSql, lockingRecord);
		return null;
	}

	@Override
	public void deleteLockingRecord(Integer id) throws Exception {
		Map paramMap= new HashMap<String, Object>();
		paramMap.put("id", id);
		String selectSql = namespace + ".deleteLockingRecord";
		this.sqlSessionTemplate.delete(selectSql, paramMap);
	}

	@Override
	public LockingRecord findLockingRecordById(Integer id) throws Exception {
		Map paramMap= new HashMap<String, Object>();
		paramMap.put("id", id);
		String selectSql = namespace + ".findLockingRecordById";
		LockingRecord lockingRecord = this.sqlSessionTemplate.selectOne(selectSql,paramMap);
		return lockingRecord;
	}

	@Override
	public LockingRecord updateLockingRecord(LockingRecord lockingRecord)
			throws Exception {
		String addSql = namespace + ".updateLockingRecord";
		sqlSessionTemplate.insert(addSql, lockingRecord);
		return null;
	}

	@Override
	public List<LockingRecord> findAllLockingRecords() throws Exception {
		String selectSql = namespace + ".findAllLockingRecords";
		List<LockingRecord> lockingRecords = this.sqlSessionTemplate.selectList(selectSql);
		return lockingRecords;
	}

	@Override
	public List<LockingRecord> findLockingRecordsByLandId(Integer landId, Integer displayDay) {
		Map paramMap= new HashMap<String, Object>();
		paramMap.put("landId", landId);
		paramMap.put("displayDay", displayDay);
		String selectSql = namespace + ".findLockingRecordsByLandId";
		List<LockingRecord> lockingRecords = this.sqlSessionTemplate.selectList(selectSql,paramMap);
		return lockingRecords;
	}
	
}

