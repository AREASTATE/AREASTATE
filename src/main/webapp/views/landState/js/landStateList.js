angular.module("landStateListModule",[]).
controller("landStateListController",["$scope","$state","LandStateService","LandService",function($scope,$state,LandStateService,LandService){
	
	$scope.init = function(){
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
			
			$scope.currentLandName = $scope.landList[0].landName;
			LandStateService.getLandDailyState($scope.landList[0].id,suc2,ero2);
			function suc2(landDailyStates){
				$scope.landDailyStates = landDailyStates;
			}
			function ero2(error){
				alert(error);
			}
		}
		function ero1(error){
			alert(error);
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
		
		$scope.currentLandName = land.landName;
		LandStateService.getLandDailyState(land.id,suc,ero);
		function suc(data){
			$scope.landDailyStates = data;
		}
		function ero(error){
			alert(error);
		}
	}
}]);
