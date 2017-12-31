package com.frame.dao.sysConfigDao;

import java.util.List;

import com.frame.entity.sysconfig.SysConfig;

public interface SysConfigDao {
	public SysConfig saveSysConfig(SysConfig SysConfig) throws Exception;
	
	public void deleteSysConfig(Integer id) throws Exception;
	
	SysConfig findSysConfigById(Integer id) throws Exception;

	SysConfig updateSysConfig(SysConfig SysConfig) throws Exception;
	
	public List<SysConfig> findAllSysConfigs() throws Exception;

	SysConfig findSysConfigByProperty(String property) throws Exception;
}
