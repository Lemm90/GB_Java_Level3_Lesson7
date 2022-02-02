angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function () {
        console.log($scope.filter)
                $http({
                    url: contextPath + '/products',
                    method: 'GET',
                    params: {
                        min_price: $scope.filter ? $scope.filter.min_price: null,
                        max_price: $scope.filter ? $scope.filter.max_price : null,
                        title: $scope.filter ? $scope.filter.title : null,
                    }
                }).then(function (response) {
                    $scope.ProductsList = response.data.content;
                });
    };

        $scope.addProduct = function () {
            console.log($scope.newProducts)
                    $http.post(contextPath + '/products', $scope.newProducts)
                    .then(function (response) {
                    alert ('Продукт добавлен')
                        $scope.loadProducts;
                    });
        };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
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