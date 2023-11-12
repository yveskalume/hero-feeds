package com.yveskalume.herofeeds.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yveskalume.herofeeds.android.R

@Composable
fun XPostItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_x), contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),

                    ) {
                    Text(
                        text = "2 hours ago",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null)
                }
            }
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                style = MaterialTheme.typography.bodySmall,
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier.clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Preview
@Composable
private fun XPostItemPreview() {
    MaterialTheme {
        XPostItem()
    }
}