package com.app.movie.presentation.nav

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.movie.data.model.Character
import com.app.movie.data.model.Movie
import com.app.movie.data.model.Quote
import com.app.movie.presentation.characters.CharacterDetailScreen
import com.app.movie.presentation.characters.CharactersScreen
import com.app.movie.presentation.movies.listing.MovieListScreen
import com.app.movie.presentation.quotes.QuoteScreen
import com.app.movie.util.AppConstants
import com.google.gson.Gson

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = AppConstants.ROUTE_MOVIE_LIST_SCREEN) {
        composable(AppConstants.ROUTE_MOVIE_LIST_SCREEN) { MovieListScreen(navController) }

        composable(
            route = "${AppConstants.ROUTE_CHARACTERS_SCREEN}/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movie").orEmpty()
            val movieItem = Gson().fromJson(movieJson, Movie::class.java)
            CharactersScreen(navController, movieItem)
        }

        composable(
            route = "${AppConstants.ROUTE_CHARACTERS_DETAILS_SCREEN}/{character}/{quoteList}",
            arguments = listOf(
                navArgument("character") { type = NavType.StringType },
                navArgument("quoteList") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val characterJson = backStackEntry.arguments?.getString("character")
            val character = Gson().fromJson(characterJson, Character::class.java)
            val quoteListJson = backStackEntry.arguments?.getString("quoteList").orEmpty()
            val quoteList = Gson().fromJson(Uri.decode(quoteListJson), Array<Quote>::class.java).toList()

            CharacterDetailScreen(navController, character, quoteList)
        }

        composable(
            route = "${AppConstants.ROUTE_QUOTE_LIST_SCREEN}/{quoteList}",
            arguments = listOf(navArgument("quoteList") { type = NavType.StringType })
        ) { backStackEntry ->
            val quoteListJson = backStackEntry.arguments?.getString("quoteList").orEmpty()
            val quoteList = Gson().fromJson(Uri.decode(quoteListJson), Array<Quote>::class.java).toList()
            QuoteScreen(navController, quoteList)
        }
    }
}
