angular.module("mainModule",["ui.bootstrap"])
.controller("mainController",["$rootScope","$scope","$state","$uibModal",function($rootScope,$scope,$state, $uibModal){
	$scope.items = [
	                {"name":"系统配置","url":"",childItems:[{"name":"系统参数配置列表","url":"main.sysConfigList"},{"name":"新增系统配置","url":"main.sysConfigAddForm"}]},
	                {"name":"用地管理","url":"",childItems:[{"name":"用地列表","url":"main.landList"},{"name":"新建用地","url":"main.landAddForm"}]},
	                {"name":"用地状态一览表","url":"",childItems:[{"name":"用地状态列表","url":"main.landStateList"}]},
	                {"name":"用地分配与解除","url":"",childItems:[{"name":"用地分配与解除管理","url":""}]},
	                {"name":"个人中心","url":"",childItems:[{"name":"个人信息维护","url":""}]},
	                {"name":"我的用地锁定记录","url":"",childItems:[{"name":"我的用地锁定记录管理","url":""}]}];
	
	$scope.init = function(){
		$state.go("main.landStateList");
	}
}]);
