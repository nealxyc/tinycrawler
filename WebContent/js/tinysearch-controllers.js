/**
 * 
 */
angular.module('tinySearch', ["ngResource"]);

angular.module("tinySearch").controller("searchResult", ["$scope", "search", function($scope,search){
	$scope.pages = [];
	$scope.doSearch = function(){
		$scope.pages = search($scope.query, $scope.depth);
	};
}]);
