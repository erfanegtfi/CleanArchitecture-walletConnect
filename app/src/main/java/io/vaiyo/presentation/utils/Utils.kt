package io.vaiyo.presentation.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import io.vaiyo.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utils {

    fun formatDate(date: String?): String {
        val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val targetFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US)
        var reformattedStr = ""
        try {
            if (date != null)
                reformattedStr = targetFormat.format(sourceFormat.parse(date)!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return reformattedStr
    }

    fun formatDate2(date: String?): String {
        val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
        val targetFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US)
        var reformattedStr = ""
        try {
            if (date != null)
                reformattedStr = targetFormat.format(sourceFormat.parse(date)!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return reformattedStr
    }

    fun copyToClipboard(context: Context, text: CharSequence) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(context.getString(R.string.utils_copy_text), text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, context.getString(R.string.utils_copy_text), Toast.LENGTH_LONG).show();
    }

    fun openUrl(url: String, context: Context) {
        val customTabsIntent = CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .setShowTitle(true)
            .build()

        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

}