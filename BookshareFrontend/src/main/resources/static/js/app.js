angular.module('app', ['ngRoute'])
	.config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider){
		$routeProvider.when('/', {
			templateUrl: 'home.html',
			controller: 'home'
		}).when('/login', {
			templateUrl : 'login.html',
			controller: 'navigation'
		}).when('/register', {
			templateUrl : 'register.html',
			controller: 'navigation'		
		}).otherwise('/');
	
		//prevent default spring security pop-up
	    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

	}])

	.controller('home', ['$scope', '$http', function($scope, $http){
		console.log("Loading home controller....");
		
		//retrieve security token from session and send it to back-end server
		$http.get('token').success(function(token){
			console.log(token.token);
			$http({
				url : 'resource',
				method : 'GET',
				headers : {
					'x-auth-token' : token.token
				}
			}).success(function(data){
		    	console.log("REtrieved resource " + JSON.stringify(data));
		    	$scope.greeting = data;
			})
		})
	}])

	.controller('navigation', ['$rootScope','$scope', '$http', '$location', function($rootScope, $scope, $http, $location){
		var authenticate = function(credentials, callback){
			var headers = credentials ? {authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)}:{};
			
		    $http.get('user', {headers : headers}).success(function(data) {
				if (data.name) {
					$rootScope.authenticated = true;
				} else {
					$rootScope.authenticated = false;
				}
				//if callback function exists then call it
				callback && callback();
	    	}).error(function() {
		        $rootScope.authenticated = false;
		        callback && callback();
	    	});
		}
		//check if user is authenticated when page is loaded (navigation controller is called when page is loaded first)
		//authenticate();
		//object to hold uuser credentials
		$scope.credentials = {};
	
		//try to authenticate user when login is clicked
		$scope.login = function(){
			authenticate($scope.credentials, function(){
				if($rootScope.authenticated){
					$location.path("/");
					$scope.error = false;
				}	
				else{
					$location.path("/login");
					$scope.error = true;
				}
			});
		};
		
		//logout user
		$scope.logout = function() {
			$http.post('logoutMeOut', {}).success(function() {
				$rootScope.authenticated = false;
				$location.path("/");
			}).error(function(data) {
			    $rootScope.authenticated = false;
			});
		}
	}]);