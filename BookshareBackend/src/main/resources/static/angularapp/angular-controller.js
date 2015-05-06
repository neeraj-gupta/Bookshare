angular.module("BookShare", ['ui.router'])
	.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
		$stateProvider
            .state('login', {
				url : '/login',
				templateUrl : '/templates/login.html',
				controller : 'studentLogin'
			})
			.state('register', {
				url : '/register',
				templateUrl : '/templates/register.html',
				controller : 'studentRegister'
			})
			.state('home', {
				url : '/home',
				templateUrl : '/templates/dashboard.html',
				controller : 'userHome'
			});
		$urlRouterProvider.otherwise('/login');
	}])
	.factory('student', ['$state', function($state){
		var userObj = {
				user : []
		};
		
		userObj.saveUser = function(userData){
			userObj.user.push(userData);
			$state.go('home');
		};
		
		return userObj;
	}])
	.controller("studentRegister", ["$scope", "student", function($scope, student){
		$scope.submitUser = function(){
			// Submit form and add values to DB.
			
			var userObj = {
				name : $scope.name,
				email : $scope.email,
				phone : $scope.phone,
				university : $scope.university
			};
			
			user.saveUser(userObj);
		};
		
		$scope.reset = function(){
			// Clear all the fields of the form.
			$scope.name = "";
			$scope.email = "";
			$scope.phone = "";
			$scope.university = "";
		};
	}])