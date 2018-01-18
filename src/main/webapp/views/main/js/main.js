angular.module("mainModule",["ui.bootstrap"])
.controller("mainController",["$rootScope","$scope","$state","$uibModal","RegisterAndLoginService",function($rootScope,$scope,$state, $uibModal,RegisterAndLoginService){
	$scope.init = function(){
		$rootScope.usr = angular.fromJson(localStorage.getItem("currentUser"));
		$scope.items = [{"name":"用地状态一览表","url":"",childItems:[{"name":"用地状态列表","url":"main.landStateList"}]},
		                {"name":"个人中心","url":"",childItems:[{"name":"个人信息维护","url":"main.personnalCenter"}]},
		                {"name":"我的用地锁定记录","url":"",childItems:[{"name":"我的用地锁定记录管理","url":"main.lockingRecordList"},{"name":"用地锁定申请","url":"main.lockingRecordForm"}]}];
		if($rootScope.usr&&$rootScope.usr.role=="admin"){
			$scope.items = [
			                {"name":"系统配置","url":"",childItems:[{"name":"系统参数配置列表","url":"main.sysConfigList"},{"name":"新增系统配置","url":"main.sysConfigAddForm"}]},
			                {"name":"用地管理","url":"",childItems:[{"name":"用地列表","url":"main.landList"},{"name":"新建用地","url":"main.landAddForm"}]},
			                {"name":"用地状态一览表","url":"",childItems:[{"name":"用地状态列表","url":"main.landStateList"}]},
			                {"name":"用地分配与解除管理","url":"",childItems:[{"name":"用地分配与解除管理","url":"main.landAssignedAndReliveList"}]},
			                {"name":"个人中心","url":"",childItems:[{"name":"个人信息维护","url":"main.personnalCenter"}]},
			                {"name":"我的用地锁定记录","url":"",childItems:[{"name":"我的用地锁定记录管理","url":"main.lockingRecordList"},{"name":"用地锁定申请","url":"main.lockingRecordForm"}]}];
		}
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
	
	//全屏与缩小屏幕
//	$scope.changeScreenSize = function(){
//		var clickele = $("#fusmal");
//		var eleclasses = (clickele.attr('class')).split(" ");
//		if(eleclasses.includes("glyphicon-resize-small")){
//			clickele.removeClass("glyphicon-resize-small");
//			clickele.addClass("glyphicon-resize-full");
//			$("#leftnav").addClass("col-md-offset-1");
//			$("#rightcontent").removeClass("col-xs-10").addClass("col-xs-7");
//		}
//		else{
//			clickele.removeClass("glyphicon-resize-full");
//			clickele.addClass("glyphicon-resize-small");
//			$("#leftnav").addClass("col-md-offset-1");
//			$("#rightcontent").removeClass("col-xs-7").addClass("col-xs-10");
//		}
//	}
	
//	$scope.innitWindowStyle = function (){
//		$("#leftnav").css("height",($rootScope.wHeight - 90) + "px");
//		$("#rightcontent").css("height",($rootScope.wHeight - 90) + "px");
//		$("#rightcontent").css("overflowY","auto");
//	}
}]);
