package kg.asylbekov.litecleaner.utils.extensions

import android.content.Context
import kg.asylbekov.litecleaner.R

class UserManager() {

    companion object {
        const val FIRST_OPEN = "first_open"
        const val PRIVACY_POLICY = "privacy_policy"
    }

}

fun saveFirstOpen(context: Context, flag: Boolean) {
    val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
    editor.putBoolean(UserManager.FIRST_OPEN, flag)
    editor.apply()
}

fun getFirstOpen(context: Context): Boolean {
    return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).getBoolean(UserManager.FIRST_OPEN, true)
}

fun saveAcceptPV(context: Context, flag: Boolean) {
    val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
    editor.putBoolean(UserManager.PRIVACY_POLICY, flag)
    editor.apply()
}

fun getAcceptPV(context: Context): Boolean {
    return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).getBoolean(UserManager.PRIVACY_POLICY, false)
}

