package com.j1ang.demo.api.bean;

/**********************************************************
 * @author: jiangyuqing
 * @date: 2021/5/21  16:47
 * @desc:
 **********************************************************/
public class HolidayBean {

    private String name;
    private String date;
    private Boolean isOffDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getIsOffDay() {
        return isOffDay;
    }

    public void setIsOffDay(Boolean isOffDay) {
        this.isOffDay = isOffDay;
    }
}
