#include<jni.h>
#include<com_darly_ndkclient_MainActivity.h>

JNIEXPORT jstring JNICALL Java_com_darly_ndkclient_MainActivity_getNDKStr(
		JNIEnv * env, jobject obj) {
	return env->NewStringUTF("Hello from JNI !  Compiled with ABI ");
}
