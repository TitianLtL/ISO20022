package wrap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.codec.binary.Base64;

import util.AbstractKeyWord;
import wrap.vo.ApplicationVO;

public class WrapFileBuilder extends AbstractKeyWord {

	@Deprecated
	public static void main(String[] args) throws Exception {
		WrapFileBuilder b = new WrapFileBuilder();
		b.builder();

	}

	@Deprecated
	public void builder() throws Exception {
		byte[] data = readerFromFile();
		ApplicationVO fileVo = new ApplicationVO();
		writerToFile(data, fileVo);

	}

	public void builder(ApplicationVO fileVo) throws Exception {
		byte[] data = readerFromFile();
		writerToFile(data, fileVo);

	}

	protected byte[] readerFromFile() throws IOException{
		String folder = keyWord.getStoreFolder();
		File file = new File(folder);
		byte[] bFile = new byte[(int) file.length()];
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} finally {
			fileInputStream.close();
		}
		return bFile;
	}

	protected void writerToFile(byte[] bFile, ApplicationVO fileVo) throws IOException {
		FileOutputStream fileOuputStream = null;
		String folder = keyWord.getStoreFolder() + keyWord.getWrapFileExe();
		byte[] headerInfo = headerInfoBuilder(fileVo);
		try {
			fileOuputStream = new FileOutputStream(folder);
			fileOuputStream
					.write("<ApplicationRequest xmlns=\"http://bxd.fi/xmldata/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance/\" >\n"
							.getBytes());
			fileOuputStream.write(headerInfo);
			fileOuputStream.write("<Content>\n".getBytes());
			fileOuputStream.write(encode(bFile));
			fileOuputStream.write("</Content>\n".getBytes());
			fileOuputStream.write("</ApplicationRequest>".getBytes());
		}finally {
			fileOuputStream.close();
		}
	}

	protected byte[] headerInfoBuilder(ApplicationVO fileVO) {
		StringBuilder sb = new StringBuilder();
		sb.append("<CustomerId>").append(fileVO.getCustomerId()).append("</CustomerId>\n").append("<Command>")
				.append(fileVO.getCommand()).append("</Command>\n").append("<Timestamp>").append(fileVO.getTimestamp())
				.append("</Timestamp>\n").append("<Environment>").append(fileVO.getEnvironment())
				.append("</Environment>\n").append("<TargetId>").append(fileVO.getTargetId()).append("</TargetId>\n")
				.append("<SoftwareId>").append(fileVO.getSoftwareId()).append("</SoftwareId>\n").append("<FileType>")
				.append(fileVO.getFileType()).append("</FileType>\n");
		return sb.toString().getBytes();
	}

	public byte[] encode(byte[] bFile) {
		byte[] encodeByte = Base64.encodeBase64(bFile);
		return encodeByte;
	}

}
