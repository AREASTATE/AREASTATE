package com.frame.entity.landDailyState;

import java.util.Date;

public class LandDailyState {
	
	public String land;
	
	public String amState;
	
	public String pmState;
	
	public String nightState;
	
	public Date date;

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getAmState() {
		return amState;
	}

	public void setAmState(String amState) {
		this.amState = amState;
	}

	public String getPmState() {
		return pmState;
	}

	public void setPmState(String pmState) {
		this.pmState = pmState;
	}

	public String getNightState() {
		return nightState;
	}

	public void setNightState(String nightState) {
		this.nightState = nightState;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
