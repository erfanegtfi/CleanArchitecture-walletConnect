package io.vaiyo.presentation.utils;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.vaiyo.R;

public final class MessageUtils {

    private MessageUtils() {

    }


    public static SweetAlertDialog showLoadingDialog(Context context) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimary));
        return dialog;
    }


    public static SweetAlertDialog showConfirmationDialog(String messgae, Context context,
                                                          SweetAlertDialog.OnSweetClickListener confirm,
                                                          SweetAlertDialog.OnSweetClickListener cancel) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        dialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimary));
        dialog.setContentText(messgae);
        dialog.setConfirmButton(context.getString(R.string.confirm), confirm);
        dialog.setCancelButton(context.getString(R.string.cancel), cancel);
        return dialog;
    }

    public static SweetAlertDialog showSuccessDialog(Context context, String content) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setContentText(content);
        dialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimary));
        dialog.getProgressHelper().setRimColor(context.getResources().getColor(R.color.colorPrimary));
        return dialog;
    }

    public static SweetAlertDialog showSuccessDialog(String content, Context context, SweetAlertDialog.OnSweetClickListener confirm) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setContentText(content);
        dialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimary));
        dialog.getProgressHelper().setRimColor(context.getResources().getColor(R.color.colorPrimary));
        dialog.setConfirmButton(context.getString(R.string.confirm), confirm);
        return dialog;
    }

    public static SweetAlertDialog showErrorDialog(Context context, String content) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setContentText(content);
        dialog.setCancelable(false);
        dialog.setConfirmButtonBackgroundColor(context.getResources().getColor(R.color.red));
        dialog.setConfirmText("dismiss");
        return dialog;
    }

}
