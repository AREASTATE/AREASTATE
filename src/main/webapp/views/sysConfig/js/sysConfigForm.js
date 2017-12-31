angular.module("sysConfigFormModule",[]).
controller("sysConfigFormController",["$scope","$state","$stateParams","SysConfigService",function($scope,$state,$stateParams,SysConfigService){
	$scope.id = $stateParams.id;
	$scope.operate = $stateParams.operate;
	
	$scope.init = function(){
		$scope.sysConfig = {};
		if($scope.operate == "edit"){
			SysConfigService.findSysConfigById($scope.id,suc,ero);
			function suc(data){
				$scope.sysConfig = data;
			}
			function ero(error){
				alert(error);
			}
		}
	}
	
	$scope.cancel = function(){
		$state.go("main.sysConfigList");
	}
	
	$scope.saveSysConfig = function(){
		if($scope.operate=="edit"){
			SysConfigService.updateSysConfig($scope.sysConfig,suc,ero);
		}
		else{
			$scope.sysConfig.inputDate = new Date();
			SysConfigService.saveSysConfig($scope.sysConfig,suc,ero);
		}
		function suc(data){
			$state.go("main.sysConfigList");
		}
		
		function ero(){
			
		}
	}
}]);
