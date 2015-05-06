angular.module("BookShare", ['ui.router'])
	
	.controller("appHome", ["$rootScope", "$scope", "$location", "student", "mapper", "$stateParams", appDashboard])

	.controller("loginController", ["$rootScope", "$scope", "$location","student", LoginStudent])
	
	.controller("signupController", ["$scope", SignupStudent])
	
	.controller("dataController", ["$rootScope", "$scope", apiCall])
	
	.factory('student', ['$state', '$http', studentFactory])
	.factory('mapper', ['$state', '$http', mapperFactory])
	
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
	})
	.state('home.profile', {
		url: '/home/profile',
		templateUrl: 'templates/partials/profile.html',
		controller: 'dataController'
	})
	.state('home.addBook', {
		url: '/home/addBook',
		templateUrl: 'templates/partials/addBook.html',
		controller: 'dataController'
	})
	.state('home.listBook', {
		url: '/home/listBook',
		templateUrl: 'templates/partials/listBooks.html',
		controller: 'dataController'
	})
	.state('home.history', {
		url: '/home/history',
		templateUrl: 'templates/partials/history.html',
		controller: 'dataController'
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

//mapper factory callback method
function mapperFactory($state, $http){
	var mapperObj = {
			mapper : []
	};
	
	function LoadProfile(){
		$state.go('home.profile');
	}
	
	function ListBook(student){
		return $http.get('/listallbooks', student)
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
	
	function AddBook(){
		$state.go('home.profile');
	}
	
	function History(){
		$state.go('home.profile');
	}
	
	return {
		LoadProfile : LoadProfile,
		History : History,
		ListBook : ListBook,
		AddBook : AddBook
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
function appDashboard($rootScope, $scope, $location, student, mapper, $stateParams){
	
	var mapperService = mapper;
	if(!$rootScope.isAuthenticated){
		$location.path("#/login");
	}
	
	console.log("Stateparams received " + (student.userObj.firstName));
	$scope.student = student.userObj.firstName;
	
	$scope.logout = function(){
		$rootScope.isAuthenticated = false;
		$location.path("#/login");
	}
	
	$scope.profile = function(){
		mapperService.LoadProfile();
	}
	
	$scope.history = function(){
		mapperService.History();
	}
	
	$scope.addBook = function(){
		mapperService.AddBook();
	}
	
	$scope.myBooks = function(){
		mapperService.ListBook();
	}
}

function apiCall($rootScope, $scope){
	
}