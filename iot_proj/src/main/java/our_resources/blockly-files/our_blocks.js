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
        "name": "NAME",
        "check": "String"
      }
    ],
	"output": "BP_EVENT",
    "colour": "0",
    "tooltip": "A BP Event"
  },
  
  
  {
  "type": "bp_bsync",
  "message0": "Wait %1 Request %2 Block %3",
  "args0": [
    {
      "type": "input_value",
      "name": "WAIT",
      "check": "[BP_EVENT,BP_EVENT_LIST]"
    },
    {
      "type": "input_value",
      "name": "REQUEST",
      "check": "[BP_EVENT,BP_EVENT_LIST]"
    },
    {
      "type": "input_value",
      "name": "BLOCK",
      "check": "[BP_EVENT,BP_EVENT_LIST]"
    }
  ],
  "previousStatement": null,
  "nextStatement": null,
  "colour": 0,
  "tooltip": "A single bsync statement",
  "helpUrl": ""
}
  ])
  
  Blockly.JavaScript['text_indexOf'] = function(block) {
  // Search the text for a substring.
  var operator = block.getFieldValue('END') == 'FIRST' ? 'indexOf' : 'lastIndexOf';
  var subString = Blockly.JavaScript.valueToCode(block, 'FIND',
      Blockly.JavaScript.ORDER_NONE) || '\'\'';
  var text = Blockly.JavaScript.valueToCode(block, 'VALUE',
      Blockly.JavaScript.ORDER_MEMBER) || '\'\'';
  var code = text + '.' + operator + '(' + subString + ')';
  return [code, Blockly.JavaScript.ORDER_MEMBER];
};
  
  
  Blockly.JavaScript['bp_event'] = function(block) {
  var event_name = block.getFieldValue('END') == 'FIRST' ? 'indexOf' : 'lastIndexOf';
  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  var code = '...;\n';
  return code;
};
  
 Blockly.JavaScript['bp_bsync'] = function(block) {
  var value_wait = Blockly.JavaScript.valueToCode(block, 'WAIT', Blockly.JavaScript.ORDER_ATOMIC);
  var value_request = Blockly.JavaScript.valueToCode(block, 'REQUEST', Blockly.JavaScript.ORDER_ATOMIC);
  var value_block = Blockly.JavaScript.valueToCode(block, 'BLOCK', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};