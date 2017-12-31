angular.module("landStateListModule",[]).
controller("landStateListController",["$scope","$state","LandStateService",function($scope,$state,LandStateService){
	
	$scope.init = function(){
		LandStateService.getLandDailyState("场地一",suc,ero);
		function suc(data){
			$scope.landDailyStates = data;
			console.log(data);
		}
		function ero(error){
			alert(error);
		}
	}
}]);
