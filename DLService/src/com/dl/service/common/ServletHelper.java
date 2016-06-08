/**上午10:12:45
 * @author zhangyh2
 * ServletHelper.java
 * TODO
 */
package com.dl.service.common;

import java.io.IOException;
import java.io.InputStream;

/** @author zhangyh2
 * ServletHelper
 * 上午10:12:45
 * TODO
 */
public class ServletHelper {

	public static final int TOKENTIME = 180;
	
	public static final byte[] readBytes(InputStream is, int contentLen) {
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			byte[] message = new byte[contentLen];
			try {
				while (readLen != contentLen) {
					readLengthThisTime = is.read(message, readLen, contentLen
							- readLen);
					if (readLengthThisTime == -1) {// Should not happen.
						break;
					}
					readLen += readLengthThisTime;
				}

				return message;
			} catch (IOException e) {
				// Ignore
				// e.printStackTrace();
			}
		}

		return new byte[] {};
	}

}
