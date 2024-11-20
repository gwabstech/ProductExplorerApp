package com.gwabs.productexplorerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gwabs.productexplorerapp.ui.navigation.AppNavigation
import com.gwabs.productexplorerapp.ui.theme.ProductExplorerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

                AppNavigation()

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProductExplorerAppTheme {

        AppNavigation()
    }
}