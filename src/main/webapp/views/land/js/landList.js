angular.module("landListModule",[]).
controller("landListController",["$scope","$state","LandService",function($scope,$state,LandService){
	
	$scope.init = function(){
		$("#loading").modal("show");
		LandService.findAllLands(suc,ero);
		function suc(data){
			$scope.landList = data;
			$("#loading").modal("hide");
		}
		function ero(error){
			$("#errorhapen").modal("show");
		}
	}
	
	$scope.editLand = function(id){
		$state.go("main.landEditForm",{"id":id,"operate":"edit"});
	}
	
	$scope.displayLand = function(id){
		$state.go("main.landDisplay",{"id":id});
	}
	
	$scope.deleteLand = function(id){
		$("#effectiveing").modal("show");
		LandService.deleteLand(id,suc,ero);
		function suc(data){
			$("#effectiveing").modal("hide");
			$scope.init();
		}
		function ero(error){
			$("#errorhapen").modal("show");
		}
	}
}]);
