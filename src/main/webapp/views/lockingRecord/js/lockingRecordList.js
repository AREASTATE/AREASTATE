angular.module("lockingRecordListModule",[]).
controller("lockingRecordListController",["$rootScope","$scope","$state","LockingRecordService",function($rootScope,$scope,$state,LockingRecordService){
	$scope.pageSize = 8;
	$scope.currentPage = 1;  
	$scope.searchCondition = "";
	$scope.init = function(){
//		LockingRecordService.findAllLockingRecordsByUserId(suc,ero);
//		function suc(data){
//			$scope.lockingRecordList = data;
//		}
//		function ero(error){
//				$("#errorhapen").modal("show");
//		}
		$("#loading").modal("show");
		LockingRecordService.getSearchPageList($scope.currentPage,$scope.pageSize,$scope.searchCondition,$rootScope.usr.id,suc,ero);
		function suc(data){
			$scope.lockingRecordList = data.pageList;
			$scope.totalItems = data.totalItems;  
			$scope.totalPage = Math.ceil($scope.totalItems/$scope.pageSize);
			$("#loading").modal("hide");
		};
		function ero(error){
			$("#errorhapen").modal("show");
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
	
	$scope.updateLockingRecorderState = function(id,state){
		$("#effectiveing").modal("show");
		LockingRecordService.updateLockingRecorderState(id,state,suc,ero);
		function suc(data){
			$("#effectiveing").modal("hide");
			$scope.init();
		}
		function ero(error){
			$("#errorhapen").modal("show");
		}
	}
	
	$scope.displayLockingRecord = function(id){
		$state.go("main.lockingRecordDisplay",{"id":id});
	}
	
	$scope.deleteLockingRecord = function(id){
		$("#effectiveing").modal("show");
		LockingRecordService.deleteLockingRecord(id,suc,ero);
		function suc(data){
			$("#effectiveing").modal("hide");
			$scope.init();
		}
		function ero(error){
			$("#errorhapen").modal("show");
		}
	}
	
	
	/**
	 * 下一页
	 */
	$scope.nextPage = function () { 
		var computeAtr1 = $scope.currentPage;
		var couputeResult = computeAtr1 ++;
		if(couputeResult<$scope.totalPage){
			$("#loading").modal("show");
			//禁用按钮
			LockingRecordService.getSearchPageList(++$scope.currentPage,$scope.pageSize,$scope.searchCondition,$rootScope.usr.id,suc,ero);
			function suc(data){
				$scope.lockingRecordList = data.pageList;
				$scope.totalItems = data.totalItems;  
				$scope.totalPage = Math.ceil($scope.totalItems/$scope.pageSize);
				$("#loading").modal("hide");
			};
			function ero(error){
				$("#errorhapen").modal("show");
			}
		}
		else{
			alert("最后一页");
		}
	}  

	$scope.lastPage = function () { 
		var computeAtr1 = $scope.currentPage;
		var couputeResult = computeAtr1 --;
		if(couputeResult>1){
			$("#loading").modal("show");
			//禁用按钮
			LockingRecordService.getSearchPageList(--$scope.currentPage,$scope.pageSize,$scope.searchCondition,$rootScope.usr.id,suc,ero);
			function suc(data){
				$scope.lockingRecordList = data.pageList;
				$scope.totalItems = data.totalItems;  
				$scope.totalPage = Math.ceil($scope.totalItems/$scope.pageSize);
				$("#loading").modal("hide");
			};
			function ero(error){
				$("#errorhapen").modal("show");
			}
		}
		else{
			alert("第一页");
		}
	} 
	
	
	/**
	 * 首页
	 */
	$scope.firstPage = function () { 
		$("#loading").modal("show");
		$scope.currentPage=1;
		LockingRecordService.getSearchPageList($scope.currentPage,$scope.pageSize,$scope.searchCondition,$rootScope.usr.id,suc,ero);
		function suc(data){
			$scope.lockingRecordList = data.pageList;
			$scope.totalItems = data.totalItems;  
			$scope.totalPage = Math.ceil($scope.totalItems/$scope.pageSize);
			$("#loading").modal("hide");
		};
		function ero(error){
			$("#errorhapen").modal("show");
		}
	} 
	
	$scope.endPage = function () {
		$("#loading").modal("show");
		$scope.currentPage=1;
		$scope.currentPage=$scope.totalPage;
		LockingRecordService.getSearchPageList($scope.currentPage,$scope.pageSize,$scope.searchCondition,$rootScope.usr.id,suc,ero);
		function suc(data){
			$scope.lockingRecordList = data.pageList;
			$scope.totalItems = data.totalItems;  
			$scope.totalPage = Math.ceil($scope.totalItems/$scope.pageSize);
			$("#loading").modal("hide");
		};
		function ero(error){
			$("#errorhapen").modal("show");
		}
	} 
	

	$scope.initalPageList = function () {  
		$("#loading").modal("show");
		$scope.currentPage = 1;
		LockingRecordService.getSearchPageList($scope.currentPage,$scope.pageSize,$scope.searchCondition,$rootScope.usr.id,suc,ero);
		function suc(data){
			$scope.lockingRecordList = data.pageList;
			$scope.totalItems = data.totalItems;  
			$scope.totalPage = Math.ceil($scope.totalItems/$scope.pageSize);
			$("#loading").modal("hide");
		};
		function ero(error){
			$("#errorhapen").modal("show");
		}
	} 
}]);
