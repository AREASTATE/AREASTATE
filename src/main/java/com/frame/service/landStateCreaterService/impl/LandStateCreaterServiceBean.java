package com.frame.service.landStateCreaterService.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.frame.entity.landDailyState.LandDailyState;
import com.frame.service.landStateCreaterService.LandStateCreaterService;

@Service("landStateCreaterService")
public class LandStateCreaterServiceBean implements LandStateCreaterService{

	@Override
	public List<LandDailyState> getLandDailyState(String land,HttpServletRequest request) {
		return landDailyStateHander(land);
	}


	/**
	 * TODO:初始化从今天开始到后面15天的用地状态信息s
	 * @return List<LandDailyState>
	 * @author 李桥
	 * @time 2017年12月30日
	 */
	private List<LandDailyState> initialLandDailyState(){
		ArrayList<LandDailyState> landDailyStates = new ArrayList<LandDailyState>();
		//初始化上午、下午、晚上都是可用状态
		for(int i = 0; i < 5; i ++){
			Calendar calendar = Calendar.getInstance();
			LandDailyState landDailyState = new LandDailyState();
			calendar.add(Calendar.DATE, i);
			landDailyState.setDate(calendar.getTime());
			landDailyState.setAmState("可用");
			landDailyState.setPmState("可用");
			landDailyState.setNightState("可用");
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
	private List<LandLockApplication> loadMySqlDataOfLandLockApplication(String land){
		String [] stage = {"上午","下午","晚上"};

		String [] state = {"可用","锁定","已分配"};

		ArrayList<LandLockApplication> landLockApplications = new ArrayList<LandLockApplication>();

		for(int i = 0; i < 5; i ++){
			Calendar calendar = Calendar.getInstance();
			LandLockApplication landLockApplication = new LandLockApplication();
			int index = (int)(Math.round(Math.random()*2));
			calendar.add(Calendar.DATE, i);
			landLockApplication.setDate(calendar.getTime());

			landLockApplication.setLand(land);

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
	private List<LandDailyState> landDailyStateHander(String land){
		ArrayList<LandLockApplication> landLockApplications = (ArrayList<LandLockApplication>) loadMySqlDataOfLandLockApplication(land);
		List<LandDailyState> landDailyStates = initialLandDailyState();
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

class LandLockApplication{

	public String land;
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
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
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
}
