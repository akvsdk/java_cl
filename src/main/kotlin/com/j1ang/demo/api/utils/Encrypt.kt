package com.j1ang.demo.api.utils

import cn.hutool.core.codec.Base64
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**********************************************************
 * @author: jiangyuqing
 * @date:   2021/4/30  12:46
 * @desc:   加解密
 **********************************************************/


fun encrypt(key: String, value: String): String? {
    try {
        val secretKeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")
        val iv = IvParameterSpec(secretKeySpec.encoded)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv)
        val encrypted = cipher.doFinal(value.toByteArray())
        return Base64.encode(encrypted).toString()
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return null
}

fun decrypt(key: String, encrypted: String?): String? {
    try {
        val secretKeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")
        val iv = IvParameterSpec(secretKeySpec.encoded)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv)
        val original = cipher.doFinal(Base64.decode(encrypted))
        return String(original)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return null
}

fun generateKey(appId: Int?, appKey: String?): String {
    require(!(appId == null || appKey == null))
    return md5(appId.toString() + appKey)!!.substring(0, 16)
}

fun md5(content: String): String? {
    return try {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(content.toByteArray())
        String(encodeHex(digest.digest())!!)
    } catch (e: NoSuchAlgorithmException) {
        null
    }
}

private val DIGITS_LOWER = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

private fun encodeHex(data: ByteArray): CharArray? {
    return encodeHex(data, DIGITS_LOWER)
}

private fun encodeHex(data: ByteArray, toDigits: CharArray): CharArray? {
    val l = data.size
    val out = CharArray(l shl 1)
    // two characters form the hex value.
    var i = 0
    var j = 0
    while (i < l) {
        out[j++] = toDigits[0xF0 and data[i].toInt() ushr 4]
        out[j++] = toDigits[0x0F and data[i].toInt()]
        i++
    }
    return out
}

//    @JvmStatic
//    fun main(args: Array<String>) {
//        val appId = 1776103597
//        val appKey = "0279ca701e1c4ede8016a381750affcd"
//        val key = generateKey(appId, appKey)
//        //
////        String content = "Hello World";
////        String encryptText = encrypt(key, content);
////        System.out.println(encryptText);
//        val decryptText = decrypt(key, "28LSucSw8wODkpc9XuXrYzd+uNS1fjqJTjPpoycxPFlNzTuoQ8WaQZ6O8C6M+JNPq6hpxUvxfCJLSdOjR+7FwDnEqO4HVsHAfY+gxV/XPZ5SyXPkFjPjx4wSN9Ve+Iq1nZsylmDyaoWO2A7QJhoMX3y+4A7WEBFr/PS/REVN5rnfUvOTk64oWhk6BPUY1Wx84uWT0sKNTuMkTlbYp0QScCL+oBypqTWc7ezmVslC/THgB2pR9/YeYk3lrRqPQpFpAITRHDQL4gaenSNE8Yb6wMgLQenvp2lYkdXqznTn1uA45VoOTEBZLz34dxFYs/rUlx5WPBRSnmPRJ1nqX0QhDH0rAI4iS/GVynkANsH9rEJ17MHYBIo4uWkvDGKEtXOR+e+YMRPZetsPm+HX7vS/bxIcCyDuTTuzG7lvSp77XGPNbP1jnTKzEnLyndxEQq8EykDZUu8fb5ZtcD09jD5WZQ==")
//        println(decryptText)
//    }


