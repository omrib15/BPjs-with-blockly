var Count, MAX_TEMP, MIN_TEMP, text, x, event_name;

/**
 * Is this event a temperature event?
 */
function is_temp_event(event_name) {
  return event_name.startsWith('Temp: ');
}

/**
 * Get the temperature stated in the event's name
 */
function getTemp(event_name) {
  return event_name.slice(event_name.length - 2, event_name.length);
}

/**
 * Does this event indicate a temperature hotter than the max temperatu
 */
function tooHot(event_name) {
  if (!is_temp_event(event_name)) {
    return false;
  }
  return getTemp(event_name) > MAX_TEMP;
}

/**
 * Does this event indicate a temperature colder than the min temperature
 */
function tooCold(event_name) {
  if (!is_temp_event(event_name)) {
    return false;
  }
  return getTemp(event_name) < MIN_TEMP;
}


40;

MAX_TEMP = '40';
MIN_TEMP = '20';

20;

bp.registerBThread('AC-cold', function(){
  while (true) {
    bsync({waitFor: (bp.EventSet("es466", function(e) {
      return (tooHot((e.getName())));
    }))});
    bsync({request: bp.Event('AC: set to cold'),
    block: bp.Event('AC: set to heat')});
  }

});

bp.registerBThread('Lights-auto-turnoff', function(){
  while (true) {
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'electricity_critical');
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'tennant_left');
    bsync({waitFor: [bp.Event('tennant_left'), bp.Event('electricity_critical')]});
    bsync({request: bp.Event('Lights: turnoff')});
  }

});

bp.registerBThread('Thermostat-update-display', function(){
  while (true) {
    bsync({waitFor: (bp.EventSet("es467", function(e) {
      return (is_temp_event((e.getName())));
    }))});
    bsync({request: bp.Event('Thermostat: update display')});
  }

});

bp.registerBThread('AC-heat', function(){
  while (true) {
    bsync({waitFor: (bp.EventSet("es468", function(e) {
      return (tooCold((e.getName())));
    }))});
	bp.log.info("AC-Cooler wakes up");
    bsync({request: bp.Event('AC: set to heat'),
    block: bp.Event('AC: set to cold')});
  }

});

bp.registerBThread('Lights-auto-turnon', function(){
  while (true) {
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'tennant_arrived');
    bsync({waitFor: bp.Event('tennant_arrived')});
    bsync({request: bp.Event('Lights: turnon')});
  }

});

bp.registerBThread('Thermostat-auto-turnoff', function(){
  while (true) {
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'tennant_left');
    bsync({waitFor: bp.Event('tennant_left')});
    bsync({request: bp.Event('Thermostat: turnoff')});
  }

});

bp.registerBThread('Thermostat-auto-turnon', function(){
  while (true) {
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'tennant_arrived');
    bsync({waitFor: bp.Event('tennant_arrived')});
    bsync({request: bp.Event('Thermostat: turnon')});
  }

});

bp.registerBThread('AC-auto-turnoff', function(){
  while (true) {
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'electricity_critical');
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'tennant_left');
    bsync({waitFor: [bp.Event('tennant_left'), bp.Event('electricity_critical')]});
    bsync({request: bp.Event('AC: turnoff')});
  }

});

bp.registerBThread('AC-auto-turnon', function(){
  while (true) {
    //Auto-generated code for dynamic event detection:
    bp.log.info("EVENT_DETECTED: "+'tennant_arrived');
    bsync({waitFor: bp.Event('tennant_arrived')});
    bsync({request: bp.Event('AC: turnon')});
  }

});
