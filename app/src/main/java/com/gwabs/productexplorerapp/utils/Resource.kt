package com.gwabs.productexplorerapp.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data) // data is guaranteed to be non-null
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralAppBar(
    title: String,
    isHome: Boolean = false,
    onClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                title, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                color =  Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

        },

        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        ),


        navigationIcon = {
            if (!isHome){
                IconButton(onClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back_button",
                        tint =  Color.Black
                    )
                }
            }

        },
    )
}