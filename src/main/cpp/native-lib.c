#include <jni.h>
#include<stdio.h>
#include<stdlib.h>
#include <string.h>



char* _JString2CStr(JNIEnv* env, jstring jstr) {
    char* rtn;
    jclass clsstring = (*env)->FindClass(env, "java/lang/String");
    jstring strencode = (*env)->NewStringUTF(env, "GB2312");
    jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes",
                                        "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray)(*env)->CallObjectMethod(env, jstr, mid,
                                                           strencode); // String .getByte("GB2312");
    jsize alen = (*env)->GetArrayLength(env, barr);
    jbyte* ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
    if (alen > 0) {
        rtn = (char*) malloc(alen + 1); //"\0"
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    (*env)->ReleaseByteArrayElements(env, barr, ba, 0);
    return rtn;
}

char* storeKey = "1568754835";

JNIEXPORT jstring JNICALL Java_com_mecby_base_net_NativeHelper_getAppSecurity(
        JNIEnv *env, jobject obj, jbyteArray array) {
    // 反射获取APP的签名哈希值
    jclass jClazz = (*env)->FindClass(env, "com/mecby/base/net/NativeHelper");
    jmethodID jmethodid = (*env)->GetMethodID(env, jClazz, "getSignature",
                                              "()Ljava/lang/String;");
    jstring appSign = (jstring)(*env)->CallObjectMethod(env, obj, jmethodid);

    // 判断是否是本程序的签名哈希值，如果是进行加密
    char* charAppSign = _JString2CStr(env, appSign); //将jstring转换为cha*
    if (strcmp(charAppSign, storeKey) != 0) {
        return (*env)->NewStringUTF(env, "");//(*env)->GetByteArrayElements(env, array, NULL);
    }
    return (*env)->NewStringUTF(env, "04ecf46450224f41bd3805a7b19c1629");
}
