var dahuapp = dahuapp || new (function($) {
    
    /* private API */
    var _privateAttribute = ':o';
    
    function _somePrivateFunction(args) {
        return "private hello "+args;
    };
    
    /* public API */
    this.version = "0.0.1";
    
    this.somePublicFunction = function somePublicFunction(args) {
        return "public hello "+args;
    };
    
})(Jquery);