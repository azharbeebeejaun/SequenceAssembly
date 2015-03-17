package ui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenomeAssemblyPanel extends JPanel {
	private static final long serialVersionUID = 2L;
	private JTabbedPane tabbedPane; // TODO Isolate
	private int SummaryTabIndex = 2;
	
	private JLabel lblReadsFile;
	private JTextField txtReadsFile;
	private JButton btnBrowse;

	private JPanel panelForMethodsParams;
	private JLabel lblChooseAssemblyMethods;
	private JCheckBox chckbxDeBruijnGraph;
	private JCheckBox chckbxOverlapGraph;
	private JCheckBox chckbxGreedy;
	private JCheckBox chckbxHybrid;
	private JCheckBox chckbxImprovedDeBruijn;
	private JLabel lblParameters;
	private JLabel lblKForDBG;
	private JSpinner spnKForDBG;
	private JLabel lblMinimumOverlapLength;
	private JSpinner spnMinOverlapLen;
	
	private JButton btnStartAssembly;
	
	private JScrollPane scrollPaneForLstLog;
	private JList<String> lstLog;
	
	private JButton btnNext;
	
	public GenomeAssemblyPanel(JTabbedPane tp) {
		this.tabbedPane = tp;
		this.setSize(680, 550);
		
		lblReadsFile = new JLabel("Reads File");
		
		txtReadsFile = new JTextField();
		txtReadsFile.setColumns(10);
		
		btnBrowse = new JButton("Browse");
				
		panelForMethodsParams = new JPanel();
		panelForMethodsParams.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		scrollPaneForLstLog = new JScrollPane();
		lstLog = new JList<String>();
		scrollPaneForLstLog.setViewportView(lstLog);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tabbedPane.setSelectedIndex(SummaryTabIndex);
			}
		});
	
		btnStartAssembly = new JButton("Start Assembly");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(280)
							.addComponent(btnStartAssembly))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPaneForLstLog, GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
								.addComponent(panelForMethodsParams, GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblReadsFile)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtReadsFile, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBrowse))
								.addComponent(btnNext))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReadsFile)
						.addComponent(txtReadsFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse))
					.addGap(41)
					.addComponent(panelForMethodsParams, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(btnStartAssembly)
					.addGap(38)
					.addComponent(scrollPaneForLstLog, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(btnNext)
					.addContainerGap())
		);
		
		lblChooseAssemblyMethods = new JLabel("Choose assembly methods:");
		
		chckbxDeBruijnGraph = new  JCheckBox("De Bruijn Graph");
		chckbxDeBruijnGraph.setSelected(true);

		chckbxOverlapGraph = new JCheckBox("Overlap Graph");
		chckbxOverlapGraph.setSelected(true);

		chckbxGreedy = new JCheckBox("Greedy");
		chckbxGreedy.setSelected(true);
		
		chckbxHybrid = new JCheckBox("Hybrid");
		chckbxHybrid.setSelected(true);
		
		chckbxImprovedDeBruijn = new JCheckBox("Improved De Bruijn Graph");
		chckbxImprovedDeBruijn.setSelected(true);
		
		lblParameters = new JLabel("Parameters:");
		
		lblKForDBG = new JLabel("k for De Bruijn graphs =");

		spnKForDBG = new JSpinner();
		spnKForDBG.setModel(new SpinnerNumberModel(new Integer(21), new Integer(1), null, new Integer(2)));

		lblMinimumOverlapLength = new JLabel("Minimum overlap for overlap graphs = ");
		
		spnMinOverlapLen = new JSpinner();
		spnMinOverlapLen.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		GroupLayout gl_panel = new GroupLayout(panelForMethodsParams);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxImprovedDeBruijn)
						.addComponent(chckbxOverlapGraph)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblChooseAssemblyMethods)
								.addComponent(chckbxDeBruijnGraph, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
							.addGap(153)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblParameters)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMinimumOverlapLength)
										.addComponent(lblKForDBG))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(spnMinOverlapLen, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
										.addComponent(spnKForDBG, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)))))
						.addComponent(chckbxGreedy)
						.addComponent(chckbxHybrid))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChooseAssemblyMethods)
						.addComponent(lblParameters))
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxDeBruijnGraph)
						.addComponent(lblKForDBG)
						.addComponent(spnKForDBG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxOverlapGraph)
						.addComponent(lblMinimumOverlapLength)
						.addComponent(spnMinOverlapLen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxGreedy)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxHybrid)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxImprovedDeBruijn)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panelForMethodsParams.setLayout(gl_panel);

		setLayout(groupLayout);
		
		
	}
}