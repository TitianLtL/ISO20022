package UI;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import generator.BuildData;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.xml.bind.JAXBException;

import load.csv.ItemReader;
import sign.SignFile;
import sign.XmlSigningBuilder;
import sign.vo.SignVo;
import util.DateUtil;
import util.KeyWordUtil;
import vo.CreditVo;
import vo.CreditsVo;
import vo.DebitVo;
import vo.FormVo;
import wrap.WrapFileBuilder;
import wrap.vo.ApplicationVO;

public class LocalFileToolUI extends JFrame {

	private static final long serialVersionUID = -5716012438481408611L;
	private final JFileChooser fileChooser = new JFileChooser();
	private KeyWordUtil keyWord = new KeyWordUtil();
	private static final String END_TO_END_ID_PREFIX = "eteid-";
	private static final String PAYMENT_INF_PREFIX = "piid-";
	private static final String MESSAGE_ID_PREFIX = "mgid-";
	private static final String INSTRUCTION_ID_PREFIX = "instr-";

	private JTextArea fileTextArea = new JTextArea();
	private JLabel headerLabel = new JLabel();
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JPanel jPanel3 = new JPanel();
	private JPanel jPanel4 = new JPanel();
	private JScrollPane jScrollPane1 = new JScrollPane();
	private JButton openButton = new JButton("Open");

	private JLabel[] certificateInfor = { new JLabel("Certificate:"), new JLabel("Certificate PIN:"),
			new JLabel("Signer Id:"), new JLabel("Sender Id:"), new JLabel("Execution Date:") };
	private JTextField[] certTextS = { new JTextField(), new JTextField(), new JTextField("customer id"),
			new JTextField(keyWord.getSignerId()),
			new JTextField(DateUtil.getXMLGregorianCalendarNextDate(new Date(), 1)) };

	private JLabel[] prefixs = { new JLabel("MessageId:"), new JLabel("PaymentId:"), new JLabel("Instruction:"),
			new JLabel("End to End Id:") };
	private JTextField[] prefixsTexts = { new JTextField(MESSAGE_ID_PREFIX), new JTextField(PAYMENT_INF_PREFIX),
			new JTextField(INSTRUCTION_ID_PREFIX), new JTextField(END_TO_END_ID_PREFIX) };
	private int MAX_PREFERRED_SIZE = 120;

	public LocalFileToolUI() throws IOException {
		initComponents();
	}

