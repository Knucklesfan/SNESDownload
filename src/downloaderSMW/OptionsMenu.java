package downloaderSMW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTabbedPane;
import java.awt.FlowLayout;

public class OptionsMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void OptionsMenuLoad() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionsMenu window = new OptionsMenu();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public OptionsMenu() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 470, 321);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "name_131545693133300");
		
		JPanel savemanagement = new JPanel();
		tabbedPane.addTab("Save Management", null, savemanagement, null);
		savemanagement.setLayout(null);
		
		JLabel lblSaveManagement = new JLabel("SAVE MANAGEMENT");
		lblSaveManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveManagement.setBounds(10, 11, 414, 14);
		savemanagement.add(lblSaveManagement);
		
		JTextArea txtrHereAtThe = new JTextArea();
		txtrHereAtThe.setLineWrap(true);
		txtrHereAtThe.setWrapStyleWord(true);
		txtrHereAtThe.setText("Here at the Save management screen, you can delete your save, back up your save or import an external save file to play.");
		txtrHereAtThe.setBounds(10, 39, 414, 58);
		txtrHereAtThe.setEditable(false);
		savemanagement.add(txtrHereAtThe);
		
		JButton btnLoadSave = new JButton("LOAD SAVE");
		btnLoadSave.setBounds(20, 108, 123, 58);
		savemanagement.add(btnLoadSave);
		btnLoadSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Load Pushed!");
				try {
					Download.saveLoad();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File not found exception! Did you forget to pick your save?");
				}
			}
		});

		JButton btnBackupSave = new JButton("EXPORT SAVE");
		btnBackupSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Export Pushed!");
				try {
					Download.saveDump();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File not found exception! Did you forget to save your save?");
				}
			}
		});
		btnBackupSave.setBounds(301, 111, 123, 58);
		savemanagement.add(btnBackupSave);
		
		JButton btnDeleteSavewarning = new JButton("DELETE SAVE (WARNING BAD IDEA)");
		btnDeleteSavewarning.setBackground(Color.RED);
		btnDeleteSavewarning.setForeground(Color.WHITE);
		btnDeleteSavewarning.setBounds(20, 190, 404, 60);
		savemanagement.add(btnDeleteSavewarning);
		btnDeleteSavewarning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Pushed!");
				Download.delete("emulator\\Saves\\rom.srm");
			}
		});

		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Credits", null, panel, null);
		panel.setLayout(null);
		int temp = Macros.RetrieveLine("[HACKNAME]", "database.ini");
		String initName = Macros.returnString(temp, 0, "database.ini");
		temp = Macros.RetrieveLine("[HACKER]", "database.ini");
		String creator = Macros.returnString(temp, 0, "database.ini");

		JTextArea txtrSnesdownloadCreatedBy = new JTextArea();
		txtrSnesdownloadCreatedBy.setText("SNESDownload created by Knucklesfan\r\n\r\nSNESDownload is licensed under the GPLv3 license, FREE FOR ALL!\r\n\r\n" + initName + " created by " + creator);
		txtrSnesdownloadCreatedBy.setWrapStyleWord(true);
		txtrSnesdownloadCreatedBy.setLineWrap(true);
		txtrSnesdownloadCreatedBy.setBounds(0, 0, 449, 254);
		txtrSnesdownloadCreatedBy.setEditable(false);
		txtrSnesdownloadCreatedBy.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(txtrSnesdownloadCreatedBy);
	}
}
