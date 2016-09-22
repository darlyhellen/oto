/**下午5:27:53
 * @author zhangyh2
 * ParserHandler.java
 * TODO
 */
package cn.com.sax;

import org.xml.sax.Attributes;

/**
 * @author zhangyh2 ParserHandler 下午5:27:53 TODO
 */
public interface ParserHandler {

	public void start(String uri, String localName, String qName,
			Attributes attributes);

	public void end(String uri, String localName, String qName);
}
