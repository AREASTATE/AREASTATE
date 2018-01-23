angular.module("mainModule",["ui.bootstrap"])
.controller("mainController",["$rootScope","$timeout","$scope","$state","$uibModal","RegisterAndLoginService",function($rootScope,$timeout,$scope,$state, $uibModal,RegisterAndLoginService){
	$scope.init = function(){
		$scope.innitWindowStyle();
		$rootScope.usr = angular.fromJson(sessionStorage.getItem("currentUser"));
		$scope.items = [{"name":"用地状态一览表","url":"",childItems:[{"name":"用地状态列表","url":"main.landStateList"}]},
		                {"name":"个人中心","url":"",childItems:[{"name":"个人信息维护","url":"main.personnalCenter"}]},
		                {"name":"我的用地锁定记录","url":"",childItems:[{"name":"我的用地锁定记录管理","url":"main.lockingRecordList"},{"name":"用地锁定申请","url":"main.lockingRecordForm"}]},
		                {"name":"公告记录","url":"",childItems:[{"name":"公告历史记录","url":"main.anouncementList"}]}];
		if($rootScope.usr&&$rootScope.usr.role=="admin"){
			$scope.items = [
			                {"name":"系统配置","url":"",childItems:[{"name":"系统参数配置列表","url":"main.sysConfigList"},{"name":"新增系统配置","url":"main.sysConfigAddForm"}]},
			                {"name":"用地管理","url":"",childItems:[{"name":"用地列表","url":"main.landList"},{"name":"新建用地","url":"main.landAddForm"}]},
			                {"name":"用地状态一览表","url":"",childItems:[{"name":"用地状态列表","url":"main.landStateList"}]},
			                {"name":"用地分配与解除管理","url":"",childItems:[{"name":"用地分配与解除管理","url":"main.landAssignedAndReliveList"}]},
			                {"name":"个人中心","url":"",childItems:[{"name":"个人信息维护","url":"main.personnalCenter"}]},
			                {"name":"我的用地锁定记录","url":"",childItems:[{"name":"我的用地锁定记录管理","url":"main.lockingRecordList"},{"name":"用地锁定申请","url":"main.lockingRecordForm"}]},
			                {"name":"公告管理","url":"",childItems:[{"name":"公告记录","url":"main.anouncementList"},{"name":"发布新公告","url":"main.anouncementAddForm"}]}];
		}
		
		//获取公告信息
		$timeout(function(){
			$scope.showNoReadAnouncement();
		},0);
		//路由到场地状态列表页面
		$state.go("main.landStateList");
	}
	
	$scope.loginOut = function(){
		RegisterAndLoginService.loginOut(suc,ero);
		function suc(data){
			$state.go("login");
		};
		function ero(){
			$("#errorhapen").modal("show");
		};
	}
	
	/**
	 * 适应屏幕高度
	 */
	$scope.innitWindowStyle = function (){
		$("#leftnav").css("height",($rootScope.wHeight - $("#navb").height()-10) + "px");
		$("#rightctex").css("height",($rootScope.wHeight - $("#navb").height()-10) + "px");
	}
	
	$scope.showNoReadAnouncement = function(){
		if ($rootScope.usr.noReadAnouncement!=null){
			$("#anouncementWindow").modal("show");
			var anouncement = $rootScope.usr.noReadAnouncement;
			$("#anouncementTitle").html(anouncement.anouncementTitle);
			$("#anouncementContent").html(anouncement.anouncementContent);
			if(anouncement.urgency=="特急"){
				$("#mestype").css("background","#ff1717");
				$("#knowthatbtn").css({"background":"#ff1717","border":"none"});
			}
			else if(anouncement.urgency=="紧急"){
				$("#mestype").css("background","rgb(243, 167, 96)");
				$("#knowthatbtn").css({"background":"rgb(243, 167, 96)","border":"none"});
			}
			else {
				$("#mestype").css("background","#1fad09");
				$("#knowthatbtn").css({"background":"#1fad09","border":"none"});
			}
		}
	}
	
	/**读取公告**/
	$scope.readedIt = function(){
		RegisterAndLoginService.readedAnouncement($rootScope.usr.loginNo,suc,ero);
		function suc(data){
			$rootScope.usr.noReadAnouncement = null;
			sessionStorage.setItem("currentUser", JSON.stringify($rootScope.usr));
			$("#anouncementWindow").modal("hide");
		};
		function ero(){
			$("#errorhapen").modal("show");
		};
	}
}]);
