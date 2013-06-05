/***********************
 * Dahu App script
 ***********************/
jQuery(function($) {

    /*
     * In every button callback, this boolean must be checked
     * to see if the action has to be performed or not.
     * @type Boolean
     */
    var captureMode = false;
    
    /************************
     * Notifiers for keyboard drivers
     ************************/
    
    var captureNotify = {
        "notify" : function(type) {
            dahuapp.drivers.dummy.onLoad();
            // tripe egal pour comparer des string en javascript
            if (type === "capture") {
                dahuapp.drivers.fileSystem.writeImage(
                        dahuapp.drivers.screen.takeScreen(),
                        ".");
            } else if (type === "escape") {
                captureMode = false;
                dahuapp.drivers.keyboard.removeKeyListener();
            }
        }
    };

    /************************
     * Set actions to buttons
     ************************/
    
    $('#capture-mode').click(function() {
        if (!captureMode) {
            captureMode = true;
            dahuapp.drivers.keyboard.addKeyListener(captureNotify);
        } else {
            captureMode = false;
        }
     });
});