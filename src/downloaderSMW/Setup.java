package downloaderSMW;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class Setup {
	public static boolean update; //Get updates "Flag"
	public static boolean platform; //Get updates "Flag"

	private static String initName = ""; //The hack name
	private static int emulator = 0;
		public static void Setup() throws IOException {
			int temp = Macros.RetrieveLine("[HACKNAME]", "database.ini");
			initName = Macros.returnString(temp, 0, "database.ini");
	        Object[] simp = {"Yes",
	        "No"};
	        Object[] platformText = {"PC",
	        "Console/Android/Others"};

	        int updates = JOptionPane.showOptionDialog(null,
	        		"Welcome to " + initName + ", this guide will help you setup the hack launcher. \n Would you like to receive auto-updates?",
	        		initName + " Launcher",
	        		JOptionPane.YES_NO_OPTION,
	        		JOptionPane.QUESTION_MESSAGE,
	        		null,     //do not use a custom Icon
	        		simp,  //the titles of buttons
	        		simp[0]); //default button title
	        update = (updates == JOptionPane.YES_OPTION);
	        int platformer = JOptionPane.showOptionDialog(null,
	        		"Next, which platform are you planning on playing on?",
	        		initName + " Launcher: Initial setup",
	        		JOptionPane.YES_NO_OPTION,
	        		JOptionPane.QUESTION_MESSAGE,
	        		null,     //do not use a custom Icon
	        		platformText,  //the titles of buttons
	        		platformText[0]); //default button title
	        platform = (platformer == JOptionPane.YES_OPTION);
	        if(platform == true) {
	        	PCSetup();
	        }
	        AndroidSetup();
}
		private static void AndroidSetup() throws IOException {
			JOptionPane.showMessageDialog(null, "Finally, select your SMW Rom to be used by the game (MUST BE CLEAN!)");
	        JFileChooser saver = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	        int save = saver.showSaveDialog(null); 
	        File sav = saver.getSelectedFile();
			int emuline = Macros.RetrieveLine("[IMAGE]", "database.ini");
			String downlink = Macros.returnString(emuline, 0, "database.ini");
			InputStream is = new URL(downlink).openStream();
			Files.copy(is, Paths.get("image.png"), StandardCopyOption.REPLACE_EXISTING);
 			is = new URL(downlink).openStream();
			Files.copy(is, Paths.get("dlc.png"), StandardCopyOption.REPLACE_EXISTING);
			JOptionPane.showMessageDialog(null, "Thank you for your time!");
			emuline = Macros.RetrieveLine("[EXECUTABLE]", "database.ini");
			String downloadLink = Macros.returnString(emuline, emulator, "database.ini");
	        PrintWriter print = new PrintWriter("config.ini");
	        print.println("[ROM]");
	        print.println(sav.toString());
	        print.println("[END]");
	        print.println("The following settings work in this fashion: update, platform");
	        print.println("[SETTINGS]");
	        print.println(update);
	        print.println(platform);
	        print.println("[END]");
	        print.println("[EXECUTABLE]");
	        print.println("emulator\\" + downloadLink);
	        print.println("[END]");
	        print.close();

			
		}
		private static void PCSetup() throws IOException {
			int emuline = Macros.RetrieveLine("[EMULATORNAMES]", "database.ini");
			ArrayList<String> emulators = Macros.addToArray(emuline, "database.ini");
			emulators.add("Other");
			System.out.println(emulators.toString());
	        Object[] text = emulators.toArray(); 
	        emulator = JOptionPane.showOptionDialog(null,
	        		"Which emulator would you like to use?",
	        		initName + " Launcher",
	        		JOptionPane.YES_NO_OPTION,
	        		JOptionPane.QUESTION_MESSAGE,
	        		null,     //do not use a custom Icon
	        		text,  //the titles of buttons
	        		text[0]); //default button title
	        if(emulator != text.length-1) {
	        	Download.emulator(emulator);
	        }
	        else {
	        	platform = false;
	        	AndroidSetup();
	        }
	        }
		public static void update() throws Exception {
			
			int temp = Macros.RetrieveLine("[HACKNAME]", "database.ini");
			initName = Macros.returnString(temp, 0, "database.ini");
	        Object[] simp = {"Yes",
	        "No"};
	        Object[] platformText = {"PC",
	        "Console/Android/Others"};

	        int updates = JOptionPane.showOptionDialog(null,
	        		"Update detected! Would you like to update now?",
	        		initName + " Launcher",
	        		JOptionPane.YES_NO_OPTION,
	        		JOptionPane.QUESTION_MESSAGE,
	        		null,     //do not use a custom Icon
	        		simp,  //the titles of buttons
	        		simp[0]); //default button title
	        update = (updates == JOptionPane.YES_OPTION);
	        	if(!update) {
	        		return;
	        	}
	        int platformer = JOptionPane.showOptionDialog(null,
	        		"Next, which platform are you planning on playing on?",
	        		initName + " Launcher: Initial setup",
	        		JOptionPane.YES_NO_OPTION,
	        		JOptionPane.QUESTION_MESSAGE,
	        		null,     //do not use a custom Icon
	        		platformText,  //the titles of buttons
	        		platformText[0]); //default button title
	        platform = (platformer == JOptionPane.YES_OPTION);
	        if(platform == true) {
	        	PCSetup();
	        }
	        AndroidSetup();

			
		}
}
