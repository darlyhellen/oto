/**上午10:10:04
 * @author zhangyh2
 * UserToken.java
 * TODO
 */
package cn.com.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @author zhangyh2 UserToken 上午10:10:04 TODO
 */
public class UserToken {

	private static final int TOKENTIME = 180;

	public static final int NULLPARAMS = 201;
	public static final int ERRORPARAMS = 202;
	public static final int OUTTOKEN = 203;
	public static final int OKTOKEN = 200;

	public static int checkToken(String token, String key) {
		if (token != null && key != null) {
			String to = new String(Base64.decode(token));
			String ke = new String(Base64.decode(key));
			if (to.contains(ke)) {
				String time = to.substring(to.length() - ke.length(),
						to.length());
				if (System.currentTimeMillis() / 1000 - Integer.parseInt(time) < TOKENTIME) {
					return OKTOKEN;
				} else {
					return OUTTOKEN;
				}
			} else {
				return ERRORPARAMS;
			}
		} else {
			return NULLPARAMS;
		}
	}
}
