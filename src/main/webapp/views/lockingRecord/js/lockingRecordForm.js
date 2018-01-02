angular.module("lockingRecordFormModule",[]).
controller("lockingRecordFormController",["$scope","$state","$stateParams","LockingRecordService","LandService",function($scope,$state,$stateParams,LockingRecordService,LandService){
	$scope.id = $stateParams.id;
	$scope.operate = $stateParams.operate;
	
	$scope.init = function(){
		$scope.lockingRecord = {};
		$scope.lands = {};
		if($scope.operate == "edit"){
			LockingRecordService.findLockingRecordById($scope.id,suc,ero);
			function suc(data){
				$scope.lockingRecord = data;
			}
			function ero(error){
				alert(error);
			}
		}
		else{
			LandService.findAllLandsByState(suc,ero);
			function suc(data){
				$scope.lands = data;
			}
			function ero(error){
				alert(error);
			}
		}
		
	}
	
	$scope.cancel = function(){
		$state.go("main.lockingRecordList");
	}
	
	$scope.saveLockingRecord = function(){
		if($scope.operate=="edit"){
			LockingRecordService.updateLockingRecord($scope.lockingRecord,suc,ero);
		}
		else{
			LockingRecordService.saveLockingRecord($scope.lockingRecord,suc,ero);
		}
		function suc(data){
			$state.go("main.lockingRecordList");
		}
		
		function ero(){
			
		}
	}
}]);
