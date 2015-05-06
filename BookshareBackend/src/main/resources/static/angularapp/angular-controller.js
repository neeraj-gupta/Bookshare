angular.module("BookShare", ['ui.router'])
	
	.controller("appHome", ["$rootScope", "$scope", "$location", "student", "$stateParams", appDashboard])

	.controller("loginController", ["$rootScope", "$scope", "$location","student", LoginStudent])
	
	.controller("signupController", ["$scope", SignupStudent])
	
	.factory('student', ['$state', '$http', studentFactory])
	
	.config(['$stateProvider', '$urlRouterProvider', appConfigHandler]);
	
	


function appConfigHandler($stateProvider, $urlRouterProvider){
	$stateProvider
    .state('login', {
		url : '/login',
		templateUrl : '/templates/login.html',
		controller : 'loginController'
	})
	.state('register', {
		url : '/register',
		templateUrl : '/templates/register.html',
		controller : 'signupController'
	})
	.state('home', {
		url : '/home',
		templateUrl : '/templates/dashboard.html',
		controller : 'appHome'
	});
	
	$urlRouterProvider.otherwise('/login');
}

//student factory callback method
function studentFactory($state, $http){
	var userObj = {
			user : []
	};
	
	function LoadSignup(){
		$state.go('register');
	}

	function Login(student){
	//userObj.Login = function(student){	
		return $http.post('/login', student)
		.success(function(response){
			
			console.log("Logging user in " + JSON.stringify(student));
			angular.copy(response, userObj);
			console.log("User logged in " + JSON.stringify(response.firstName));
			$state.go('home');
		})
		.error(function(response, status){
            
			console.log("Login POST error: " + response + " status " + status);

		});
	}
	
	return {
		userObj : userObj,
		Login : Login,
		LoadSignup : LoadSignup
	};
}

//register callback method
function SignupStudent($scope){
	
}


//loginController callback method
function LoginStudent($rootScope, $scope, $location, studentservice){
	
	var studentService = studentservice;
	//student login function
	 $scope.LoginStudent = function(){
		 studentService.Login($scope.student)
		 console.log("login controller " + studentService.userObj);
		 $rootScope.isAuthenticated = true;
	 }
	 
	 $scope.loadSignup = function(){
		 console.log("Signup controller");
		 studentService.LoadSignup()
	 }
}

//appHome
function appDashboard($rootScope, $scope, $location, student, $stateParams){
	
	if(!$rootScope.isAuthenticated){
		$location.path("#/login");
	}
	
	console.log("Stateparams received " + (student.userObj.firstName));
	$scope.student = student.userObj.firstName;
	
	$scope.logout = function(){
		$rootScope.isAuthenticated = false;
		$location.path("#/login");
	}
}
