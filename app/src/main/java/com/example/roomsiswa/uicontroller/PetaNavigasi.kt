package com.example.roomsiswa.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomsiswa.uicontroller.DestinasiDetailSiswa.itemIdArg
import com.example.roomsiswa.view.DetailSiswaScreen
import com.example.roomsiswa.view.EditSiswaScreen
import com.example.roomsiswa.view.EntrySiswaScreen
import com.example.roomsiswa.view.HomeScreen

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    HostNavigasi(navController = navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = DestinasiDetailHome.route, modifier = Modifier)
    {
        composable(DestinasiDetailHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                //edit 1 : tambahkan parameter navigateToItemUpdate
                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetailSiswa.route}/$it")
                }
            )
        }
        composable(DestinasiEntry.route){
            EntrySiswaScreen(navigateBack = { navController.popBackStack()})
        }
        //edit 2 : tambahkan 2 composable route
        composable(route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(itemIdArg) {
                type = NavType.IntType
            })
        ){
            DetailSiswaScreen(
                navigateToEditItem = {navController.navigate("${DestinasiEditSiswa.route}/$it")},
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditSiswa.itemIdArg){
                type = NavType.IntType})) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack()},
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}