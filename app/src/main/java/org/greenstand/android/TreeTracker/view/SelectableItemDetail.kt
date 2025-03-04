package org.greenstand.android.TreeTracker.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.greenstand.android.TreeTracker.R
import org.greenstand.android.TreeTracker.models.user.User
import org.greenstand.android.TreeTracker.theme.CustomTheme

@Composable
fun SelectableImageDetail(
    photoPath: String? = null,
    isSelected: Boolean,
    buttonColors: DepthButtonColors,
    selectedColor: Color,
    onClick: () -> Unit,
    header: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    TreeTrackerButton(
        modifier = Modifier
            .padding(8.dp)
            .height(270.dp)
            .width(156.dp)
            .wrapContentHeight(),
        colors = buttonColors,
        onClick = onClick,
        isSelected = isSelected,
        borderBrushOverride = verticalGradient(
            colors = listOf(
                AppColors.Gray,
                selectedColor
            )
        ).takeIf { isSelected },
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            photoPath?.let {
                LocalImage(
                    imagePath = it,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .aspectRatio(1.0f)
                        .padding(bottom = 20.dp)
                        .clip(RoundedCornerShape(10.dp)),
                )
            }
            header?.let { it() }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun UserButton(
    user: User,
    isSelected: Boolean,
    buttonColors: DepthButtonColors,
    selectedColor: Color,
    onClick: () -> Unit,
    isNotificationEnabled: Boolean = false,
) {
    SelectableImageDetail(
        photoPath = user.photoPath,
        isSelected = isSelected,
        buttonColors = buttonColors,
        selectedColor = selectedColor,
        onClick = onClick
    ) {
        Text(
            text = "${user.firstName} ${user.lastName}",
            color = CustomTheme.textColors.lightText,
            style = CustomTheme.typography.small,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = user.wallet,
            color = CustomTheme.textColors.lightText,
            style = CustomTheme.typography.small,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.white_leaf),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(width = 20.dp, height = 22.dp)
            )
            Text(
                text = user.numberOfTrees.toString(), // TODO: Fetch user's token count.
                modifier = Modifier.weight(3f).padding(start = 4.dp),
                color = CustomTheme.textColors.lightText,
                style = CustomTheme.typography.medium,
                fontWeight = FontWeight.SemiBold,
            )
            if (isNotificationEnabled) {
                Image(
                    modifier = Modifier
                        .size(33.dp)
                        .weight(1f),
                    painter = painterResource(id = R.drawable.notification_icon),
                    contentDescription = null
                )
            }
        }
    }
}