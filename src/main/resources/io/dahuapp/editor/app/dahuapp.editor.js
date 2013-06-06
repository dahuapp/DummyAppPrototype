/*
 * Engine declaration (creates the engine if 'dahuapp.engine' doesn't
 * exists, if it does, it simply does nothing).
 */

log("start editor");

var dahuapp = (function(dahuapp, $) { 
    var editor = (function () {
        var self = {};
        
        /* private API */
        
        /*
         * Tells if the app is in the capture mode (in theory, no actions are
         * available in this mode, other than clicking on the capture mode
         * button, or pressing the capture or escape keyboard keys).
         * @type Boolean
         */
        var captureMode = false;

        /*
         * Event handlers
         ****************
         * For each handler, put a function name (the same as the key for example).
         * Anonymous functions can't be called from the driver,
         * because Java7 doesn't handle JSObjects well, so it only records the name.
         */

        /*
         * Handle events relative to the capture mode.
         * Other events will be ignored.
         * @param String type Type of the event.
         */
        function handleCaptureModeEvent(type) {
            $('body').append('bonjour');
            switch (type.toLowerCase()) {
                case "capture":
                    //dahuapp.drivers.screen.takeScreen();
                    break;
                case "escape":
                    //dahuapp.engine.exitCaptureMode();
                    break;
            }
        };

        function dummy() {
            $('body').append('bonjour');
        };

        /* public API */
        
        /*
         * Main function : by calling this function, we bind the
         * html components of the application with their behaviour.
         * So this function must be called once when the html is loaded
         * in the application window.
         ******************
         * This function has to be defined after the engine definition,
         * because it uses the properties and methods of this so it has
         * to be defined before using it.
         */
        self.init = function init() {
            /*
             * We must remember who 'this' is, because in a jQuery statement
             * the 'this' refers to the jQuery returns and not to the
             * englobant object.
             */
            var self = this;

            $('#capture-mode').click(function() {
                // shortcut
                var driver = dahuapp.drivers.keyboard;
                // if we're in capture mode, we exit it, otherwise we enter it
                if (!self.captureMode) {
                    driver.addKeyCallback(window.dahuapp.editor.handleCaptureModeEvent);
                } else {
                    driver.removeKeyCallback(window.dahuapp.editor.handleCaptureModeEvent);
                }
                // the capture mode button gets a different style
                $('#capture-mode').toggleClass('btn-primary', 'btn-success');
                self.captureMode = !self.captureMode;
            });
        };
        
        self.somePublicFunction = function somePublicFunction(args) {
            return "public (editor) hello "+args;
        }; 
        
        return self;
    })();
    
    dahuapp.editor = editor;

    return dahuapp;
})(dahuapp || {}, jQuery);

log("stop editor");