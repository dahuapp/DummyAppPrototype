/*
 * MOVE THE NEXT LINE IN 'dahuapp.js'
 */
var dahuapp = dahuapp || {};

/*
 * Engine declaration (creates the engine if 'dahuapp.engine' doesn't
 * exists, if it does, it simply does nothing).
 */
dahuapp.engine = dahuapp.engine || {
    
    /*
     * Tells if the app is in the capture mode (in theory, no actions are
     * available in this mode, other than clicking on the capture mode
     * button, or pressing the capture or escape keyboard keys).
     * @type Boolean
     */
    captureMode: false,
            
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
    handleCaptureModeEvent: function handleCaptureModeEvent(type) {
        $('body').append('bonjour');
        switch (type.toLowerCase()) {
            case "capture":
                //dahuapp.drivers.screen.takeScreen();
                break;
            case "escape":
                //dahuapp.engine.exitCaptureMode();
                break;
        }
    },
    
    dummy: function dummy() {
        $('body').append('bonjour');
    }
};

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
dahuapp.engine.apply = function() {
    
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
            driver.addKeyCallback(self.handleCaptureModeEvent);
        } else {
            driver.removeKeyCallback(self.handleCaptureModeEvent);
        }
        // the capture mode button gets a different style
        $('#capture-mode').toggleClass('btn-primary', 'btn-success');
        self.captureMode = !self.captureMode;
    });
};