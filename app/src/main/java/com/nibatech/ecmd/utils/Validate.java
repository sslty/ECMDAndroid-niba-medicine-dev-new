package com.nibatech.ecmd.utils;

import android.text.TextUtils;



public class Validate {
    /**
     * 验证手机格式
     */
    public static boolean isMobileNOValid(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegEx = "[1][3578]\\d{9}";
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，
        // "\\d{9}"代表后面是可以是0～9的数字，有9位。

        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegEx);
        }

    }

    /**
     * 验证密码
     */
    public static boolean isPasswordValid(String passwords) {
        //String pwdRegEx = "/^(?=.{6,16}$)(?![0-9]+$)(?!.*(.).*\\1)[0-9a-zA-Z]+$/";
        //return passwords.matches(pwdRegEx);

        /*
        长度6-16位
        不能为同一个字符
        不能全是数字
        只能有数字、字母和常用特殊字符
        */

        return passwords.length() > 0;
    }

    /**
     * 验证两次密码是否一致
     */
    public static boolean isMatchValid(String first, String second) {
        return first.compareTo(second) == 0;
    }

    /**
     * 验证年龄信息
     */
    public static boolean isAgeValid(String age) {
        return Integer.parseInt(age) > 1 && Integer.parseInt(age) < 100;
    }

}
