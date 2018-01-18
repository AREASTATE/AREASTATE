angular.module("landStateListModule",["ui.bootstrap"]).
controller("landStateListController",["$scope","$state","$rootScope","$uibModal","LandStateService","LandService","LockingRecordService",function($scope,$state,$rootScope,$uibModal,LandStateService,LandService,LockingRecordService){

	$scope.init = function(){
		$("#loading").modal("show");
		LandService.findAllLands(suc1,ero1);
		function suc1(data){
			$scope.landList = data;
			$scope.landAvailable = false;
			for(var i = 0; i < $scope.landList.length; i ++){
				if($scope.landList[i].landState == "启用"){
					$scope.landAvailable = true;
					break;
				}
			}

			$scope.currentLand = $scope.landList[0];
			LandStateService.getLandDailyState($scope.landList[0].id,suc2,ero2);
			function suc2(landDailyStates){
				$scope.landDailyStates = landDailyStates;
				$("#loading").modal("hide");
			}
			function ero2(error){
				$("#errorhapen").modal("show");
			}
		}
		function ero1(error){
			$("#errorhapen").modal("show");
		}
	}

	$scope.getLandDailyState = function(land,self){
		var targetEle = $(self.target);
		targetEle.attr("class", "landAClick"); 
		var siblingsEle = targetEle.siblings("a");
		for(var i = 0; i < siblingsEle.length; i ++){
			var ele = $(siblingsEle[i]);
			ele.attr("class","landAinit");
		}
		$("#loading").modal("show");
		$scope.currentLand = land;
		LandStateService.getLandDailyState(land.id,suc,ero);
		function suc(data){
			$scope.landDailyStates = data;
			$("#loading").modal("hide");
		}
		function ero(error){
			$("#errorhapen").modal("show");
		}
	}

	//打开dialog的函数
	$scope.newapplycation= function(land,date,dateStage){
		$scope.lockingRecord = {};
		$scope.lockingRecord.land = land;
		$scope.lockingRecord.timeQuantum = dateStage;
		$scope.lockingRecord.lockDate = date;
		$uibModal.open({
			size:"md",
			animation:true,  
			templateUrl:"newapplycation.html",//dialog的id，与html建立的template的id一致
			controller:"applylocklandcontroller",//指定dialog的controller
			resolve: {    
				lockingRecord:function(){  
					return angular.copy($scope.lockingRecord);
				}  
			} ,
		});
	};
}])
.controller("applylocklandcontroller",function($scope,$state,$rootScope,$modalInstance,lockingRecord,LockingRecordService){
	$scope.lockingRecord = lockingRecord;
	//定义dialog中的取消按钮的点击事件的处理函数
	$scope.cancel = function(){
		$modalInstance.dismiss('cancel');
	};

	$scope.saveLockingRecord = function(){
		$modalInstance.dismiss('cancel');
		$("#effectiveing").modal("show");
		LockingRecordService.saveLockingRecord($scope.lockingRecord,suc,ero);
		function suc(data){
			$("#effectiveing").modal("hide");
			$state.go("main.lockingRecordList");
		}

		function ero(error){
			$("#errorhapen").modal("show");
		}
	}
});