	private void initComponents() throws IOException {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Local  File Tool");
		setPreferredSize(new java.awt.Dimension(1200, 750));
		fileTextArea.setColumns(20);
		fileTextArea.setRows(5);
		// URL iconUrl = new ClassPathResource("header.png").getURL();
		// headerLabel.setIcon(new javax.swing.ImageIcon(iconUrl)); // NOI18N

		jScrollPane1.setViewportView(fileTextArea);
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		openButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openButtonActionPerformed(evt);
			}
		});
		// panel1
		GroupLayout jPanelL1 = new GroupLayout(jPanel1);
		final SequentialGroup hSequentialG1 = jPanelL1.createSequentialGroup();
		hSequentialG1.addContainerGap();
		for (int i = 0; i < prefixs.length; i++) {
			hSequentialG1.addComponent(prefixs[i], PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE);
			hSequentialG1.addGap(5, 5, 5);
			hSequentialG1.addComponent(prefixsTexts[i], PREFERRED_SIZE, MAX_PREFERRED_SIZE, PREFERRED_SIZE);
			hSequentialG1.addGap(10, 10, 10);
		}
		hSequentialG1.addComponent(openButton, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE);
		hSequentialG1.addContainerGap();
		jPanelL1.setHorizontalGroup(jPanelL1.createParallelGroup(Alignment.LEADING).addGroup(hSequentialG1));
		jPanel1.setLayout(jPanelL1);

		final SequentialGroup vSequentialG1 = jPanelL1.createSequentialGroup();
		final ParallelGroup parallelG1 = jPanelL1.createParallelGroup(Alignment.BASELINE);
		for (int i = 0; i < prefixs.length; i++) {
			parallelG1.addComponent(prefixs[i], PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE);
			parallelG1.addComponent(prefixsTexts[i], PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE);
		}

		parallelG1.addComponent(openButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE);

		vSequentialG1.addContainerGap().addGroup(parallelG1);
		vSequentialG1.addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE);

		jPanelL1.setVerticalGroup(jPanelL1.createParallelGroup(Alignment.LEADING).addGroup(vSequentialG1));
		// panel2
		jPanel2.setBorder(BorderFactory.createEtchedBorder());
		GroupLayout jPanelL2 = new GroupLayout(jPanel2);
		final SequentialGroup hSequentialG2 = jPanelL2.createSequentialGroup();
		hSequentialG2.addContainerGap();
		for (int i = 0; i < certificateInfor.length; i++) {
			hSequentialG2.addComponent(certificateInfor[i], PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE);
			hSequentialG2.addGap(5, 5, 5);
			hSequentialG2.addComponent(certTextS[i], PREFERRED_SIZE, MAX_PREFERRED_SIZE, PREFERRED_SIZE);
			hSequentialG2.addGap(10, 10, 10);
		}
		hSequentialG2.addContainerGap();
		jPanelL2.setHorizontalGroup(jPanelL2.createParallelGroup(Alignment.LEADING).addGroup(hSequentialG2));
		jPanel2.setLayout(jPanelL2);

		final SequentialGroup vSequentialG2 = jPanelL2.createSequentialGroup();
		final ParallelGroup parallelG2 = jPanelL2.createParallelGroup(Alignment.BASELINE);
		for (int i = 0; i < certificateInfor.length; i++) {
			parallelG2.addComponent(certificateInfor[i], PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE);
			parallelG2.addComponent(certTextS[i], PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE);
		}

		vSequentialG2.addContainerGap().addGroup(parallelG2);
		vSequentialG2.addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE);

		jPanelL2.setVerticalGroup(jPanelL2.createParallelGroup(Alignment.LEADING).addGroup(vSequentialG2));

		// set layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.createSequentialGroup();
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(headerLabel, GroupLayout.PREFERRED_SIZE, 0,
														Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(layout.createSequentialGroup()
												.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(layout.createSequentialGroup()
												.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addComponent(jPanel4, GroupLayout.Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(jScrollPane1, GroupLayout.Alignment.LEADING)
														.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addContainerGap()))));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addComponent(headerLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(19, Short.MAX_VALUE)));
		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_openButtonActionPerformed
		int returnVal = fileChooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				loadFile(file);
			} catch (Exception e) {
				showMesssage("An error occurred opening file. Please check console window for details.");
				e.printStackTrace();
			}
		}
	}// GEN-LAST:event_openButtonActionPerformed

	private void loadFile(File file) {

		try {
			List<String> lines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
			CreditsVo credits = storeCreditVo(lines);
			FormVo formVo = new FormVo(certTextS, prefixsTexts);
			buildData(formVo, new DebitVo(), credits);
			wrapData(formVo);
			signData(formVo);
		} catch (Exception e) {
			showMesssage("An error occurred: " + e.getMessage());
		}
	}

	private CreditsVo storeCreditVo(List<String> lines) {
		CreditsVo totalCredits = new CreditsVo();
		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line).append("\n");
			totalCredits.addCreditVo(ItemReader.reader(line));
		}
		showMesssage("Read infor is done.");
		return totalCredits;
	}

	private void buildData(FormVo formVo, DebitVo debit, CreditsVo credits) throws Exception {
		BuildData bd = new BuildData();
		bd.bulidFile(formVo, debit, credits);
		showMesssage("Build file is done.");
	}

	private void wrapData(FormVo formVo) throws Exception {
		WrapFileBuilder builder = new WrapFileBuilder();
		ApplicationVO fileVo = new ApplicationVO();
		fileVo.setCustomerId(formVo.getSenderId());
		fileVo.setTargetId(formVo.getSignId());
		fileVo.setTimestamp(formVo.getExecutionDate());
		builder.builder(fileVo);
		showMesssage("Wrap file is done.");
	}

	private void signData(FormVo formVo) throws Exception {
		SignFile sf = new SignFile();
		XmlSigningBuilder builder = new XmlSigningBuilder();
		String password = formVo.getPin();
		String storeFolder = keyWord.getStoreFolder();
		String fileToBeSignedPath = storeFolder + keyWord.getWrapFileExe();
		String fileSignedPath = storeFolder + keyWord.getSignedFileExe();
		SignVo vo = builder.signbuilder(fileToBeSignedPath, password);
		sf.signXml(vo);
		sf.writeResultingDocument(vo.getDoc(), fileSignedPath);
		showMesssage("Sign file is done. Get your file in folder " + fileSignedPath);
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LocalFileToolUI().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	final static String newline = "\n";

	public void showMesssage(String message) {
		fileTextArea.append(newline + DateUtil.getXMLGregorianCalendarTime(new Date()) + " -- " + message);
	}

}
