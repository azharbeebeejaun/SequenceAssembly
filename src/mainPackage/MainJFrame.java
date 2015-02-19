package mainPackage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class MainJFrame extends JFrame {
	private static final long serialVersionUID = 0L;
	private static final String TabNameStyleStart = "<html><body marginwidth=10 marginheight=10>";
	private static final String TabNameStyleEnd = "</body></html>";
	
	public MainJFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(850, 570);
		this.setResizable(false);
		this.setTitle("Computational Methods For Genome Assembly");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		tabbedPane.addTab(TabNameStyleStart+"Reads Generator"+TabNameStyleEnd, this.new ReadsGeneratorPanel());
		tabbedPane.addTab(TabNameStyleStart+"Assembly Methods"+TabNameStyleEnd, this.new GenomeAssemblyPanel());
		tabbedPane.addTab(TabNameStyleStart+"Summary"+TabNameStyleEnd, this.new SummaryPanel());
		
		this.setVisible(true);
	}
	
	private class ReadsGeneratorPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private JTextField txtSequenceFile;
		private JLabel lblSequenceFile;
		private JButton btnBrowseInput;
		private JFileChooser fileChooser;
		private JLabel lblReadSize;
		private JLabel lblMinimumOverlapLength;
		private JLabel lblOutputFile;
		private JTextField txtOutputFile;
		private JButton btnGenerateReads;
		private JButton btnBrowseOutput;
		private JSpinner spnMinOverlapLen;
		private JSpinner spnReadSize;
		
		public ReadsGeneratorPanel() {
			this.setSize(680, 550);
			
			lblSequenceFile = new JLabel("Sequence File");
			
			txtSequenceFile = new JTextField();
			txtSequenceFile.setColumns(10);
			
			btnBrowseInput = new JButton("Browse");
			btnBrowseInput.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					fileChooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("fasta files", "fasta");
					fileChooser.setFileFilter(filter);
					int browseForInput = fileChooser.showOpenDialog(null);
					if (browseForInput == JFileChooser.APPROVE_OPTION) {
						if (fileChooser.getSelectedFile() != null && fileChooser.getSelectedFile().getAbsolutePath().endsWith(".fasta"))
							txtSequenceFile.setText(fileChooser.getSelectedFile().getAbsolutePath());
						else
							JOptionPane.showMessageDialog(null, "Sorry! Only .fasta files allowed until now.");
					} else if (browseForInput == JFileChooser.ERROR_OPTION) {
						JOptionPane.showMessageDialog(null, "Sorry! An error has occurred. Please select file again.");
						txtSequenceFile.setText("");
					}
				}
			});
			
			lblReadSize = new JLabel("Read Size");
			
			lblMinimumOverlapLength = new JLabel("Minimum Overlap Length");
			
			lblOutputFile = new JLabel("Output File");
			
			txtOutputFile = new JTextField();
			txtOutputFile.setColumns(10);
			
			btnGenerateReads = new JButton("Generate Reads");
			btnGenerateReads.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String sequenceFile = txtSequenceFile.getText();
					if (!sequenceFile.endsWith(".fasta")) {
						JOptionPane.showMessageDialog(null, "Sequence file should be in fasta format.");
						return;
					}
					int readSize = (int) spnReadSize.getValue();
					int minOverlap = (int) spnMinOverlapLen.getValue();
					String readsFile = txtOutputFile.getText();
					if (!readsFile.endsWith(".fasta")) {
						JOptionPane.showMessageDialog(null, "Reads file should be in fasta format.");
						return;
					}
					JOptionPane.showMessageDialog(null, ReadsGenerator.generateReads(sequenceFile, readSize, minOverlap, readsFile));
				}
			});
			
			btnBrowseOutput = new JButton("Browse");
			btnBrowseOutput.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fileChooser.setAcceptAllFileFilterUsed(false);
					int browseForOutput = fileChooser.showSaveDialog(null);
					if (browseForOutput == JFileChooser.APPROVE_OPTION) {
						txtOutputFile.setText(fileChooser.getSelectedFile().getAbsolutePath() + "\\generatedReads.fasta");
					} else if (browseForOutput == JFileChooser.ERROR_OPTION) {
						
					}
				}
			});
			
			spnMinOverlapLen = new JSpinner();
			spnMinOverlapLen.setModel(new SpinnerNumberModel(new Integer(10), new Integer(0), null, new Integer(1)));
			
			spnReadSize = new JSpinner();
			spnReadSize.setModel(new SpinnerNumberModel(new Integer(200), new Integer(0), null, new Integer(1)));
			GroupLayout groupLayout = new GroupLayout(this);
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(53)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(228)
								.addComponent(btnGenerateReads))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblSequenceFile)
									.addComponent(lblReadSize)
									.addComponent(lblOutputFile))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(txtSequenceFile, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(spnReadSize, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
										.addGap(67)
										.addComponent(lblMinimumOverlapLength)
										.addGap(18)
										.addComponent(spnMinOverlapLen, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
									.addComponent(txtOutputFile, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnBrowseInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBrowseOutput))
						.addGap(53))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(191)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSequenceFile)
							.addComponent(txtSequenceFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBrowseInput))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblReadSize)
								.addComponent(spnMinOverlapLen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMinimumOverlapLength))
							.addComponent(spnReadSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblOutputFile)
							.addComponent(txtOutputFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBrowseOutput))
						.addGap(43)
						.addComponent(btnGenerateReads)
						.addContainerGap(191, Short.MAX_VALUE))
			);
			setLayout(groupLayout);
		}
	}
	
	private class GenomeAssemblyPanel extends JPanel {
		private static final long serialVersionUID = 2L;
		
		public GenomeAssemblyPanel() {
		}
	}
	
	private class SummaryPanel extends JPanel {
		private static final long serialVersionUID = 3L;
		
		public SummaryPanel() {
		}
	}
}
