var Count, tankWaterAmount, x, amount, tankMaxCapacity, text, e, event_name;

/**
 * Describe this function...
 */
function addWater(amount) {
  tankWaterAmount = tankWaterAmount + amount;
  if (tankWaterAmount >= tankMaxCapacity) {
    tankWaterAmount = 100;
  }
}

/**
 * Describe this function...
 */
function drawWater(amount) {
  tankWaterAmount = tankWaterAmount - amount;
  if (tankWaterAmount <= 0) {
    tankWaterAmount = 0;
  }
}

/**
 * Describe this function...
 */
function is_rain(event_name) {
  if (event_name.startsWith('Rain:')) {
    return true;
  }
  return false;
}

/**
 * Describe this function...
 */
function is_draw(event_name) {
  if (event_name.startsWith('Draw:')) {
    return true;
  }
  return false;
}

function subsequenceFromStartLast(sequence, at1) {
  var start = at1;
  var end = sequence.length - 1 + 1;
  return sequence.slice(start, end);
}


tankMaxCapacity = 100;
tankWaterAmount = 0;

bp.registerBThread('Rain ', function(){
  while (true) {
    e = bsync({waitFor: (bp.EventSet("es187", function(e) {
      return (is_rain((e.getName())));
    }))});
    addWater((parseInt((subsequenceFromStartLast((e.getName()), 6)))));
    bsync({request: bp.Event(('Current water amount: '+tankWaterAmount))});
    if (tankWaterAmount == tankMaxCapacity) {
      bsync({request: bp.Event('Tank full')});
    }
  }

});

bp.registerBThread('Draw', function(){
  while (true) {
    e = bsync({waitFor: (bp.EventSet("es188", function(e) {
      return (is_draw((e.getName())));
    }))});
    drawWater((parseInt((subsequenceFromStartLast((e.getName()), 6)))));
    bsync({request: bp.Event(('Current water amount: '+tankWaterAmount))});
    if (tankWaterAmount == 0) {
      bsync({request: bp.Event('Tank empty, can\'t draw any more')});
    }
  }

});
