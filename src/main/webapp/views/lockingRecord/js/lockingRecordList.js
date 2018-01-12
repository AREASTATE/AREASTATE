angular.module("lockingRecordListModule",[]).
controller("lockingRecordListController",["$scope","$state","LockingRecordService",function($scope,$state,LockingRecordService){
	
	$scope.init = function(){
		LockingRecordService.findAllLockingRecordsByUserId(suc,ero);
		function suc(data){
			$scope.lockingRecordList = data;
			for (var i = 0; i < $scope.lockingRecordList.length; i++) {
				$scope.lockingRecordList[i].lockDate = $scope.fmtDate($scope.lockingRecordList[i].lockDate);
				$scope.lockingRecordList[i].submitDate = $scope.fmtDate($scope.lockingRecordList[i].submitDate);
			}
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
	
	$scope.abolishLockingRecord = function(id){
		LockingRecordService.abolishLockingRecord(id,suc,ero);
		function suc(data){
			alert("废除成功");
			$scope.init();
		}
		function ero(error){
			alert(error);
		}
	}
	
	$scope.displayLockingRecord = function(id){
		$state.go("main.lockingRecordDisplay",{"id":id});
	}
	
	$scope.deleteLockingRecord = function(id){
		LockingRecordService.deleteLockingRecord(id,suc,ero);
		function suc(data){
			alert("删除成功");
			$scope.init();
		}
		function ero(error){
			alert(error);
		}
	}
}]);
