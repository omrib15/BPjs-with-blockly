var Count, tankWaterAmount, x, amount, tankMaxCapacity, text;

/**
 * Describe this function...
 */
function addWater(amount) {
  tankWaterAmount = (parseInt(tankWaterAmount)) + (parseInt(amount));
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

function subsequenceFromStartLast(sequence, at1) {
  var start = at1;
  var end = sequence.length - 1 + 1;
  return sequence.slice(start, end);
}


tankMaxCapacity = 100;
tankWaterAmount = 0;

bp.registerBThread('Rain ', function(){
  bsync({waitFor: (bp.EventSet("es163", function(e) {
    return ((e.getName()).startsWith('Rain: '));
  }))});
  addWater(subsequenceFromStartLast((e.getName()), 6));
  bsync({request: bp.Event(('Current water amount: '+tankWaterAmount))});
  if (tankWaterAmount == tankMaxCapacity) {
    bsync({request: bp.Event('Tank full')});
  } else {
  }

});

bp.registerBThread('Draw', function(){
  bsync({waitFor: (bp.EventSet("es164", function(e) {
    return ((e.getName()).startsWith('Draw: '));
  }))});
  drawWater(subsequenceFromStartLast((e.getName()), 6));
  bsync({request: bp.Event(('Current water amount: '+tankWaterAmount))});
  if (tankWaterAmount == 0) {
    bsync({request: bp.Event('Tank empty, can\'t draw')});
  } else {
  }

});
