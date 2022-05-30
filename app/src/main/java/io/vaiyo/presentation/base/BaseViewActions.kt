package io.vaiyo.presentation.base;

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.snackbar.Snackbar
import io.vaiyo.domain.utils.GeneralError
import io.vaiyo.presentation.utils.MessageUtils

object BaseViewActions {


    fun getSweetAlertDialog(context: Context): SweetAlertDialog {
        return MessageUtils.showLoadingDialog(context)
    }

    fun showLoading(mProgressDialog: SweetAlertDialog) {
        mProgressDialog.show()
    }

    fun hideLoading(mProgressDialog: SweetAlertDialog) {
        if (mProgressDialog.isShowing) {
            mProgressDialog.cancel()
            mProgressDialog.dismissWithAnimation()
        }
    }

    fun onMessageToast(context: Context, error: GeneralError) {
        if (error.message != null)
            onMessageToast(context, error.message)
        else if (error.messageRes != null && error.messageRes != 0)
            onMessageToast(context, error.messageRes!!)
    }

    fun onMessageToast(context: Context, message: Int) {
        onMessageToast(context, context.getString(message))
    }

    fun onMessageSnackbar(context: Context, message: Int) {
        onMessageSnackbar(context, context.getString(message))
    }

    fun onMessageSnackbar(context: Context, error: GeneralError) {
        if (error.message != null)
            onMessageSnackbar(context, error.message)
        else if (error.messageRes != null && error.messageRes != 0)
            onMessageSnackbar(context, error.messageRes!!)
    }

    fun onMessageSnackbar(context: Context, message: String?) {
        if (message != null)
            Snackbar.make(View(context), message, Snackbar.LENGTH_LONG).show()
    }


    fun onMessageToast(context: Context, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun onErrorDialog(context: Context, error: GeneralError) {

        val message = if (error.message != null)
            error.message
        else if (error.messageRes != null && error.messageRes != 0)
            context.getString(error.messageRes!!)
        else ""

        val mProgressDialog = MessageUtils.showErrorDialog(context, message)
        mProgressDialog!!.show()
    }

    fun onErrorDialog(context: Context, message: String?) {
        if (message != null) {
            val mProgressDialog = MessageUtils.showErrorDialog(context, message)
            mProgressDialog!!.show()
        }
    }

    fun onSuccessDialog(context: Context, message: String?) {
        if (message != null) {
            val mProgressDialog = MessageUtils.showSuccessDialog(context, message)
            mProgressDialog!!.show()
        }
    }

}
