package com.yveskalume.herofeeds.android.ui.components

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yveskalume.herofeeds.android.R
import com.yveskalume.herofeeds.android.getFormattedDate
import com.yveskalume.herofeeds.data.model.hashnode.HashNodeRemotePost
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HashnodePostItem(post: HashNodeRemotePost, modifier: Modifier = Modifier) {

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Card(modifier = modifier, onClick = { uriHandler.openUri(post.url) }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_hashnode),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),

                    ) {
                    Text(
                        text = post.publishedAt.toString().getFormattedDate(),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, post.url);
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null)
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(post.coverImage)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = post.title,
                    maxLines = 3,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview
@Composable
private fun HashnodePostItemPreview() {
    MaterialTheme {
        HashnodePostItem(
            post = HashNodeRemotePost(
                id = "1",
                title = "",
                url = "",
                coverImage = null,
                publishedAt = Date()
            )
        )
    }
}