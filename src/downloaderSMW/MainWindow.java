package downloaderSMW;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MainWindow {

	private JFrame frame;
	public static JLabel lblDlcEnabled;
	public static JButton btnDownload;
	public static JButton btnLaunch;

	private static boolean msu1;
	private static boolean voice;
	public static boolean dlcenabled;
	public static boolean dlc;
	private static String description;
	private static String title;
	private static JCheckBox chckbxMsu;
	private static JCheckBox chckbxVoices;
	private static boolean downloaded;




	/**
	 * Launch the application.
	 */
	public static void mainWindowLoad() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					int emuline;
					try {
						emuline = Macros.RetrieveLine("[SETTINGS]", "database.ini");
						msu1 = Macros.returnBool(emuline, 0, "database.ini");
						voice = Macros.returnBool(emuline, 1, "database.ini");
						dlc = Macros.returnBool(emuline, 2, "database.ini");
						emuline = Macros.RetrieveLine("[DESCRIPTION]", "database.ini");
						description = Macros.returnString(emuline, 0, "database.ini");
						emuline = Macros.RetrieveLine("[HACKNAME]", "database.ini");
						title = Macros.returnString(emuline, 0, "database.ini");
						emuline = Macros.RetrieveLine("[DOWNLOADED]", "config.ini");
						String deb = Macros.returnString(emuline, 0, "config.ini");
						downloaded = Macros.checkParsable(deb);

						System.out.println(downloaded);
						System.out.println(deb);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnLaunch = new JButton("LAUNCH");
		btnLaunch.setEnabled(downloaded);
		btnLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int emuline;
				try {
					emuline = Macros.RetrieveLine("[SETTINGS]", "config.ini");
					boolean ece = Macros.returnBool(emuline, 1, "config.ini");
					if(ece) {
						File file = new File("hack\\rom.smc");
						emuline = Macros.RetrieveLine("[EXECUTABLE]", "config.ini");
						String executable = Macros.returnString(emuline, 0, "config.ini");
					    Process p = null;
					    ProcessBuilder execute = new ProcessBuilder(executable, file.getAbsolutePath());
					    execute.directory(new File("hack\\"));
					    p = execute.start();
					}
					else {
						Desktop.getDesktop().open(new File("hack\\"));
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnLaunch.setBounds(276, 11, 106, 86);
		frame.getContentPane().add(btnLaunch);
		if(msu1 == true) {
		chckbxMsu = new JCheckBox("MSU-1");
		chckbxMsu.setBounds(272, 212, 97, 23);
		frame.getContentPane().add(chckbxMsu);
		}
		
		if(voice == true) {
		chckbxVoices = new JCheckBox("Voice Acting");
		chckbxVoices.setBounds(272, 186, 97, 23);
		frame.getContentPane().add(chckbxVoices);
		
		JLabel lblHighQuality = new JLabel("High Quality");
		lblHighQuality.setBounds(292, 172, 73, 14);
		frame.getContentPane().add(lblHighQuality);
		}
		lblDlcEnabled = new JLabel("DLC ENABLED!");
		lblDlcEnabled.setBounds(317, 251, 75, 14);
		frame.getContentPane().add(lblDlcEnabled);
		lblDlcEnabled.setVisible(dlcenabled);

		if(dlc == true) {
		JButton btnDlc = new JButton("DLC");
		btnDlc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLCMenu.dlcMenuLoad();
				lblDlcEnabled.setVisible(dlcenabled);
			}
		});

		btnDlc.setBounds(276, 138, 89, 23);
		frame.getContentPane().add(btnDlc);
		}
		JButton btnNewButton = new JButton("Options");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionsMenu.OptionsMenuLoad();
			}
		});
		btnNewButton.setBounds(276, 104, 106, 23);
		frame.getContentPane().add(btnNewButton);
		
        ImageIcon icon = new ImageIcon("image.png");
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(10, 11, 256, 224);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea txtrSuperMarioWorld = new JTextArea();
		txtrSuperMarioWorld.setWrapStyleWord(true);
		txtrSuperMarioWorld.setLineWrap(true);
		txtrSuperMarioWorld.setText(description);
		txtrSuperMarioWorld.setBounds(10, 276, 256, 106);
		frame.getContentPane().add(txtrSuperMarioWorld);
		
		btnDownload = new JButton("DOWNLOAD");
		btnDownload.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean msu1temp = false;
				boolean voicetemp  = false;
				if(chckbxMsu.isSelected()) {
					msu1temp = true;
				}
				if(chckbxVoices.isSelected()) {
					voicetemp = true;
				}
				try {
					Download.hack(msu1temp, voicetemp);
					int emuline = Macros.RetrieveLine("[DOWNLOADED]", "database.ini");
					String version = Macros.returnString(emuline, 0, "database.ini");
					String version2 = Macros.returnString(emuline, 1, "database.ini");
					String version3 = Macros.returnString(emuline, 2, "database.ini");
					File file = new File("config.ini");
					FileWriter fr = new FileWriter(file, true);
					BufferedWriter br = new BufferedWriter(fr);
					PrintWriter pr = new PrintWriter(br);
					pr.println("[DOWNLOADED]");
					pr.println(version);
					pr.println(version2);
					pr.println(version3);
					pr.close();
					br.close();
					fr.close();
					btnLaunch.setEnabled(true);
					lblDlcEnabled.setEnabled(false);
					

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDownload.setEnabled(true);
		btnDownload.setBounds(268, 276, 114, 106);
		frame.getContentPane().add(btnDownload);
		
		JLabel lblNewLabel_1 = new JLabel(title);
		lblNewLabel_1.setBounds(10, 251, 297, 14);
		frame.getContentPane().add(lblNewLabel_1);
		

	}
}
