package com.app.movie.presentation.quotes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.movie.R
import com.app.movie.data.model.Quote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(navController: NavHostController, quoteList: List<Quote>) {

    Scaffold(
        topBar = {
            // TopAppBar for the screen
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_quote_list)) },
                actions = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                            contentDescription = "Refresh",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Custom background color
                    titleContentColor = Color.White // Custom title color
                )
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
