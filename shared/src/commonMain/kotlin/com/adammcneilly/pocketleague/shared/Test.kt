package com.adammcneilly.pocketleague.shared

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Custom Colors
object RLColors {
    val DarkBg = Color(0xFF0F172A)
    val BlueBg = Color(0xFF1E3A8A)
    val SlateBg = Color(0xFF1E293B)
    val Cyan = Color(0xFF22D3EE)
    val CyanLight = Color(0xFF67E8F9)
    val Blue = Color(0xFF3B82F6)
    val Orange = Color(0xFFF97316)
    val Pink = Color(0xFFEC4899)
    val Purple = Color(0xFFA855F7)
    val Yellow = Color(0xFFFACC15)
    val SlateLight = Color(0xFF64748B)
}

@Composable
fun RocketLeagueEsportsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        RLColors.DarkBg,
                        RLColors.BlueBg,
                        RLColors.DarkBg
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            HeaderSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Live Match Card
            LiveMatchCard()

            Spacer(modifier = Modifier.height(24.dp))

            // Tournament Bracket
            TournamentBracket()

            Spacer(modifier = Modifier.height(24.dp))

            // Player Stats
            PlayerStats()
        }
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Glow effect background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .blur(40.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            RLColors.Cyan.copy(alpha = 0.2f),
                            RLColors.Blue.copy(alpha = 0.2f)
                        )
                    )
                )
        )

        // Content
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = RLColors.SlateBg.copy(alpha = 0.5f),
            border = BorderStroke(1.dp, RLColors.Cyan.copy(alpha = 0.3f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "RLCS LIVE",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = RLColors.Cyan
                    )
                    Text(
                        text = "Winter Major 2025",
                        fontSize = 14.sp,
                        color = RLColors.CyanLight.copy(alpha = 0.7f)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LiveIndicator()
                    Text(
                        text = "LIVE",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun LiveIndicator() {
    val infiniteTransition = rememberInfiniteTransition(label = "live")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(
        modifier = Modifier
            .size(8.dp)
            .background(Color.Red.copy(alpha = alpha), CircleShape)
    )
}

@Composable
fun LiveMatchCard() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Glow effect
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .blur(60.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            RLColors.Orange.copy(alpha = 0.2f),
                            RLColors.Pink.copy(alpha = 0.2f)
                        )
                    )
                )
        )

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = RLColors.SlateBg.copy(alpha = 0.8f),
            border = BorderStroke(1.dp, RLColors.Orange.copy(alpha = 0.3f))
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                // Match in Progress Label
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = RLColors.Orange,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "MATCH IN PROGRESS",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = RLColors.Orange,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Score Display
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Team 1
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "G2 Esports",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = RLColors.Cyan
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "3",
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    }

                    // Middle Info
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "GAME 5",
                            fontSize = 12.sp,
                            color = RLColors.SlateLight
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "VS",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = RLColors.Orange
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "2:34",
                            fontSize = 12.sp,
                            color = RLColors.Cyan
                        )
                    }

                    // Team 2
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Vitality",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = RLColors.Pink
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "2",
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(RLColors.Orange, RLColors.Pink)
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Watch Live",
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }

                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                RLColors.SlateBg.copy(alpha = 0.5f),
                                RoundedCornerShape(12.dp)
                            )
                            .border(1.dp, RLColors.SlateLight, RoundedCornerShape(12.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = "Stats",
                            tint = RLColors.SlateLight
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TournamentBracket() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = RLColors.SlateBg.copy(alpha = 0.5f),
        border = BorderStroke(1.dp, RLColors.Blue.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = RLColors.Yellow,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Bracket",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(
                    text = "Upper Finals",
                    fontSize = 14.sp,
                    color = RLColors.Cyan
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            listOf("G2 vs Vitality", "FaZe vs NRG").forEach { match ->
                BracketMatchItem(match)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun BracketMatchItem(match: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent,
        border = BorderStroke(1.dp, RLColors.SlateLight.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            RLColors.SlateBg.copy(alpha = 0.5f),
                            RLColors.SlateBg.copy(alpha = 0.5f)
                        )
                    )
                )
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = match,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Best of 7",
                    fontSize = 12.sp,
                    color = RLColors.SlateLight
                )
            }
        }
    }
}

@Composable
fun PlayerStats() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = RLColors.SlateBg.copy(alpha = 0.5f),
        border = BorderStroke(1.dp, RLColors.Purple.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = RLColors.Purple,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Top Players",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            listOf(
                Triple("Firstkiller", "1247", "Rating"),
                Triple("Zen", "0.72", "Goals/Game"),
                Triple("Beastmode", "1.84", "Assists/Game")
            ).forEach { (name, stat, metric) ->
                PlayerStatItem(name, stat, metric)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun PlayerStatItem(name: String, stat: String, metric: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            RLColors.SlateBg.copy(alpha = 0.3f),
                            Color.Transparent
                        )
                    )
                )
                .border(
                    width = 4.dp,
                    color = RLColors.Purple,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = metric,
                    fontSize = 12.sp,
                    color = RLColors.SlateLight
                )
            }

            Text(
                text = stat,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = RLColors.Purple
            )
        }
    }
}
