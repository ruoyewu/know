package com.wuruoye.know.util

import android.util.Base64


/**
 * Created at 2019/4/1 11:12 by wuruoye
 * Description:
 */
object CodeUtil {
    fun encodeBase64(text: String): String {
        return Base64.encodeToString(text.toByteArray(), Base64.DEFAULT);
    }

    fun decodeBase64(text: String): String {
        return String(Base64.decode(text, Base64.DEFAULT))
    }
}