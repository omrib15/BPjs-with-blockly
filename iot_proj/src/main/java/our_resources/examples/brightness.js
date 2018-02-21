var Count, BATTERY_LIFE, MIN_BRIGHTNESS, USER_BRIGHTNESS, user_request, e, x, event_name, text, camera_brightness, BRIGHTNESS, inc, MAX_BRIGHTNESS;

/**
 * Describe this function...
 */
function getBrightness(event_name) {
  return event_name.slice(event_name.length() - 2, event_name.length());
}

/**
 * Describe this function...
 */
function update_brightness(inc) {
  if (inc < 0) {
    BRIGHTNESS = Math.max.apply(null, [0, BRIGHTNESS + inc]);
  } else {
    BRIGHTNESS = Math.min.apply(null, [100, BRIGHTNESS + inc]);
  }
}


BATTERY_LIFE = 100;
USER_BRIGHTNESS = false;
BRIGHTNESS = 50;
MAX_BRIGHTNESS = 100;
MIN_BRIGHTNESS = 0;

bp.registerBThread('User-setting-off', function(){
  while (true) {
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'user brightness off');
    bsync({waitFor: bp.Event('user brightness off')});
    USER_BRIGHTNESS = false;
  }

});

bp.registerBThread('User-setting-update', function(){
  while (true) {
    e = bsync({waitFor: (bp.EventSet("es2873", function(e) {
      return ((e.getName()).startsWith('User:'));
    }))});
    USER_BRIGHTNESS = true;
    user_request = getBrightness((e.getName()));
    BRIGHTNESS = user_request;
    bsync({request: bp.Event(('Brightness updated to: '+user_request))});
  }

});

bp.registerBThread('Camera-brightness-listener', function(){
  while (true) {
    e = bsync({waitFor: (bp.EventSet("es2874", function(e) {
      return ((e.getName()).startsWith('Camera:'));
    }))});
    camera_brightness = getBrightness((e.getName()));
    update_brightness((BRIGHTNESS - camera_brightness) / 2);
    bsync({request: bp.Event(('Brightness updated to: '+BRIGHTNESS))});
  }

});

bp.registerBThread('battery-inc-listener', function(){
  while (true) {
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'Battery: +1');
    bsync({waitFor: bp.Event('Battery: +1')});
    update_brightness(1);
    bsync({request: bp.Event(('Brightness updated to: '+BRIGHTNESS))});
  }

});

bp.registerBThread('battery-dec-listener', function(){
  while (true) {
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'Battery: -1');
    bsync({waitFor: bp.Event('Battery: -1')});
    update_brightness(-1);
    bsync({request: bp.Event(('Brightness updated to: '+BRIGHTNESS))});
  }

});

bp.registerBThread('blocker', function(){
  while (true) {
    if (USER_BRIGHTNESS) {
      bsync({block: (bp.EventSet("es2875", function(e) {
        return (((e.getName()).startsWith('Camera:')) || ((e.getName()).startsWith('Battery: ')));
      }))});
    } else {
      //Auto-generated code for dynamic event detection:
      bp.log.info("EVENT_DETECTED: "+('Brightness updated to: '+user_request));
      bsync({waitFor: bp.Event(('Brightness updated to: '+user_request))});
    }
  }

});
