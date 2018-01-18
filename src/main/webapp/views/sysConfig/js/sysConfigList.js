angular.module("sysConfigListModule",[]).
controller("sysConfigListController",["$scope","$state","SysConfigService",function($scope,$state,SysConfigService){
	
	$scope.init = function(){
		$("#loading").modal("show");
		SysConfigService.findAllSysConfigs(suc,ero);
		function suc(data){
			$scope.sysConfigList = data;
			$("#loading").modal("hide");
		}
		function ero(error){
			$("#errorhapen").modal("show");
		}
	}
	
	$scope.editSysConfig = function(id){
		$state.go("main.sysConfigEditForm",{"id":id,"operate":"edit"});
	}
	
	$scope.displaySysConfig = function(id){
		$state.go("main.sysConfigDisplay",{"id":id});
	}
	
	$scope.deleteSysConfig = function(id){
		$("#effectiveing").modal("show");
		SysConfigService.deleteSysConfig(id,suc,ero);
		function suc(data){
			$("#effectiveing").modal("hide");
			$scope.init();
		}
		function ero(error){
			$("#errorhapen").modal("show");
		}
	}
}]);
