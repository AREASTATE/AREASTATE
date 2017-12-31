package com.frame.controller.sysConfigController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.entity.sysconfig.SysConfig;
import com.frame.service.sysConfigService.SysConfigService;

@Controller
@RequestMapping("/sysConfigController")
public class SysConfigController {
	@Autowired
	private SysConfigService sysConfigService; 
	
	@RequestMapping(value="/saveSysConfig",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
	public SysConfig saveSysConfig(@RequestBody SysConfig sysConfig, HttpServletRequest request){
		return this.sysConfigService.saveSysConfig(sysConfig,request);
	}
	
	@RequestMapping(value="/findSysConfigById",method = RequestMethod.POST)
    @ResponseBody
	public SysConfig findSysConfigById(@RequestParam("id") Integer id, HttpServletRequest request) {
		return this.sysConfigService.findSysConfigById(id,request);
	}
	
	@RequestMapping(value="/findSysConfigByProperty",method = RequestMethod.POST)
    @ResponseBody
	public SysConfig findSysConfigByProperty(@RequestParam("property") String property, HttpServletRequest request) {
		return this.sysConfigService.findSysConfigByProperty(property, request);
	}
	
	@RequestMapping(value="/deleteSysConfig",method = RequestMethod.POST)
    @ResponseBody
	public void deleteSysConfig(@RequestParam("id") Integer id, HttpServletRequest request) {
		this.sysConfigService.deleteSysConfig(id, request);
	}
	
	@RequestMapping(value="/updateSysConfig",method = RequestMethod.POST)
    @ResponseBody
	public SysConfig updateSysConfig(@RequestBody SysConfig sysConfig, HttpServletRequest request){
		return this.sysConfigService.updateSysConfig(sysConfig,request);
	}
	
    
    @RequestMapping(value="/findAllSysConfigs",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<SysConfig> findAllSysConfigs(HttpServletRequest request){
    	return this.sysConfigService.findAllSysConfigs(request);
    }
    
}
