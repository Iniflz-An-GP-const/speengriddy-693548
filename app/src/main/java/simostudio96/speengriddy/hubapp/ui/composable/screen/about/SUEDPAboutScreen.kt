package simostudio96.speengriddy.hubapp.ui.composable.screen.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.ui.composable.app_root.topbar.AppTopBar
import simostudio96.speengriddy.hubapp.ui.composable.screen.about.card.AppInfoCard
import simostudio96.speengriddy.hubapp.ui.composable.screen.about.rules.header.RulesHeader
import simostudio96.speengriddy.hubapp.ui.composable.screen.about.rules.list.RulesList
import simostudio96.speengriddy.hubapp.ui.theme.LocalDimens
import simostudio96.speengriddy.hubapp.ui.theme.cardBorderStroke

@Composable
fun SUEDPAboutScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier.fillMaxSize()) {
        AppTopBar(
            title = stringResource(R.string.about_title),
            onNavigateBack = onBackClick,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(dimens.screenPadding),
            verticalArrangement = Arrangement.spacedBy(dimens.sectionGap),
        ) {
            RulesCard(modifier = Modifier.fillMaxWidth())

            AppInfoCard(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun RulesCard(modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current
    val rules = stringArrayResource(R.array.about_rules).toList()

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = cardBorderStroke(),
    ) {
        Column(
            modifier = Modifier.padding(dimens.cardPadding),
            verticalArrangement = Arrangement.spacedBy(dimens.sectionGap),
        ) {
            RulesHeader(title = stringResource(R.string.about_rules_title))
            RulesList(rules = rules)
        }
    }
}
