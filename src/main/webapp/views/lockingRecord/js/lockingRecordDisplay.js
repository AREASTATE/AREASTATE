angular.module("lockingRecordDisplayModule",[]).
controller("lockingRecordDisplayController",["$scope","$state","$stateParams","LockingRecordService",function($scope,$state,$stateParams,LockingRecordService){
	$scope.id = $stateParams.id;
	
	$scope.init = function(){
		LockingRecordService.findLockingRecordById($scope.id,suc,ero);
		function suc(data){
			$scope.lockingRecord = data;
			$scope.lockingRecord.lockDate = $scope.fmtDate($scope.lockingRecord.lockDate);
			$scope.lockingRecord.submitDate = $scope.fmtDate($scope.lockingRecord.submitDate);
		}
		function ero(error){
			alert(error);
		}
		
	}
	
	/**
	 * 时间戳转换yyyy--mm--dd
	 */
	$scope.fmtDate = function(obj){
	    var date =  new Date(obj);
	    var y = 1900+date.getYear();
	    var m = "0"+(date.getMonth()+1);
	    var d = "0"+date.getDate();
	    return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
	}
	
	$scope.cancel = function(){
		$state.go("main.lockingRecordList");
	}
}]);
