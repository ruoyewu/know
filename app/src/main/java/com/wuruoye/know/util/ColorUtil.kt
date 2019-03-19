package com.wuruoye.know.util

/**
 * Created at 2019/3/19 19:16 by wuruoye
 * Description:
 */
object ColorUtil {
    fun decode2ARGB(color: Int): FloatArray {
        val a = (color.shr(24) and 0xff) / 255F
        val r = (color.shr(16) and 0xff) / 255F
        val g = (color.shr( 8) and 0xff) / 255F
        val b = (color                  and 0xff) / 255F
        return floatArrayOf(a, r, g, b)
    }

    fun code2Int(a: Float, r: Float, g: Float, b: Float): Int {
        return Math.round(a).shl(24) or Math.round(r).shl(16) or
                Math.round(g).shl(8) or Math.round(b)
    }

    fun code2Hex(color: Int): String {
        val builder = StringBuilder("#")
        val array = decode2ARGB(color)
        for (f in array) {
            val s = Integer.toHexString(f.toInt())
            if (s.length == 1) builder.append(0);
            builder.append(s);
        }
        return builder.toString()
    }

    fun code2Hex(a: Float, r: Float, g: Float, b: Float): String {
        val builder = StringBuilder("#")
        val array = arrayOf(a, r, g, b)
        for (f in array) {
            val s = Integer.toHexString(f.toInt())
            if (s.length == 1) builder.append(0);
            builder.append(s);
        }
        return builder.toString()
    }
}