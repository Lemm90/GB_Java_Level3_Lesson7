angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        console.log($scope.number)
                $http({
                    url: contextPath + '/products',
                    method: 'GET',
                    params: {
                        numberMin: $scope.number ? $scope.number.min : null,
                        numberMax: $scope.number ? $scope.number.max : null
                    }
                }).then(function (response) {
                console.log(response.data)
                    $scope.ProductsList = response.data;
                });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changePrice = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_price',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadProducts();
});