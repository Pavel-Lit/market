angular.module('market',['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    if ($localStorage.marchMarketUser) {
        try {
            let jwt = $localStorage.marchMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.marchMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        if ($localStorage.marchMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marchMarketUser.token;
        }
    }

    $scope.fillTable = function () {
        $http.get('http://localhost:5555/core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                // console.log(response);
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:5555/core/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }


    $scope.login = function (){
        console.log($scope.user)
        $http.post('http://localhost:5555/auth/authenticate', $scope.user)
            .then(function (response){
                if(response.data.token){
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marchMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }

            }, function  errorCallback(response){
            });
    };

    $scope.about = function (){
        $http.get('http://localhost:5555/auth/about')
            .then(function (response){
                console.log(response)
            alert(response.data.name + " "+ response.data.email)
            //     alert(JSON.stringify(response.data);
        });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
    };

    $scope.clearUser = function () {
        delete $localStorage.marchMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.marchMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.addProductToCart = function (id){
        $http.get('http://localhost:5555/cart/api/v1/cart/add/' + id)
            .then(function (response){
                $scope.fillCart();
                });
    }
    $scope.fillCart = function (){
        $http.get('http://localhost:5555/cart/api/v1/cart')
            .then(function (response){
                $scope.cartList = response.data.items;
                console.log(response);
            })
    }

    $scope.totalPrice = function (){
        $http.get('http://localhost:5555/cart/api/v1/cart')
            .then(function (response){
                $scope.total = response.data.totalPrice;
            })
    }
    $scope.clearCart = function (){
        $http.get('http://localhost:5555/cart/api/v1/cart/clear')
            .then(function (response){
                $scope.fillCart();
                console.log(response);
            })
    }
    $scope.deleteOneProductFromCart = function (productId){
        $http.get('http://localhost:5555/cart/api/v1/cart/remove/' + productId)
            .then(function (response){
            $scope.fillCart();
            console.log(response);
        })
    }

    $scope.createNewOrder = function (){
        $http.post('http://localhost:5555/core/api/v1/orders')
            .then(function (response){

                console.log(response);
                alert("Заках успешно сформирован");
            })
    }

    $scope.fillCart();
    $scope.fillTable();
});