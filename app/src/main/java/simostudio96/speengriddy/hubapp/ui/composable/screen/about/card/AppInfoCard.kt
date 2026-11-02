package simostudio96.speengriddy.hubapp.ui.composable.screen.about.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import simostudio96.speengriddy.hubapp.BuildConfig
import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens
import simostudio96.speengriddy.hubapp.ui.theme.cardBorderStroke
import simostudio96.speengriddy.hubapp.ui.theme.imageBorder

private val LogoSize = 56.dp
private val TrailingIconSize = 20.dp
private val IconBadgeSize = 36.dp
private val SupportIconSize = 18.dp

@Composable
fun AppInfoCard(modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current
    val companyName = stringResource(R.string.suedp_company_name)
    val logoShape = RoundedCornerShape(dimens.cornerRadius)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimens.sectionGap),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimens.cardCornerRadius),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
            border = cardBorderStroke(),
        ) {
            Row(
                modifier = Modifier.padding(dimens.cardPadding),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.icon),
                    contentDescription = stringResource(R.string.suedp_app_logo_description),
                    modifier = Modifier
                        .size(LogoSize)
                        .clip(logoShape)
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                        .imageBorder(logoShape),
                )

                Spacer(modifier = Modifier.width(dimens.contentSpacing))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Text(
                        text = stringResource(R.string.suedp_main_menu_tagline),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Spacer(modifier = Modifier.height(dimens.tightSpacing))

                    Text(
                        text = stringResource(
                            R.string.about_version_format,
                            BuildConfig.VERSION_NAME
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelSmall,
                    )

                    if (companyName.isNotBlank()) {
                        Spacer(modifier = Modifier.height(dimens.contentSpacing))

                        Text(
                            text = companyName,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }

        CustomerSupportCard(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun CustomerSupportCard(modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current
    val supportLink = stringResource(R.string.suedp_customer_support_link)

    if (supportLink.isBlank()) return

    val uriHandler = LocalUriHandler.current

    Card(
        onClick = { uriHandler.openUri(supportLink) },
        modifier = modifier,
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = cardBorderStroke(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimens.cardPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
        ) {
            Box(
                modifier = Modifier
                    .size(IconBadgeSize)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Mail,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(SupportIconSize),
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.about_contact_label),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                )

                Spacer(modifier = Modifier.height(dimens.tightSpacing))

                Text(
                    text = supportLink,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(TrailingIconSize),
            )
        }
    }
}
