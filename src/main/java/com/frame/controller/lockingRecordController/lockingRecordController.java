package com.frame.controller.lockingRecordController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.entity.lockingRecord.LockingRecord;
import com.frame.service.lockingRecordService.LockingRecordService;

/**
 * @author chengbangguo
 *2018年1月1日 下午3:12:23 
 */
@Controller
@RequestMapping("/lockingRecordController")
public class lockingRecordController {
	@Autowired
	private LockingRecordService lockingRecordService;
	
	@RequestMapping(value="/saveLockingRecord",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
	public LockingRecord saveLockingRecord(@RequestBody LockingRecord lockingRecord, HttpServletRequest request){
		return lockingRecordService.saveLockingRecord(lockingRecord,request);
	}
	
	@RequestMapping(value="/findLockingRecordById",method = RequestMethod.POST)
    @ResponseBody
	public LockingRecord findLockingRecordById(@RequestParam("id") Integer id, HttpServletRequest request) {
		return this.lockingRecordService.findLockingRecordById(id,request);
	}
	
	@RequestMapping(value="/deleteLockingRecord",method = RequestMethod.POST)
    @ResponseBody
	public void deleteLockingRecord(@RequestParam("id") Integer id, HttpServletRequest request) {
		this.lockingRecordService.deleteLockingRecord(id, request);
	}
	
	@RequestMapping(value="/updateLockingRecord",method = RequestMethod.POST)
    @ResponseBody
	public LockingRecord updateLockingRecord(@RequestBody LockingRecord LockingRecord, HttpServletRequest request){
		return lockingRecordService.updateLockingRecord(LockingRecord,request);
	}
	
    
    @RequestMapping(value="/findAllLockingRecords",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<LockingRecord> findAllLockingRecords(HttpServletRequest request){
    	return this.lockingRecordService.findAllLockingRecords(request);
    }
    
    @RequestMapping(value="/findAllLockingRecordsByUserId",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<LockingRecord> findAllLockingRecordsByUserId(HttpServletRequest request){
    	return this.lockingRecordService.findAllLockingRecordsByUserId(request);
    }
    
    @RequestMapping(value="/abolishLockingRecord",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public LockingRecord abolishLockingRecord(@RequestParam("id") Integer id,HttpServletRequest request){
    	return this.lockingRecordService.abolishLockingRecord(id,request);
    }
}

