package com.nibatech.ecmd.common.preferences;


import android.content.Context;

import com.google.gson.Gson;
import com.nibatech.ecmd.common.bean.guide.GuideStartTreatmentBean;

import static com.j256.ormlite.field.DatabaseField.DEFAULT_STRING;

public class PaymentSharePreferences extends BaseSharedPreferences {
    private static final String KEY_APIS = "key.payment";

    public static void save(Context context, GuideStartTreatmentBean treatmentBean) {
        put(context, KEY_APIS, new Gson().toJson(treatmentBean));
    }

    public static GuideStartTreatmentBean get(Context context) {
        String string = (String) get(context, KEY_APIS, DEFAULT_STRING);
        GuideStartTreatmentBean treatmentBean = null;
        if (!(string != null && string.isEmpty())) {
            try {
                treatmentBean = new Gson().fromJson(string, GuideStartTreatmentBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return treatmentBean;
    }
}
