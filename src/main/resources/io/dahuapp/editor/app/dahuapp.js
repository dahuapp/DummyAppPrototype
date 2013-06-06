(function(window, $){
    var dahuapp  = (function () {
        var self = {};
        
        /* private API */
        var _privateAttribute = ':o';
    
        function _somePrivateFunction(args) {
            return "private hello "+args;
        };
        
        /* public API */

        self.version = "0.0.1";
    
        self.somePublicFunction = function somePublicFunction(args) {
            return "public hello "+args;
        }; 

        return self;
    }());

    window.dahuapp = dahuapp;
})(window, JQuery);