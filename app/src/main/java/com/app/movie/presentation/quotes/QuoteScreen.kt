package com.app.movie.presentation.quotes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.movie.R
import com.app.movie.data.model.Quote
import com.app.movie.presentation.widgets.CenteredTopAppBar

/**
 * Composable function to display the quote screen.
 *
 * @param navController The navigation controller to handle navigation actions.
 * @param quoteList The list of quotes to be displayed.
 */
@Composable
fun QuoteScreen(navController: NavHostController, quoteList: List<Quote>) {

    Scaffold(
        topBar = {
            // TopAppBar for the screen
            CenteredTopAppBar(
                title = stringResource(R.string.title_quote_list),
                onBackClick = {
                    navController.popBackStack()
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    items(quoteList) { quote ->
                        QuoteListItem(quote)
                    }
                }
            }
        }
    )
}
