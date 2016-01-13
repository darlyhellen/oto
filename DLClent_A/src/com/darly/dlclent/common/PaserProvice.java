/**下午4:13:46
 * @author zhangyh2
 * PaserProvice.java
 * TODO
 */
package com.darly.dlclent.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.darly.dlclent.model.CityModel;
import com.darly.dlclent.model.DistrictModel;
import com.darly.dlclent.model.ProvinceModel;

/**
 * @author zhangyh2 PaserProvice 下午4:13:46 TODO 解析assets目录下的省市区
 */
public class PaserProvice {

	public static List<ProvinceModel> paserPro(InputStream xml) {
		List<ProvinceModel> provinces = null;
		List<CityModel> citys = null;
		List<DistrictModel> districts = null;
		XmlPullParser pullParser = Xml.newPullParser();
		try {
			String p = null;
			String c = null;
			pullParser.setInput(xml, "UTF-8");// 为PULL解析器设置要解析的XML数据
			int event = pullParser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					provinces = new ArrayList<ProvinceModel>();
					citys = new ArrayList<CityModel>();
					districts = new ArrayList<DistrictModel>();
					break;
				case XmlPullParser.START_TAG:
					if ("province".equals(pullParser.getName())) {
						p = new String(pullParser.getAttributeValue(0));
					}
					if ("city".equals(pullParser.getName())) {
						c = new String(pullParser.getAttributeValue(0));
					}
					if ("district".equals(pullParser.getName())) {
						String d = new String(pullParser.getAttributeValue(0));
						String z = new String(pullParser.getAttributeValue(1));
						districts.add(new DistrictModel(d, z));
					}
					break;
				case XmlPullParser.END_TAG:
					if ("province".equals(pullParser.getName())) {
						provinces.add(new ProvinceModel(p, citys));
						citys = new ArrayList<CityModel>();
					}
					if ("city".equals(pullParser.getName())) {
						citys.add(new CityModel(c, districts));
						districts = new ArrayList<DistrictModel>();
					}
					break;
				}
				event = pullParser.next();
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return provinces;
	}

}
