package downloaderSMW;

import java.io.*;
import java.lang.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import javax.swing.*;

public class Main {
	public static boolean FirstRun;
	public static String link = "https://www.dropbox.com/s/rncst247vr1nutl/database.ini?dl=1";
	public static void main(String[] args) throws Exception {

		File config = new File("config.ini");
		if(!config.exists()) {
			InputStream inputStream = new URL(link).openStream();
			Files.copy(inputStream, Paths.get("database.ini"), StandardCopyOption.REPLACE_EXISTING);
			FirstRun = true;
			Setup.Setup();
		}
		int emuline = Macros.RetrieveLine("[SETTINGS]", "config.ini");
		boolean c = Macros.returnBool(emuline, 0, "config.ini");
		if(c == true) {
		InputStream inputStream = new URL(link).openStream();
		Files.copy(inputStream, Paths.get("database.ini"), StandardCopyOption.REPLACE_EXISTING);
		emuline = Macros.RetrieveLine("[DOWNLOADED]", "config.ini");
		String a = Macros.returnString(emuline, 0, "config.ini");
		emuline = Macros.RetrieveLine("[DOWNLOADED]", "database.ini");
		String b = Macros.returnString(emuline, 0, "database.ini");
		System.out.println(a + " " + b);
		if(!a.equals(b) && !FirstRun) {
			Setup.update();
		}
		}
        MainWindow.mainWindowLoad();


	}

}