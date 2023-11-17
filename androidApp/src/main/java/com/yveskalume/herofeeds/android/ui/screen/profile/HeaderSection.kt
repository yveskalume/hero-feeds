package com.yveskalume.herofeeds.android.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.yveskalume.herofeeds.data.local.Creator

@Composable
fun HeaderSection(
    creator: Creator,
    modifier: Modifier = Modifier
) {
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
                Text(text = creator.name, style = MaterialTheme.typography.titleLarge)
                Text(text = creator.bio, style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription =null)
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
        HeaderSection(
            creator = Creator(
                id = 1,
                photo = null,
                name = "Yves Kalume",
                bio = "Android Developer",
                medium = "https://medium.com/@yveskalume",
                hashnode = "https://yveskalume.hashnode.dev/"
            )
        )
    }
}