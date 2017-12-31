angular.module("sysConfigListModule",[]).
controller("sysConfigListController",["$scope","$state","SysConfigService",function($scope,$state,SysConfigService){
	
	$scope.init = function(){
		SysConfigService.findAllSysConfigs(suc,ero);
		function suc(data){
			$scope.sysConfigList = data;
		}
		function ero(error){
			alert(error);
		}
	}
	
	$scope.editSysConfig = function(id){
		$state.go("main.sysConfigEditForm",{"id":id,"operate":"edit"});
	}
	
	$scope.displaySysConfig = function(id){
		$state.go("main.sysConfigDisplay",{"id":id});
	}
	
	$scope.deleteSysConfig = function(id){
		SysConfigService.deleteSysConfig(id,suc,ero);
		function suc(data){
			alert("删除成功");
			$scope.init();
		}
		function ero(error){
			alert(error);
		}
	}
}]);
