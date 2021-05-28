package com.j1ang.demo.api.utils

import java.util.*


/**********************************************************
 * @author: jiangyuqing
 * @date:   2021/5/21  15:29
 * @desc:   操作時間
 **********************************************************/
open class TimeExt {
    //节假日列表
    private val holidayList: MutableList<Calendar> = mutableListOf()

    //周末为工作日
    private val weekendList: MutableList<Calendar> = ArrayList()

    open fun checkHoliday(calendar: Calendar): Boolean {
        if (calendar[Calendar.DAY_OF_WEEK] == Calendar.SUNDAY || calendar[Calendar.DAY_OF_WEEK] == Calendar.SATURDAY) {
            //判断日期是否是节假日
            for (ca in weekendList) {
                if (ca[Calendar.MONTH] == calendar[Calendar.MONTH] && ca[Calendar.DAY_OF_MONTH] == calendar[Calendar.DAY_OF_MONTH] && ca[Calendar.YEAR] == calendar[Calendar.YEAR]) {
                    return false
                }
            }
            return true
        }
        //判断日期是否是节假日
        //判断日期是否是节假日
        for (ca in holidayList) {
            if (ca[Calendar.MONTH] == calendar[Calendar.MONTH] && ca[Calendar.DAY_OF_MONTH] == calendar[Calendar.DAY_OF_MONTH] && ca[Calendar.YEAR] == calendar[Calendar.YEAR]) {
                return true
            }
        }

        return false;
    }

    fun initHolidayList(date: String) {
        val da = date.split("-".toRegex()).toTypedArray()
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = Integer.valueOf(da[0])
        calendar[Calendar.MONTH] = Integer.valueOf(da[1]) - 1 //月份比正常小1,0代表一月
        calendar[Calendar.DAY_OF_MONTH] = Integer.valueOf(da[2])
        holidayList.add(calendar)
    }


    /**
     * 初始化周末被调整为工作日的数据
     */
    fun initWeekendList(date: String) {
        val da = date.split("-".toRegex()).toTypedArray()
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = Integer.valueOf(da[0])
        calendar[Calendar.MONTH] = Integer.valueOf(da[1]) - 1 //月份比正常小1,0代表一月
        calendar[Calendar.DAY_OF_MONTH] = Integer.valueOf(da[2])
        weekendList.add(calendar)
    }


}