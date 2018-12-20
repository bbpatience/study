package com.walle.concurrent.streamline;

/**
 * @author: bbpatience
 * @date: 2018/12/20
 * @description: Msg
 **/
public class Msg {
    public double i;
    public double j;
    public String orgStr = null;

    public Msg(double i, double j, String orgStr) {
        this.i = i;
        this.j = j;
        this.orgStr = orgStr;
    }
}
