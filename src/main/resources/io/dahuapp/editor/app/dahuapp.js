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