/**下午4:10:16
 * @author zhangyh2
 * BaseListener.java
 * TODO
 */
package com.hellen.base;

/**
 * @author zhangyh2 BaseListener 下午4:10:16 TODO
 */
public interface BaseListener<T> {

	void onSucces(T result);

	void onFaild(int code, String info);
}
