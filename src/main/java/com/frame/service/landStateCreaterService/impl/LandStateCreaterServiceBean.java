package com.frame.service.landStateCreaterService.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.entity.land.Land;
import com.frame.entity.landDailyState.LandDailyState;
import com.frame.entity.sysconfig.SysConfig;
import com.frame.service.landService.LandService;
import com.frame.service.landStateCreaterService.LandStateCreaterService;
import com.frame.service.sysConfigService.SysConfigService;

@Service("landStateCreaterService")
public class LandStateCreaterServiceBean implements LandStateCreaterService{
	
	@Autowired
	private LandService landService;
	@Autowired
	private SysConfigService sysConfigService;

	@Override
	public List<LandDailyState> getLandDailyState(Integer landId,HttpServletRequest request) {
		SysConfig sysConfig = sysConfigService.findSysConfigByProperty("展示用地状态数据天数", request);
		List<LandDailyState> landDailyStates;
		if(sysConfig==null){
			/**
			 * 默认展示15天状态
			 */
			landDailyStates = landDailyStateHander(landId,15);
		}else{
			Integer displayDay = Integer.parseInt(sysConfig.getValue());
			landDailyStates = landDailyStateHander(landId,displayDay);
		}
		return landDailyStates;
	}


	/**
	 * TODO:初始化从今天开始到后面15天的用地状态信息
	 * @return List<LandDailyState>
	 * @author 李桥
	 * @time 2017年12月30日
	 */
	private List<LandDailyState> initialLandDailyState(Integer landId,Integer displayDay){
		ArrayList<LandDailyState> landDailyStates = new ArrayList<LandDailyState>();
		//用地信息
		Land land = landService.findLandById(landId, null);
		//初始化上午、下午、晚上都是可用状态
		for(int i = 0; i < displayDay; i ++){
			Calendar calendar = Calendar.getInstance();
			LandDailyState landDailyState = new LandDailyState();
			calendar.add(Calendar.DATE, i);
			landDailyState.setDate(calendar.getTime());
			landDailyState.setAmState("可用");
			landDailyState.setPmState("可用");
			landDailyState.setNightState("可用");
			landDailyState.setLand(land);
			landDailyStates.add(landDailyState);
		}
		return landDailyStates;
	} 

	/**
	 * TODO:模拟查询数据库，生成用地锁定申请记录信息
	 * @return List<LandLockApplication>
	 * @author 李桥
	 * @time 2017年12月30日
	 */
	private List<LandLockApplication> loadMySqlDataOfLandLockApplication(Integer landId,Integer displayDay){
		String [] stage = {"上午","下午","晚上"};

		String [] state = {"可用","锁定","已分配"};

		ArrayList<LandLockApplication> landLockApplications = new ArrayList<LandLockApplication>();

		for(int i = 0; i < displayDay; i ++){
			Calendar calendar = Calendar.getInstance();
			LandLockApplication landLockApplication = new LandLockApplication();
			int index = (int)(Math.round(Math.random()*2));
			calendar.add(Calendar.DATE, i);
			landLockApplication.setDate(calendar.getTime());

			landLockApplication.setDateStage(stage[index]);

			landLockApplication.setLandState(state[index]);

			landLockApplications.add(landLockApplication);
		}
		return landLockApplications;
	}

	/**
	 * TODO:将初始化的用地数据和查出的数据库锁定申请记录数据结合进行处理，得到最终结果
	 * @return List<LandDailyState>
	 * @author 李桥
	 * @time 2017年12月30日
	 */
	private List<LandDailyState> landDailyStateHander(Integer landId,Integer displayDay){
		ArrayList<LandLockApplication> landLockApplications = (ArrayList<LandLockApplication>) loadMySqlDataOfLandLockApplication(landId,displayDay);
		List<LandDailyState> landDailyStates = initialLandDailyState(landId,displayDay);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
		for(int i = 0; i < landLockApplications.size(); i ++){
			for(int j = 0; j < landDailyStates.size(); j ++){
				LandLockApplication landLockApplication = landLockApplications.get(i);
				LandDailyState landDailyState = landDailyStates.get(j);
				//是当天
				if(df.format(landLockApplication.getDate()).compareTo(df.format(landDailyState.getDate())) == 0){
					if(landLockApplication.getDateStage() == "上午"){
						landDailyState.setAmState(landLockApplication.getLandState());
					}
					if(landLockApplication.getDateStage() == "下午"){
						landDailyState.setPmState(landLockApplication.getLandState());
					}
					if(landLockApplication.getDateStage() == "晚上"){
						landDailyState.setNightState(landLockApplication.getLandState());
					}
				}
			}
		}
		
		return landDailyStates;
	}
}

/**
 * @author 李桥
 * 模拟用地锁定的类，后期替换
 */
class LandLockApplication{

	public Land land;
	public Date date;
	public String dateStage;
	public String lockuser;
	public Date lockDate;
	public String landState;

	public String getLandState() {
		return landState;
	}
	public void setLandState(String landState) {
		this.landState = landState;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateStage() {
		return dateStage;
	}

	public void setDateStage(String dateStage) {
		this.dateStage = dateStage;
	}

	public String getLockuser() {
		return lockuser;
	}

	public void setLockuser(String lockuser) {
		this.lockuser = lockuser;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}
	public Land getLand() {
		return land;
	}
	public void setLand(Land land) {
		this.land = land;
	}
	
}
