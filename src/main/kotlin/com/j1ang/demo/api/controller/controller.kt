package com.j1ang.demo.api.controller

import cn.hutool.http.HttpUtil
import cn.hutool.json.JSONUtil
import com.j1ang.demo.api.bean.Bean
import com.j1ang.demo.api.scheduled.ScheduledTask
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.concurrent.atomic.AtomicLong


/**********************************************************
 * @author: jiangyuqing
 * @date:   2021/4/30  11:26
 * @desc:   控制器
 **********************************************************/

@RestController
class GreetingController {

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(defaultValue = "World") name: String) =
        Bean(counter.incrementAndGet(), "Hello, $name")


    @GetMapping("/encrypt")
    fun encrypt(
        @RequestParam(defaultValue = "1776103597") appId: Int,
        @RequestParam(defaultValue = "0279ca701e1c4ede8016a381750affcd") appKey: String,
        @RequestParam(name = "encrypted") encrypted: String
    ) = run {
        val key = com.j1ang.demo.api.utils.generateKey(appId, appKey)
//        val encrypt = "ztcrasW+v/uPJ+lGvZLu3nJwdqnT7TXYn2mbz+PKJ14SbZvEK/NqwKjL0SFVjhu5X1VBGlaouq0Nzyi+A7wAmVOr7a7Y/SddhcdKxcTkZnuCJ59dIdlVyuWIU3rowQO3hQJt0CBVlPMYmnYXI2lR37brfujcPcn3h7THlvSO8rr5ujoxglwVnxL8q4CJVOC1E7BJjv7GV5BE4NYlWwTNRyFhHNU0ZTlXJhc5eJlEznKg/xBTzqKVAO6eI3mLcGoN0yb2YFszjbl++wnUWf4bsITdDd09xmXDHN/9cL6SKX66MivN2VBif2pBNf0HOalZ"
//        val temp = URLEncoder.encode(encrypted, "UTF-8")
        var result = ""
        try {
            val temp = encrypted
            println(temp)
            val decryptText = com.j1ang.demo.api.utils.decrypt(key, temp)
            println(decryptText)
            result = decryptText!!
        } catch (ex: Exception) {
            println(ex.message)
            result = ex.message!!
        }
        result

    }

    @GetMapping("/qq")
    fun qq(
        @RequestParam() c: String,
        @RequestParam(required = false) t: String,
    ) = run {
        val paramMap = HashMap<String, Any>()
        paramMap["c"] = c
        paramMap["t"] = t
        val result =
            HttpUtil.get("https://push.xuthus.cc/group/8dc2c851e3ca5b02c43e40af008105f1", paramMap)
        result
    }


    @GetMapping("/getHoliday")
    fun getHoliday() = run {
        val holiday: String = ScheduledTask.getFileJson()
        val array = JSONUtil.parseArray(holiday)
        println(array.toStringPretty())
        array
    }

}
