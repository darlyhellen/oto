package cn.com.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author zhangyh2 BaseSourceHandler 下午4:31:41 TODO
 */
public abstract class BaseSourceHandler<T> extends DefaultHandler implements
		ParserHandler {

	T result = null;

	String currentNodeText = null;

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		start(uri, localName, qName, attributes);
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentNodeText = String.valueOf(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		end(uri, localName, qName);
	}

}
