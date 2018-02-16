//Blockly.JavaScript = Blockly.Generator.get('JavaScript');

/*
Blockly.Blocks['bp_event'] = {
  init: function() {
    this.appendValueInput('VALUE')
        .setCheck('String')
        .appendField('Event name');
    this.setColour(150);
    this.setTooltip('A BP Event');
  }
};
*/

Blockly.defineBlocksWithJsonArray([  
 {
    "type": "bp_event",
    "message0": "BP Event %1",
    "args0": [
      {
        "type": "input_value",
        "name": "TEXT",
        "check": "String"
      }
    ],
    "colour": "0",
    "tooltip": "A BP Event"
  }
  ])