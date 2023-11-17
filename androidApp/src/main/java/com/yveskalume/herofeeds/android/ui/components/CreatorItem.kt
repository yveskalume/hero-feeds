package com.yveskalume.herofeeds.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yveskalume.herofeeds.android.R
import com.yveskalume.herofeeds.android.ui.theme.HeroFeedTheme
import com.yveskalume.herofeeds.data.local.Creator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatorItem(
    creator: Creator,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = creator.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = creator.bio,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(1.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (creator.medium != null) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_medium),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                    }

                    if (creator.twitter != null) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_x),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    if (creator.hashnode != null) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_hashnode),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CreatorItemPreview() {
    HeroFeedTheme {
        CreatorItem(
            onClick = {}, creator = Creator(
                id = 1,
                photo = null,
                name = "Yves Kalume",
                bio = "Android Developer",
                medium = "https://medium.com/@yveskalume",
                twitter = "https://twitter.com/yveskalume",
                hashnode = "https://yveskalume.hashnode.dev/"
            )
        )
    }
}