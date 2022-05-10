angular.module('market').controller('storeController', function ($scope, $http, $localStorage) {
    $scope.fillTable = function () {
        $http.get('http://localhost:5555/core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                // console.log(response);
            });
    };
    $scope.addProductToCart = function (id){
        $http.get('http://localhost:5555/cart/api/v1/cart/'+$localStorage.marchMarketGuestCartId+'/add/' + id)
            .then(function (response){

            });
    }
    $scope.fillTable();
});