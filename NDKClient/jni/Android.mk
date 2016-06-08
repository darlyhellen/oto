LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := NDKClient
LOCAL_SRC_FILES := NDKClient.cpp

include $(BUILD_SHARED_LIBRARY)
