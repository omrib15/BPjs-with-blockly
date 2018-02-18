
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
	"inputsInline": true,
    "output" : "BP_EVENT",
	"colour": 0,
    "tooltip": "A BP Event"
  },
  
  {
  "type": "bp_event_of_list",
  "message0": "BP Event %1",
  "args0": [
    {
      "type": "input_value",
      "name": "NAME",
      "check": "String"
    }
  ],
  "inputsInline": true,
  "previousStatement": null,
  "nextStatement": "BP_EVENT",
  "colour": 0,
  "tooltip": "Use this block if you are using the list of BP Events block",
  "helpUrl": ""
},
 
  
  {
  "type": "bp_event_list",
  "message0": "List of BP Events %1 %2",
  "args0": [
    {
      "type": "input_dummy"
    },
    {
      "type": "input_statement",
      "name": "LIST",
      "check": "BP_EVENT"
    }
  ],
  "output": ["BP_EVENT_LIST","Array"],
  "colour": 5,
  "tooltip": "A list of BP Events",
  "helpUrl": ""
},

  {
  "type": "bp_bsync",
  "message0": "Wait %1 Request %2 Block %3",
  "args0": [
    {
      "type": "input_value",
      "name": "WAIT",
      "check": ["BP_EVENT","BP_EVENT_LIST"]
    },
    {
      "type": "input_value",
      "name": "REQUEST",
      "check": "BP_EVENT"
    },
    {
      "type": "input_value",
      "name": "BLOCK",
      "check": ["BP_EVENT","BP_EVENT_LIST"]
    }
  ],
  "previousStatement": null,
  "nextStatement": null,
  "colour": 5,
  "tooltip": "A single bsync statement",
  "helpUrl": ""
},
{
  "type": "bp_bsync_with_output",
  "message0": "Wait %1 Request %2 Block %3",
  "args0": [
    {
      "type": "input_value",
      "name": "WAIT",
      "check": ["BP_EVENT","BP_EVENT_LIST"]
    },
    {
      "type": "input_value",
      "name": "REQUEST",
      "check": "BP_EVENT"
    },
    {
      "type": "input_value",
      "name": "BLOCK",
      "check": ["BP_EVENT","BP_EVENT_LIST"]
    }
  ],
  "output":"BP_EVENT",
  "colour": 5,
  "tooltip": "Use this block if you would like to utilize the value returned by the bsync",
  "helpUrl": ""
}
  ])
  
  
  Blockly.JavaScript['bp_event'] = function(block) {
  var event_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  if(event_name == '\'\'')
	  event_name = '\'Anonymous event\''
  var code = 'bp.Event('+event_name+')';
  return [code, Blockly.JavaScript.ORDER_ATOMIC]};
  
  //Blockly.JavaScript['bp_event_of_list'] =  Blockly.JavaScript['bp_event']
  
  
  
  Blockly.JavaScript['bp_event_list'] = function(block) {
  var events_string = Blockly.JavaScript.valueToCode(block, 'LIST');
  //console.log(events_string);
  events = breakEventsString(events_string);
  //console.log(events)
  var code = '';
  events.forEach(function(entry){
	code+=entry+',\n';
  });
  code=code.substring(0,code.length-2);
  code = '['+code+']';
  return [code, Blockly.JavaScript.ORDER_ATOMIC]};
  
  var breakEventsString = function(events_string){
	result = [];
	split = events_string.split('bp.Event');
	//console.log(split)
	split.forEach(function(entry){
		if(entry.startsWith('(')){
			new_entry = entry;
			while(true)
				if(new_entry.endsWith(',0')){
					new_entry = new_entry.substring(0,new_entry.length-2);
				}
				else
					break;
			//console.log(new_entry);	
			result.push('bp.Event'+new_entry);
	}}); 
	
	return result
  };
  
 Blockly.JavaScript['bp_bsync'] = function(block) {
  var value_wait = Blockly.JavaScript.valueToCode(block, 'WAIT', Blockly.JavaScript.ORDER_ATOMIC) || 'null';
  var value_request = Blockly.JavaScript.valueToCode(block, 'REQUEST', Blockly.JavaScript.ORDER_ATOMIC) || 'null';
  var value_block = Blockly.JavaScript.valueToCode(block, 'BLOCK', Blockly.JavaScript.ORDER_ATOMIC) || 'null';

  
  var code = 'bsync({waitFor: '+value_wait+',request: '+value_request+',block: '+value_block+'});\n';
  //code='bp.log.info(found: '+value_wait+');\n'+code;
  return code;
  //return [code, Blockly.JavaScript.ORDER_ATOMIC];
};

//Blockly.JavaScript['bp_bsync_with_output'] = Blockly.JavaScript['bp_bsync'] //same generation
