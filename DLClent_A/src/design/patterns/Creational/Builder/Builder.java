/**下午4:48:46
 * @author zhangyh2
 * Builder.java
 * TODO
 */
package design.patterns.Creational.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyh2 Builder 下午4:48:46 TODO
 */
public class Builder {

	private List<Paper> data = new ArrayList<Paper>();

	public void wns(int count) {
		// TODO Auto-generated method stub
		for (int i = 0; i < count; i++) {
			data.add(new WnsPaper());
		}
	}

	public void ydn(int count) {
		// TODO Auto-generated method stub
		for (int i = 0; i < count; i++) {
			data.add(new YdnPaper());
		}
	}

	public List<Paper> getData() {
		return data;
	}

	public void setData(List<Paper> data) {
		this.data = data;
	}

}
