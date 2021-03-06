angular.module("BookShare", ['ui.router'])
	.controller("appHome", ["$rootScope", "$scope", "$location", "student", "mapper", "$stateParams", appDashboard])
	.controller("loginController", ["$rootScope", "$scope", "$location","student", LoginStudent])
	.controller("signupController", ["$scope", SignupStudent])
	.controller("errorController", ["$rootScope", "$scope", "student", handleError])
	
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
	.state('error',{
		url: '/login',
		templateUrl: '/templates/login.html',
		controller: 'errorController'
	});
	.state('home', {
		url : '/home',
		templateUrl : '/templates/dashboard.html',
		controller : 'appHome'
	})
	.state('home.profile', {
		url: '/home/profile',
		templateUrl: 'templates/partials/profile.html',
		controller: 'appHome'
	})
	.state('home.addBook', {
		url: '/home/addBook',
		templateUrl: 'templates/partials/addBook.html',
		controller: 'appHome'
	})
	.state('home.listBook', {
		url: '/home/listBook',
		templateUrl: 'templates/partials/listBooks.html',
		controller: 'appHome'
	})
	.state('home.history', {
		url: '/home/history',
		templateUrl: 'templates/partials/history.html',
		controller: 'appHome'
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
		return $http.post('/login', student)
		.success(function(response){
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

function handleError($rootScope, $scope, student){
	
	console.log("Errorcontroller " + student.userObj.error);
	if(student.userObj.error == "LoginError")
	{
		$scope.errorMsg = "Login failed. Invalid Email/password combination.";
		$rootScope.authenticated = false;
	}
}


//mapper factory callback method
function mapperFactory($state, $http){
	var mapperObj = {
		mapper : []
	};
	
	function LoadProfile(){
		$state.go('home.profile');
	}
	
	function ListBook(studentEmail){
		return $http.get('/listUserbooks/' + studentEmail)
		.success(function(response){
			console.log("Logged User Books : " + JSON.stringify(response));
			//angular.copy(response[0], mapperObj);
			if(mapperObj.mapper != null || mapperObj.mapper != undefined){
				mapperObj.mapper = [];
			}
			for(var i=0; i<response.length; i++){
				mapperObj.mapper.push(response[i]);
			}
			
			$state.go('home.listBook');
		})
		.error(function(response, status){
			console.log("Login POST error: " + response + " status " + status);
		});
	}
	
	function SearchBook(keyval){
		return $http.get('/searchbook/' + keyval)
		.success(function(response){
			console.log("Logged user search : " + JSON.stringify(response));
			if(mapperObj.mapper != null || mapperObj.mapper != undefined){
				mapperObj.mapper = [];
			}
			for(var i=0; i<response.length; i++){
				mapperObj.mapper.push(response[i]);
			}
			console.log("User logged in " + JSON.stringify(response.firstName));
			$state.go('home.listBook');
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
		mapperObj : mapperObj,
		LoadProfile : LoadProfile,
		History : History,
		ListBook : ListBook,
		AddBook : AddBook,
		SearchBook : SearchBook
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
	
	$scope.student = student.userObj.firstName;
	$scope.booksList = mapper.mapperObj.mapper;
		
	console.log("Book scope :" + JSON.stringify(mapper.mapperObj) +  " " + JSON.stringify($scope.booksList.mapper));
	
	$scope.logout = function(){
		$rootScope.isAuthenticated = false;
		student.userObj = null;
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
		console.log("My Books : " + student.userObj.email);
		mapperService.ListBook(student.userObj.email);
	}
	
	$scope.searchBook = function(){
		console.log("Search Books : " + student.userObj.email);
		mapperService.SearchBook($scope.keyval);
		$scope.keyval = "";
	}
}
