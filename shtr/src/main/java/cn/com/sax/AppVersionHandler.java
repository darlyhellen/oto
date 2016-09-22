/**下午4:56:36
 * @author zhangyh2
 * AppVersionHandler.java
 * TODO
 */
package cn.com.sax;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;

/**
 * @author zhangyh2 AppVersionHandler 下午4:56:36 TODO
 */
public class AppVersionHandler extends
		BaseSourceHandler<List<VersionUpdateModel>> {

	Stack<VersionUpdateModel> machineStack = new Stack<VersionUpdateModel>();

	@Override
	public void start(String uri, String localName, String qName,
			Attributes attributes) {
		// TODO Auto-generated method stub
		if (VersionUpdateModel.LIST.equals(qName)) {
			result = new ArrayList<VersionUpdateModel>();
		} else if (VersionUpdateModel.LISTITEM.equals(qName)) {
			machineStack.push(new VersionUpdateModel());
		}
	}

	@Override
	public void end(String uri, String localName, String qName) {
		// TODO Auto-generated method stub
		if (VersionUpdateModel.LISTITEM.equals(qName)) {
			result.add(machineStack.pop());
		} else if (VersionUpdateModel.SOURCE.equals(qName)) {
			machineStack.peek().setSource(currentNodeText);
			currentNodeText = null;
		} else if (VersionUpdateModel.VERSION.equals(qName)) {
			machineStack.peek().setVersion(currentNodeText);
			currentNodeText = null;
		} else if (VersionUpdateModel.VERSIONNAME.equals(qName)) {
			machineStack.peek().setVersionName(currentNodeText);
			currentNodeText = null;
		} else if (VersionUpdateModel.URL.equals(qName)) {
			machineStack.peek().setUrl(currentNodeText);
			currentNodeText = null;
		} else if (VersionUpdateModel.DOWNLOADTYPE.equals(qName)) {
			machineStack.peek().setDownloadType(currentNodeText);
			currentNodeText = null;
		} else if (VersionUpdateModel.COMPARESTR.equals(qName)) {
			machineStack.peek().setCompareStr(currentNodeText);
			currentNodeText = null;
		} else if (VersionUpdateModel.DESCRIPTION.equals(qName)) {
			machineStack.peek().setDescription(currentNodeText);
			currentNodeText = null;
		} else if (VersionUpdateModel.FORCED.equals(qName)) {
			machineStack.peek().setForced(currentNodeText);
			currentNodeText = null;
		}
	}

}
