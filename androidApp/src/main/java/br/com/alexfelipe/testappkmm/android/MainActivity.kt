package br.com.alexfelipe.testappkmm.android

import android.os.Bundle
import android.provider.SyncStateContract.Columns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.alexfelipe.testappkmm.SpaceXSDK
import br.com.alexfelipe.testappkmm.android.ui.screens.RocketLaunchesList
import br.com.alexfelipe.testappkmm.cache.DatabaseDriverFactory
import br.com.alexfelipe.testappkmm.entities.RocketLaunch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val spaceXSDK = SpaceXSDK(DatabaseDriverFactory(this))
        setContent {
            MyApplicationTheme {
                Surface {
                    var rocketLaunches by remember {
                        mutableStateOf(emptyList<RocketLaunch>())
                    }
                    LaunchedEffect(null) {
                        rocketLaunches = spaceXSDK.getLaunches(true)
                    }
                    RocketLaunchesList(rocketLaunches = rocketLaunches)
                }
            }
        }
    }
}

@Composable
fun App() {
    Column {
        Text(text = "alex felipe")
    }
}

@Preview
@Composable
fun AppPreview() {
    MyApplicationTheme {
        App()
    }
}