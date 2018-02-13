package com.iot_proj.iot_proj.blocklyeditor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/*
 * Call upon run() to open our blockly editor
 */
public class BlocklyRunner {

	private String path_to_file = "src/main/java/our_resources/blockly-files/index.html";
			
	public int run() throws IOException {
		File htmlFile = new File(path_to_file);
		Desktop.getDesktop().browse(htmlFile.toURI());
		return 1;
	}
}
