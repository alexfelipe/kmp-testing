package br.com.alexfelipe.testappkmm.android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import br.com.alexfelipe.testappkmm.android.MyApplicationTheme
import br.com.alexfelipe.testappkmm.entities.Links
import br.com.alexfelipe.testappkmm.entities.RocketLaunch
import kotlin.random.Random

@Composable
fun RocketLaunchesList(
    rocketLaunches: List<RocketLaunch>
) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 16.dp
        )
    ) {
        items(rocketLaunches) { rocketLaunch ->
            Column(
                Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Launch name: ${rocketLaunch.missionName}")
                rocketLaunch.launchSuccess?.let { isSuccessful ->
                    Text(text = if (isSuccessful) "Successful" else "Unsuccessful")
                }
                Text("Launch year: ${rocketLaunch.launchYear}")
                Text(text = "Launch details: ${rocketLaunch.details}")
            }
        }

    }
}

@Preview
@Composable
fun RocketLaunchesListPreview() {
    MyApplicationTheme {
        RocketLaunchesList(
            listOf(
                generateRandomRocketLaunch(),
                generateRandomRocketLaunch()
            )
        )
    }
}

@Composable
private fun generateRandomRocketLaunch() = RocketLaunch(
    missionName = generateLoremIpsum(2),
    flightNumber = Random.nextInt(1000),
    details = generateLoremIpsum(10),
    launchDateUTC = Random.nextInt(1, 2024).toString(),
    launchSuccess = Random.nextBoolean(),
    links = Links(
        null, null
    )
)

private fun generateLoremIpsum(untilAmountWords: Int = 1) =
    LoremIpsum(
        Random.nextInt(
            1, if (untilAmountWords < 1) {
                1
            } else untilAmountWords
        )
    ).values.first()