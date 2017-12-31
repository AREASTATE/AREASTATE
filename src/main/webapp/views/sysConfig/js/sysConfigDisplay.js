angular.module("sysConfigDisplayModule",[]).
controller("sysConfigDisplayController",["$scope","$state","$stateParams","SysConfigService",function($scope,$state,$stateParams,SysConfigService){
	$scope.id = $stateParams.id;
	
	$scope.init = function(){
		SysConfigService.findSysConfigById($scope.id,suc,ero);
		function suc(data){
			$scope.sysConfig = data;
		}
		function ero(error){
			alert(error);
		}
		
	}
	
	$scope.cancel = function(){
		$state.go("main.sysConfigList");
	}
}]);
