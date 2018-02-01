/*
 * Copyright (c) 2015 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nibatech.ecmd.common.contact;


import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.utils.HanziToPinyin;

public class Contact implements Comparable<Contact> {
    private String pinyin;
    private char firstChar;
    private String chatUrl;
    private DoctorProfileBean doctorProfile;

    private String getPinyin() {
        return pinyin;
    }

    private void setPinyin(String text) {
        this.pinyin = HanziToPinyin.getPinYin(text);
        String first = pinyin.substring(0, 1);
        if (first.matches("[A-Za-z]")) {
            firstChar = first.toUpperCase().charAt(0);
        } else {
            firstChar = '#';
        }
    }

    public char getFirstChar() {
        return firstChar;
    }

    public DoctorProfileBean getDoctorProfile() {
        return doctorProfile;
    }

    public void setDoctorProfile(DoctorProfileBean doctorProfile, String chatUrl) {
        this.doctorProfile = doctorProfile;
        this.chatUrl = chatUrl;
        setPinyin(doctorProfile.getFullName());
    }

    public String getChatUrl() {
        return chatUrl;
    }

    @Override
    public int compareTo(Contact another) {
        return this.pinyin.compareTo(another.getPinyin());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Contact) {
            return this.getDoctorProfile().getCdNumber().equals(((Contact) o).getDoctorProfile().getCdNumber());
        } else {
            return super.equals(o);
        }
    }
}
