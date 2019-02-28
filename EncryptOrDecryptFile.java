// User interface for easily encrypting your file.

package encryption;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class EncryptOrDecryptFile {

	public static void main(String[] args) throws Exception {
		window w = new window();
	}
	
}

class window{
	
	JButton Add, Encrypt, Decrypt;
	JTextField SelectedFileNameField, KeyTextField;
	
	public window() throws Exception{

		JPanel filePanel = new JPanel();
		JPanel keyPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JFrame Xframe = new JFrame("File Encrypter");
		Xframe.setLayout(new FlowLayout(FlowLayout.LEFT));
		Xframe.setSize(270, 150);
		Xframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel SelectedFileLabel = new JLabel("Filename"); 
		SelectedFileNameField = new JTextField("", 15);
		
		JLabel KeyNameLabel = new JLabel("Secret    ");
		KeyTextField = new JTextField("", 15);

		Add = new JButton("Add");
		Encrypt = new JButton("Encrypt");
		Decrypt = new JButton("Decrypt");

		
		filePanel.add(SelectedFileLabel);
		filePanel.add(SelectedFileNameField);
		keyPanel.add(KeyNameLabel);
		keyPanel.add(KeyTextField);
		buttonPanel.add(Add);
		buttonPanel.add(Encrypt);
		buttonPanel.add(Decrypt);
		
		ListenForButton lForButton = new ListenForButton();
		Add.addActionListener(lForButton);
		Encrypt.addActionListener(lForButton);
		Decrypt.addActionListener(lForButton);
		
		Xframe.add(filePanel);
		Xframe.add(keyPanel);
		Xframe.add(buttonPanel);
		Xframe.setVisible(true);
			
	}
	
	public String getFilePath() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile().getAbsolutePath();
		} else {
			return null;
		}
	}
	
	private class ListenForButton extends AES implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == Add) {
				try {
					SelectedFileNameField.setText(getFilePath());
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}else if(e.getSource() == Encrypt) {
				try {
					encryptFile(SelectedFileNameField.getText(), KeyTextField.getText());
					System.out.println("Encrypted");
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}else if(e.getSource() == Decrypt) {
				try {
					decryptFile(SelectedFileNameField.getText(), KeyTextField.getText());
					System.out.println("Decrypted");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
