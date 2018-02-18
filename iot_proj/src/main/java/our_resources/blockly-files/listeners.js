function save_project() {
    var xml = Blockly.Xml.workspaceToDom(workspace);
    var xml_text = Blockly.Xml.domToText(xml);
	console.log(xml_text);
};


function load_project(){
}


function export_code(){
	
}