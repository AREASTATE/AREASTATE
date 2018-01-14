angular.module("personnalModule",[])
.controller("personnalCenterController",["$scope","$state","RegisterAndLoginService",function($scope,$state,RegisterAndLoginService){

	$scope.init = function(){
		var user=JSON.parse(localStorage.getItem("currentUser"));
		RegisterAndLoginService.findUserById(user.id,suc,ero);
		function suc(data){
			$scope.user = data;
		}
		function ero(error){
			console.log(error);
		}
	}
	$scope.modifyInformation=function(){
		$state.go("main.personnalCenterEditForm");
	}
	$scope.modifyPassword=function(){
		
		$state.go("main.personnalCenterModifyPass");
	}
	
}]);