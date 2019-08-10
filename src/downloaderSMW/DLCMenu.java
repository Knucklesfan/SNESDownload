package downloaderSMW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class DLCMenu {

	private JFrame frame;
	private static boolean msu1;
	private static boolean voice;
	private static boolean dlc;
	private static String description;
	private static String title;
	private static JCheckBox chckbxMsu;
	private static JCheckBox chckbxVoices;




	/**
	 * Launch the application.
	 */
	public static void dlcMenuLoad() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					int emuline;
					try {
						emuline = Macros.RetrieveLine("[DLCSETTINGS]", "database.ini");
						msu1 = Macros.returnBool(emuline, 0, "database.ini");
						voice = Macros.returnBool(emuline, 1, "database.ini");
						emuline = Macros.RetrieveLine("[DLCDESCRIPTION]", "database.ini");
						description = Macros.returnString(emuline, 0, "database.ini");
						emuline = Macros.RetrieveLine("[DLCNAME]", "database.ini");
						title = Macros.returnString(emuline, 0, "database.ini");


					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DLCMenu window = new DLCMenu();
					window.frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DLCMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 432);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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
		
        ImageIcon icon = new ImageIcon("dlc.png");
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
		
		JButton btnDownload = new JButton("DOWNLOAD");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean msu1temp = false;
				boolean voicetemp  = false;
				if(msu1) {
				if(chckbxMsu.isSelected()) {
					msu1temp = true;
				}
				}
				if(voice) {
				if(chckbxVoices.isSelected()) {
					voicetemp = true;
				}
				}
				try {
					Download.dlc(msu1temp, voicetemp);
				
				} catch (Exception e1) {
					// ew try and catch are gross
					e1.printStackTrace();
				}
				MainWindow.lblDlcEnabled.setEnabled(true);
				MainWindow.btnDownload.setEnabled(true);
				MainWindow.btnLaunch.setEnabled(true);


			}
		});
		btnDownload.setBounds(268, 276, 114, 106);
		frame.getContentPane().add(btnDownload);
		
		JLabel lblNewLabel_1 = new JLabel(title);
		lblNewLabel_1.setBounds(10, 251, 256, 14);
		frame.getContentPane().add(lblNewLabel_1);

	}
}
