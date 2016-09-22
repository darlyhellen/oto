/**下午4:30:31
 * @author zhangyh2
 * SaxParser.java
 * TODO
 */
package cn.com.sax;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author zhangyh2 SaxParser 下午4:30:31 TODO
 */
public class SaxParser {
	XMLReader reader = null;

	/**
	 * 下午2:55:45
	 * 
	 * @author zhangyh2
	 * @throws SAXException
	 */
	public SaxParser() throws SAXException {
		// TODO Auto-generated constructor stub
		reader = XMLReaderFactory.createXMLReader();
	}

	public ParserHandler getHandlers(String type, String xmlFilePath)
			throws Exception {
		if (type.equals("version")) {
			AppVersionHandler handler = new AppVersionHandler();
			reader.setContentHandler(handler);
			reader.parse(xmlFilePath);
			return handler;
		} else if (type.equals("test")) {
			ResourceHandler handler = new ResourceHandler();
			reader.setContentHandler(handler);
			reader.parse(xmlFilePath);
			return handler;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.sax.MachineParser#getXMLLines(java.lang.String)
	 */
	public int getXMLLines(String xmlFilePath) throws IOException {
		// TODO Auto-generated method stub
		InputStream is = new BufferedInputStream(new FileInputStream(
				xmlFilePath));
		byte[] c = new byte[1024];
		int count = 0;
		int readChars = 0;
		while ((readChars = is.read(c)) != -1) {
			for (int i = 0; i < readChars; ++i) {
				if (c[i] == '\n')
					++count;
			}
		}
		is.close();
		return count;
	}

}
