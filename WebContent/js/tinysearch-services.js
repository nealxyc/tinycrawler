angular.module("tinySearch").factory('search', ['$resource', function($resource){
	return (function(q, depth){
		q = q || "" ;
		depth = depth || "2";//default search depth
		 return $resource('ajax/nealxyc.tinycrawler.web.SearchData/search',  {
		      query: {method:'GET', params:{q:q, depth:depth}, isArray:true},
		    });
	});
}]);