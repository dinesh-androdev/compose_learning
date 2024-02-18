package com.example.dineshcompose.ui.designs

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dineshcompose.model.BottomMenuContent
import com.example.dineshcompose.model.Features
import com.example.dineshcompose.R
import com.example.dineshcompose.navigation.Screen
import com.example.dineshcompose.ui.activity.NavigationActivity
import com.example.dineshcompose.ui.activity.ui.theme.AquaBlue
import com.example.dineshcompose.ui.activity.ui.theme.Beige1
import com.example.dineshcompose.ui.activity.ui.theme.BlueViolet1
import com.example.dineshcompose.ui.activity.ui.theme.ButtonBlue
import com.example.dineshcompose.ui.activity.ui.theme.DarkerButtonBlue
import com.example.dineshcompose.ui.activity.ui.theme.DeepBlue
import com.example.dineshcompose.ui.activity.ui.theme.LightGreen1
import com.example.dineshcompose.ui.activity.ui.theme.LightRed
import com.example.dineshcompose.ui.activity.ui.theme.OrangeYellow1
import com.example.dineshcompose.ui.activity.ui.theme.TextWhite

@Composable
fun HomeScreen(navController: NavController, context: Context) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    val chipList = listOf(
        "Good Morning",
        "Good Afternoon",
        "Good Evening",
        "Good Night"
    )
    val featuresArray = getFeaturesArray()
    var selectedFeatureIndex by remember {
        mutableStateOf(0)
    }

    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection(
                chipSelectedPosition = selectedChipIndex,
                chipList,
                context = context
            )
            ChipSection(
                chips = chipList
            ) {
                selectedChipIndex = it
            }
            CurrentMeditation(
                featuresPosition = selectedFeatureIndex,
                featureItems = featuresArray,
                navController = navController
            )
            FeaturesSection(features = featuresArray) {
                selectedFeatureIndex = it
            }
        }
        BottomMenu(
            items = getBottomMenuItems(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

fun getBottomMenuItems(): List<BottomMenuContent> {
    val list = ArrayList<BottomMenuContent>()
    list.add(BottomMenuContent("Home", R.drawable.ic_home))
    list.add(BottomMenuContent("Medicate", R.drawable.ic_bubble))
    list.add(BottomMenuContent("Sleep", R.drawable.ic_moon))
    list.add(BottomMenuContent("Music", R.drawable.ic_music))
    list.add(BottomMenuContent("Profile", R.drawable.ic_profile))
    return list
}

fun getFeaturesArray(): List<Features> {
    val list = ArrayList<Features>()
    list.add(Features("Sleep meditation", R.drawable.ic_music, BlueViolet1))
    list.add(Features("Tips for sleep", R.drawable.ic_videocam, LightGreen1))
    list.add(Features("Night island", R.drawable.ic_moon, Beige1))
    list.add(Features("Calming sounds", R.drawable.ic_headphone, OrangeYellow1))
    list.add(Features("Sleep meditation", R.drawable.ic_music, BlueViolet1))
    list.add(Features("Tips for sleep", R.drawable.ic_videocam, LightGreen1))
    list.add(Features("Night island", R.drawable.ic_moon, Beige1))
    list.add(Features("Calming sounds", R.drawable.ic_headphone, OrangeYellow1))
    return list
}

@Composable
fun GreetingSection(
    chipSelectedPosition: Int,
    chips: List<String>,
    name: String = "Dinesh",
    context: Context
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${chips[chipSelectedPosition]}, $name",
                style = MaterialTheme.typography.headlineMedium,
                color = TextWhite
            )
            Text(
                text = "We wish you have a good day",
                style = MaterialTheme.typography.bodyLarge,
                color = DarkerButtonBlue
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
                .clickable {
                    context.startActivity(Intent(context, NavigationActivity::class.java))
                }
        )

    }
}

@Composable
fun ChipSection(
    chips: List<String>,
    onChipClicked: (Int) -> Unit
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable {
                    selectedChipIndex = it
                    onChipClicked(it)
                }
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (selectedChipIndex == it) ButtonBlue
                    else DarkerButtonBlue
                )
                .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed,
    featuresPosition: Int,
    featureItems: List<Features>,
    navController: NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        Column {
            Text(
                text = featureItems[featuresPosition].title,
                style = MaterialTheme.typography.headlineMedium,
                color = TextWhite
            )
            Text(
                text = "Meditation . 3-10 min",
                style = MaterialTheme.typography.bodyLarge,
                color = TextWhite
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(15.dp)
                .clickable {
                    navController.navigate(Screen.DetailsScreen.withArgs(featureItems[featuresPosition].title))
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                tint = Color.White,
                contentDescription = "Play",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun FeaturesSection(
    features: List<Features>,
    featureStartClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(15.dp),
            color = TextWhite
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(features.size) {
                FeaturesItem(features = features[it], it, featureStartClick)
            }
        }
    }
}

@Composable
fun FeaturesItem(
    features: Features,
    position: Int,
    featureStartClick: (Int) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(features.color)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = features.title,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 26.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.TopStart),
                color = TextWhite
            )
            Icon(
                painter = painterResource(id = features.iconId),
                contentDescription = features.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        featureStartClick(position)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}


@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, bottomMenuContent ->
            BottomMenuItem(
                item = bottomMenuContent,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onClickItem: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onClickItem()
        }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}
