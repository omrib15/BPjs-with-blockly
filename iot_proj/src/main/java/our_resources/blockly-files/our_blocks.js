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
	"output": "BP_EVENT",
    "colour": "0",
    "tooltip": "A BP Event"
  },
  ])
  
  Blockly.Blocks['bp_bsync'] = {
  init: function() {
    this.setColour(290);
    this.appendDummyInput()
        .appendTitle("sync");
    this.appendValueInput("REQUEST")
        .setCheck(['event', Array])
        .appendTitle("requested=");
    this.appendValueInput("WAIT-FOR")
        .setCheck(['event', Array])
        .appendTitle("wait-for=");
    this.appendValueInput("BLOCK")
        .setCheck(['event', Array])
        .appendTitle("block=");
    this.setInputsInline(true);
    this.setPreviousStatement(true);
    this.setNextStatement(true);
    this.setTooltip('Synchronize with all other b-threads and post own decleratios.');
  }
};