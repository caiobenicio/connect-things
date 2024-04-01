app = angular.module('homeon', []);

app.directive("click", function () {
    return function(scope, element, attrs) {
        element.bind("click", function() {
            scope.boolChangeClass = !scope.boolChangeClass;
            scope.$apply();
        });
    };
});
