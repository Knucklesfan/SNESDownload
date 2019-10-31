package downloaderSMW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Macros {
	public static int RetrieveLine(String emustring, String file) throws IOException {
	File database = new File(file);
	Scanner fileReader = new Scanner(database);
	int lineNum = 0;
	int emuline = 0;
	while(fileReader.hasNextLine()) {
        String line = fileReader.nextLine();
        if(line.equals(emustring)) {
        	emuline = lineNum;
        	System.out.println(emuline);
        }
        lineNum++;
	}
	fileReader.close();
	return emuline;
	}

	public static String returnString(int emuline, int choice, String file) throws IOException {
		File database = new File(file);
		Scanner fileReader = new Scanner(database);
		fileReader = new Scanner(database);
		for(int x = 0; x <= emuline + choice; x++) {
			fileReader.nextLine();
		}
		return fileReader.nextLine();
	}
	public static ArrayList<String> addToArray(int emuline, String file) throws IOException {
		File database = new File(file);
		Scanner fileReader = new Scanner(database);
		for(int x = 0; x <= emuline; x++) {
			fileReader.nextLine();
		}
		ArrayList<String> emulators = new ArrayList<String>();
		String tmp;
		while(fileReader.hasNextLine()) {
			tmp = fileReader.nextLine();
			if(tmp.equals("[END]")) {
				break;
			}
			emulators.add(tmp);
		}
		fileReader.close();
		return emulators;
	}
    public static void unzip(String zipFilePath, String destDir) {
        //Method not written by me, credits to JournalDev.com
    	File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[2048];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	public static boolean returnBool(int emuline, int choice, String file) throws IOException {
		File database = new File(file);
		Scanner fileReader = new Scanner(database);
		fileReader = new Scanner(database);
		for(int x = 0; x <= emuline + choice; x++) {
			fileReader.nextLine();
		}
		return fileReader.nextBoolean();
	}
	public static void downloadFileAtString(String text) throws IOException {
		int emuline = Macros.RetrieveLine(text, "database.ini");
		String downloadLink = Macros.returnString(emuline, 0, "database.ini");
		InputStream inputStream = new URL(downloadLink).openStream();
		Files.copy(inputStream, Paths.get("temp.zip"), StandardCopyOption.REPLACE_EXISTING);
		Macros.unzip("temp.zip", "hack");
		File file = new File("temp.zip");
		file.delete();

	}
	public static void downloadFileAtString(String text, String folder) throws IOException {
		int emuline = Macros.RetrieveLine(text, "database.ini");
		String downloadLink = Macros.returnString(emuline, 0, "database.ini");
		InputStream inputStream = new URL(downloadLink).openStream();
		Files.copy(inputStream, Paths.get("temp.zip"), StandardCopyOption.REPLACE_EXISTING);
		Macros.unzip("temp.zip", folder);
		File file = new File("temp.zip");
		file.delete();

	}

	public static boolean checkParsable(String string){
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}

}