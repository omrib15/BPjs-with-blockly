var input = document.querySelector('#project_file');
input.addEventListener('change', load_project);


function save_project() {
    var xml = Blockly.Xml.workspaceToDom(workspace);
    var xml_text = Blockly.Xml.domToText(xml);
	saveTextAs(xml_text, "xml.xml");
};

function input_click(){
	
	var elem = document.getElementById('project_file');
	if(elem && document.createEvent) {
      var evt = document.createEvent("MouseEvents");
      evt.initEvent("click", true, false);
      elem.dispatchEvent(evt);
   }
}

function load_project(event){
	var files = input.files;
	if(files.length === 0)
		return;
	current_file = files[0];
	
	 var reader = new FileReader();
     reader.onload = function(){
		var xml_text = reader.result;
		console.log(reader.result.substring(0, 200));
		//Blockly.mainWorkspace.clear()
		workspace.clear();
		var xml = Blockly.Xml.textToDom(xml_text);
		Blockly.Xml.domToWorkspace(xml, workspace);
        };
     reader.readAsText(current_file);

}





// fetch('file.txt')
  // .then(response => response.text())
  // .then(text => console.log(text))

function readTextFile(file)
{
    var rawFile = new XMLHttpRequest();
    rawFile.open("GET", file, false);
    rawFile.onreadystatechange = function ()
    {
        if(rawFile.readyState === 4)
        {
            if(rawFile.status === 200 || rawFile.status == 0)
            {
                var allText = rawFile.responseText;
                alert(allText);
            }
        }
    }
    rawFile.send(null);
}


function export_code(){
	
}