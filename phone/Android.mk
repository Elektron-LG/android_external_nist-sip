LOCAL_PATH:= $(call my-dir)

## Static library with some common classes for the phone apps.
## To use it add this line in your Android.mk
##  LOCAL_STATIC_JAVA_LIBRARIES := com.android.phone.common
#include $(CLEAR_VARS)
#
#LOCAL_MODULE_TAGS := user
#
#LOCAL_SRC_FILES := \
#	src/com/android/phone/ButtonGridLayout.java \
#	src/com/android/phone/CallLogAsync.java \
#	src/com/android/phone/HapticFeedback.java
#
#LOCAL_MODULE := com.android.phone.common
#include $(BUILD_STATIC_JAVA_LIBRARY)

# Build the Phone app which includes the emergency dialer. See Contacts
# for the 'other' dialer.
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_SRC_FILES += \
        src/com/android/phone2/EventLogTags.logtags \
        src/com/android/phone2/INetworkQueryService.aidl \
        src/com/android/phone2/INetworkQueryServiceCallback.aidl

LOCAL_SRC_FILES += $(call all-java-files-under, src2)

LOCAL_PACKAGE_NAME := Phone2
LOCAL_CERTIFICATE := platform
LOCAL_STATIC_JAVA_LIBRARIES := android.sip

include $(BUILD_PACKAGE)

## Build the test package
#include $(call all-makefiles-under,$(LOCAL_PATH))