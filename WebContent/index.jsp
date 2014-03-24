<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="tinySearch">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="bower_components/angular/angular.js"></script>
<script type="text/javascript" src="bower_components/angular-resource/angular-resource.js"></script>
<script type="text/javascript" src="js/tinysearch-controllers.js"></script>
<script type="text/javascript" src="js/tinysearch-services.js"></script>
<link rel="stylesheet" href="bower_components/angular/angular-csp.css" type="text/css">
</head>
<body>
<div ng-controller="searchResult">
<form action="servlet/search"><br>
<label for="query">Search</label><input id="query" name="query" ng-model="query" size=30 >
<label for="depth">Depth</label><input id="depth" name="depth" ng-model="depth" size=3 >
<input type="button" onclick="void(0);" ng-click="doSearch();" value="Search">
</form>
<table>
	<tr ng-repeat="page in pages">
		<td><a href="{{page.url}}">{{page.title}}</a></td>
		<td>{{page.content}}</td>
	</tr>
</table>
</div>

</body>
</html>