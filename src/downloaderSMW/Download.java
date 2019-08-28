package downloaderSMW;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Download {
	public static JFrame frame;
	public static class downloadDisplay extends JPanel{  
	    
	    public void paint(Graphics g) {  
	         super.paintComponent(g);
	        Toolkit t=Toolkit.getDefaultToolkit();  
	        Image i=t.getImage("download.gif");  
	        g.drawImage(i, 0,0,this);  
	        g.drawString("Downloading... Please Wait...", 50, 25);
	          
	    }
	}
	public static void emulator(int choice) throws IOException {
		int emuline = Macros.RetrieveLine("[EMULATORS]", "database.ini");
		String downloadLink = Macros.returnString(emuline, choice, "database.ini");
		System.out.println(downloadLink);
		showDownloadWindow("Downloading");
		InputStream inputStream = new URL(downloadLink).openStream();
		Files.copy(inputStream, Paths.get("temp.zip"), StandardCopyOption.REPLACE_EXISTING);
		Macros.unzip("temp.zip", "emulator");
		File file = new File("temp.zip");
		file.delete();
		frame.dispose();


	}
	private static void showDownloadWindow(String action) {
		frame = new JFrame("SNESDownload");

		downloadDisplay content = new downloadDisplay();
	     JLabel downloadtext = new JLabel(action + "... Please wait...", SwingConstants.CENTER);
	     content.add(downloadtext);
	      frame.setContentPane(content);
	      frame.setSize(275,100);
	      frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	public static void hack(boolean msu1, boolean voice) throws Exception {
		showDownloadWindow("Downloading Hack, this may take a while.");
		frame.dispose();
		showDownloadWindow("Downloading");
		System.out.println("Downloading Hack..");
		Macros.downloadFileAtString("[DOWNLOAD]");
		if(msu1) {
			System.out.println("Downloading MSU1..");

			Macros.downloadFileAtString("[MSU1]");
		}
		if(voice) {
			System.out.println("Downloading voices..");

			Macros.downloadFileAtString("[VOICES]");
		}
		frame.dispose();
		patchRom();
		JOptionPane.showMessageDialog(null, "Download completed successfully!!");


	}
	public static void dlc(boolean msu1, boolean voice) throws Exception {
		showDownloadWindow("Downloading");
		System.out.println("Downloading Hack..");
		Macros.downloadFileAtString("[DLCDOWNLOAD]");
		if(msu1) {
			Macros.downloadFileAtString("[DLCMSU1]");
		}
		if(voice) {
			Macros.downloadFileAtString("[DLCVOICES]");
		}
		frame.dispose();
		patchRom();
		JOptionPane.showMessageDialog(null, "Download completed successfully!!");
	}
	private static void patchRom() throws Exception {
		int emuline = Macros.RetrieveLine("[PATCHNAMES]", "database.ini");
		ArrayList<String> emulators = Macros.addToArray(emuline, "database.ini");
		emulators.add("Manual Patching");
		System.out.println(emulators.toString());
        Object[] text = emulators.toArray(); 
        int choice = JOptionPane.showOptionDialog(null,
        		"Which patcher would you like to use?",
        		"SNESDownload",
        		JOptionPane.YES_NO_OPTION,
        		JOptionPane.QUESTION_MESSAGE,
        		null,     //do not use a custom Icon
        		text,  //the titles of buttons
        		text[0]); //default button title
        System.out.println(choice);
        System.out.println(text.length-1);
        if(choice != text.length-1) {
		Macros.downloadFileAtString("[PATCHER]","patcher");
		emuline = Macros.RetrieveLine("[PATCHEXECUTABLES]", "database.ini");
		System.out.println("patcher running!");
		String executable = Macros.returnString(emuline, choice, "database.ini");
		emuline = Macros.RetrieveLine("[ROM]", "config.ini");
		String rom = Macros.returnString(emuline, 0, "config.ini");
		System.out.println(rom);
	    Files.copy(Paths.get(rom), Paths.get("patcher\\rom.smc"), StandardCopyOption.REPLACE_EXISTING);
	    Files.copy(Paths.get("hack\\patch.bps"), Paths.get("patcher\\patch.bps"), StandardCopyOption.REPLACE_EXISTING);
	    
	    Process p = null;
	    ProcessBuilder execute = new ProcessBuilder("patcher\\" + executable, "patch.bps", "rom.smc");
	    execute.directory(new File("patcher\\"));
	    p = execute.start();
		 p.waitFor();
		 Files.copy(Paths.get("patcher\\rom.smc"), Paths.get("hack\\rom.smc"), StandardCopyOption.REPLACE_EXISTING);
		delete("hack\\patch.bps");
		delete("patcher\\rom.smc");
        }

	}
	public static void saveLoad() throws IOException {
        JFileChooser saver = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int save = saver.showOpenDialog(null); 
        File sav = saver.getSelectedFile();
		 Files.copy(Paths.get(sav.getPath()), Paths.get("emulator\\Saves\\rom.srm"), StandardCopyOption.REPLACE_EXISTING);
			JOptionPane.showMessageDialog(null, "Loaded Successfully!");

		
	}
	public static void saveDump() throws IOException {
        JFileChooser saver = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        saver.setSelectedFile(new File("rom.srm"));
        int save = saver.showSaveDialog(null); 
        File sav = saver.getSelectedFile();
		 Files.copy(Paths.get("emulator\\Saves\\rom.srm"), Paths.get(sav.getPath()), StandardCopyOption.REPLACE_EXISTING);
			JOptionPane.showMessageDialog(null, "Saved Successfully!");

		
	}
	public static void delete(String string) {
		// TODO Auto-generated method stub
		File file = new File(string);
		file.delete();

	}

}
	
