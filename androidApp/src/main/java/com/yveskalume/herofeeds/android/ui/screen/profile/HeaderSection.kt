package com.yveskalume.herofeeds.android.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yveskalume.herofeeds.android.R

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.wrapContentHeight()) {
                Text(text = "Yves Kalume", style = MaterialTheme.typography.titleLarge)
                Text(text = "Android Developer", style = MaterialTheme.typography.bodyMedium)
            }

            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Edit")
            }
        }
    }
}

@Preview
@Composable
private fun HeaderSectionPreview() {
    MaterialTheme {
        HeaderSection()
    }
}