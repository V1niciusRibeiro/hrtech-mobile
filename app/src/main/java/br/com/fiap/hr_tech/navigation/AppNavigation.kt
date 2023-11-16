package br.com.fiap.hr_tech.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.fiap.hr_tech.mvvm.view.component.AppHeader
import br.com.fiap.hr_tech.mvvm.view.component.SideBarMenu
import br.com.fiap.hr_tech.mvvm.view.screen.GridPayment
import br.com.fiap.hr_tech.mvvm.view.screen.WorkHoursScreen
import br.com.fiap.hr_tech.mvvm.view_model.AppHeaderViewModel
import br.com.fiap.hr_tech.mvvm.view_model.GridPaymentViewModel
import br.com.fiap.hr_tech.mvvm.view_model.WorkHoursScreenViewModel

class AppNavigation {

    companion object {
        @Composable
        fun starNavigation() {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Column {
                val appHeaderViewModel = AppHeaderViewModel()
                if (currentRoute !== AppRoutes.LOGIN_ROUTE) {
                    AppHeader(
                        AppRoutes.getScreenName(currentRoute, LocalContext.current),
                        appHeaderViewModel
                    )
                }
                Box {
                    createNavigation(navController = navController)
                    SideBarMenu(navController, appHeaderViewModel)
                }
            }
        }

        @Composable
        private fun createNavigation(navController: NavHostController) {
            NavHost(navController = navController, startDestination = AppRoutes.WORK_HOURS_ROUTE) {
                composable(route = AppRoutes.WORK_HOURS_ROUTE) {
                    WorkHoursScreen(WorkHoursScreenViewModel())
                }
                composable(route = AppRoutes.GRID_PAYMENT_ROUTE) {
                    GridPayment(GridPaymentViewModel())
                }
            }
        }
    }

}




