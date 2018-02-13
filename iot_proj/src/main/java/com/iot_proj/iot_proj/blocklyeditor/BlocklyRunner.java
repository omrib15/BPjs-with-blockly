package com.iot_proj.iot_proj.blocklyeditor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class BlocklyRunner {

	private String path_to_file = "TBD";
			
	public int runBlocklyEditor() throws IOException {
		File htmlFile = new File(path_to_file);
		Desktop.getDesktop().browse(htmlFile.toURI());
		return 1;
	}
}
