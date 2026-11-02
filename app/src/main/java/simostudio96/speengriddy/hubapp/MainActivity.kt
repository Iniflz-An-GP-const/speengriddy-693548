package simostudio96.speengriddy.hubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import simostudio96.speengriddy.hubapp.ui.composable.app_root.AppRoot
import simostudio96.speengriddy.hubapp.ui.theme.SUEDPAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SUEDPAppTheme {
                AppRoot()
            }
        }
    }
}