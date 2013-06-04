/***********************
 * Dahu App script
 ***********************/
(function($) {
    
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
            dahuapp.keyboard().listenKeyboard();
            captureMode = true;
        }
    });
});