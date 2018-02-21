/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iot_proj.iot_proj.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;

import com.iot_proj.iot_proj.blocklyeditor.BlocklyRunner;



/**
 *
 * @author omri basch
 */
public class MainFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrameFuncs funcs;
    private String javaFolderAbsPath;
	private final String examplesRelPath;
	private final String userRelPath;
    private String currFileName;
    private DefaultListModel<String> eventsModel;
    private JFileChooser fileChooser;
    private boolean isExample;
  

	public String getJavaFolderAbsPath() {
		return javaFolderAbsPath;
	}

	public void setJavaFolderAbsPath(String javaFolderAbsPath) {
		this.javaFolderAbsPath = javaFolderAbsPath;
	}

	public String getCurrFileName() {
		return currFileName;
	}

	public void setCurrFileName(String currFileName) {
		this.currFileName = currFileName;
		selectedFileLabel.setText(currFileName);
	}

	public String getExamplesRelPath() {
		return examplesRelPath;
	}

	public MainFrameFuncs getFuncs() {
		return funcs;
	}
        
        
        //updates the file list 
        private void updateFileList(){
            //the designated js files folder
            File folder = new File(this.javaFolderAbsPath + examplesRelPath);
            
            //iterate over the files in the folder and add them to the file list
            for (final File fileEntry : folder.listFiles()) {
                fileList.addItem(fileEntry.getName());
            }
        }
        
        
        private void setupEventsList(){
        	this.eventsModel = new DefaultListModel<String>();
        	this.eventsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            this.eventsList.setModel(eventsModel);
            this.eventsList.addMouseListener(new MouseAdapter(){
            	public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                    	//enqueue the selected event
                    	enqueueSelectedEvent();
                    }
            	}});
        }
	
	//the empty constructor
	public MainFrame() {
        initComponents();
        setupEventsList();
        this.fileChooser = new JFileChooser();
        this.funcs = new MainFrameFuncs(logTextArea,eventsModel);
        this.javaFolderAbsPath = System.getProperty("user.dir") + "/src/main/java/";
        this.examplesRelPath = "our_resources/examples/";
        this.userRelPath = "our_resources/user/";
        this.currFileName = "";
        this.isExample = true;
       
        
        //update the file list
        updateFileList();
        
        }
	

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        runButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        fileList = new javax.swing.JComboBox<>();
        createButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        logLabel = new javax.swing.JLabel();
        eventLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        eventsList = new javax.swing.JList<>();
        enqueueButton = new javax.swing.JButton();
        newEventTextField = new javax.swing.JTextField();
        addEventButton = new javax.swing.JButton();
        clearLogButton = new javax.swing.JButton();
        removeEventButton = new javax.swing.JButton();
        openFileButton = new javax.swing.JButton();
        selectedFileLabel = new javax.swing.JLabel();
        examplesLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.selectionBackground"));
        setForeground(java.awt.Color.white);
        setMaximumSize(new java.awt.Dimension(600, 444));
        setMinimumSize(new java.awt.Dimension(600, 444));
        setName("MainFrame"); // NOI18N
        setResizable(false);

        runButton.setBackground(new java.awt.Color(255, 255, 255));
        runButton.setText("Run:");
        runButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Behavioral Programming with Blockly");

        fileList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        fileList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileListActionPerformed(evt);
            }
        });

        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {     
					try {
						createButtonActionPerformed(evt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
            }
        });

        logTextArea.setEditable(false);
        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        jScrollPane1.setViewportView(logTextArea);

        logLabel.setText("Log");

        eventLabel.setText("Events");

        eventsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(eventsList);

        enqueueButton.setText("Enqueue");
        enqueueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enqueueButtonActionPerformed(evt);
            }
        });

        newEventTextField.setText("event1");

        addEventButton.setText("add event");
        addEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventButtonActionPerformed(evt);
            }
        });

        clearLogButton.setText("Clear Log");
        clearLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLogButtonActionPerformed(evt);
            }
        });

        removeEventButton.setText("Remove");
        removeEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeEventButtonActionPerformed(evt);
            }
        });

        openFileButton.setText("Open");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });

        selectedFileLabel.setText("no file selected");

        examplesLabel.setText("examples:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(eventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(examplesLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(newEventTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addEventButton)
                                    .addComponent(enqueueButton)
                                    .addComponent(removeEventButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(fileList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(openFileButton)
                                .addGap(18, 18, 18)
                                .addComponent(runButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(logLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(selectedFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(createButton)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(clearLogButton)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addEventButton, clearLogButton, enqueueButton, removeEventButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton)
                    .addComponent(runButton)
                    .addComponent(fileList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openFileButton)
                    .addComponent(examplesLabel)
                    .addComponent(jLabel3)
                    .addComponent(selectedFileLabel))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(enqueueButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(removeEventButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(newEventTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addEventButton)))
                            .addComponent(clearLogButton))
                        .addGap(0, 154, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //action listener to the file list
    private void fileListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileListActionPerformed
        
    	JComboBox cb = (JComboBox)evt.getSource();
        
        //getting the selected file's name 
        String fileName = (String)cb.getSelectedItem();
        setCurrFileName(fileName);
        isExample = true;
    }//GEN-LAST:event_fileListActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_createButtonActionPerformed
        funcs.openBlockly();
    }//GEN-LAST:event_createButtonActionPerformed

    private void enqueueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enqueueButtonActionPerformed
        enqueueSelectedEvent();
    }//GEN-LAST:event_enqueueButtonActionPerformed

    private void addEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventButtonActionPerformed
    	
        funcs.enqueueExternalEvent(newEventTextField.getText());
    	//eventsModel.addElement(newEventTextField.getText());
    }//GEN-LAST:event_addEventButtonActionPerformed

    private void clearLogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLogButtonActionPerformed
        logTextArea.setText("");
    }//GEN-LAST:event_clearLogButtonActionPerformed

    private void removeEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeEventButtonActionPerformed
        eventsModel.removeElement(eventsList.getSelectedValue());
    }//GEN-LAST:event_removeEventButtonActionPerformed

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        int returnVal = fileChooser.showOpenDialog(MainFrame.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            File fileInDir = new File(javaFolderAbsPath + userRelPath + file.getName());
            setCurrFileName(file.getName());
            
            try {
				funcs.copyFileUsingStream(file, fileInDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            isExample = false;
        }
        
    }//GEN-LAST:event_openFileButtonActionPerformed
    
    
    private void enqueueSelectedEvent(){
    	if(funcs.isProgRunning() && !eventsList.isSelectionEmpty()){
    		funcs.enqueueExternalEvent(eventsList.getSelectedValue());
    		eventsModel.removeElement(eventsList.getSelectedValue());
    	}
    }
    

    //click event listener for the run button
    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
    	if(!getCurrFileName().equals("")){
    	
    		try {
    			//run the chosen js file 
    			if(isExample){
    				funcs.runBprog(examplesRelPath + currFileName);
    			}
    			else{
    				funcs.runBprog(userRelPath + currFileName);
    			}
    		} catch (InterruptedException e) {
    			e.printStackTrace();
			}
    	}
    	
    	
    	
    }


	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEventButton;
    private javax.swing.JButton clearLogButton;
    private javax.swing.JButton createButton;
    private javax.swing.JButton enqueueButton;
    private javax.swing.JLabel eventLabel;
    private javax.swing.JList<String> eventsList;
    private javax.swing.JLabel examplesLabel;
    private javax.swing.JComboBox<String> fileList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel logLabel;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JTextField newEventTextField;
    private javax.swing.JButton openFileButton;
    private javax.swing.JButton removeEventButton;
    private javax.swing.JButton runButton;
    private javax.swing.JLabel selectedFileLabel;
    // End of variables declaration//GEN-END:variables
}
