LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := ndkClient
LOCAL_SRC_FILES := ndkClient.cpp

include $(BUILD_SHARED_LIBRARY)
