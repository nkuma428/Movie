package com.app.movie.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.movie.data.model.Character
import com.app.movie.presentation.characters.CharacterDetailScreen
import com.app.movie.presentation.characters.CharactersScreen
import com.app.movie.presentation.movies.listing.MovieListScreen
import com.app.movie.presentation.movies.viewmodel.MovieListViewModel
import com.app.movie.presentation.quotes.QuoteScreen
import com.app.movie.util.AppConstants
import com.google.gson.Gson

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = AppConstants.ROUTE_MOVIE_LIST_SCREEN) {
        composable(AppConstants.ROUTE_MOVIE_LIST_SCREEN) { MovieListScreen(navController) }

        composable(
            route = "${AppConstants.ROUTE_CHARACTERS_SCREEN}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            CharactersScreen(navController, movieId.toString())
        }

        composable(
            route = "${AppConstants.ROUTE_CHARACTERS_DETAILS_SCREEN}/{character}",
            arguments = listOf(navArgument("character") { type = NavType.StringType })
        ) { backStackEntry ->
            val characterJson = backStackEntry.arguments?.getString("character")
            val character = Gson().fromJson(characterJson, Character::class.java)
            CharacterDetailScreen(navController, character)
        }

        composable(
            route = "${AppConstants.ROUTE_QUOTE_LIST_SCREEN}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            QuoteScreen(navController, movieId.toString())
        }
    }
}
