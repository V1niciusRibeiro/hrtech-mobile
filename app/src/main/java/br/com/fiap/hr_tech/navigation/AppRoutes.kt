package br.com.fiap.hr_tech.navigation

import android.content.Context
import br.com.fiap.hr_tech.R

class AppRoutes {

    companion object {

        const val LOGIN_ROUTE = "login"
        const val WORK_HOURS_ROUTE = "work_hours"

        fun getScreenName(route: String?, context: Context): String {
            if (route === WORK_HOURS_ROUTE) {
                return context.getString(R.string.work_hour_name)
            }
            return context.getString(R.string.error_404)
        }
    }

}