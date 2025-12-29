package com.adammcneilly.pocketleague.shared

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adammcneilly.pocketleague.shared.feature.eventlist.EventListHeader
import kotlinx.coroutines.delay

// Custom Colors (reusing from previous design)
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
    val Red = Color(0xFFEF4444)
    val SlateLight = Color(0xFF64748B)
    val SlateText = Color(0xFF94A3B8)
}

data class Event(
    val id: Int,
    val name: String,
    val date: String,
    val time: String,
    val location: String,
    val status: EventStatus,
    val teams: Int,
    val prize: String,
    val type: EventType,
)

enum class EventStatus {
    LIVE,
    UPCOMING,
    SCHEDULED,
}

enum class EventType {
    MAJOR,
    REGIONAL,
    WORLDS,
}

data class FilterOption(
    val id: String,
    val label: String,
)

@Composable
fun RocketLeagueEventsScreen(
    modifier: Modifier = Modifier,
) {
    var selectedFilter by remember { mutableStateOf("all") }

    val events = remember {
        listOf(
            Event(
                1,
                "RLCS Winter Major",
                "Jan 15-19, 2025",
                "12:00 PM EST",
                "Los Angeles, CA",
                EventStatus.LIVE,
                16,
                "$500,000",
                EventType.MAJOR,
            ),
            Event(
                2,
                "NA Regional #3",
                "Jan 25-26, 2025",
                "3:00 PM EST",
                "Online",
                EventStatus.UPCOMING,
                32,
                "$100,000",
                EventType.REGIONAL,
            ),
            Event(
                3,
                "EU Regional #3",
                "Jan 27-28, 2025",
                "10:00 AM CET",
                "Online",
                EventStatus.UPCOMING,
                32,
                "$100,000",
                EventType.REGIONAL,
            ),
            Event(
                4,
                "RLCS World Championship",
                "Apr 10-13, 2025",
                "TBA",
                "London, UK",
                EventStatus.SCHEDULED,
                24,
                "$2,000,000",
                EventType.WORLDS,
            ),
            Event(
                5,
                "SAM Regional #2",
                "Feb 1-2, 2025",
                "5:00 PM BRT",
                "Online",
                EventStatus.UPCOMING,
                16,
                "$50,000",
                EventType.REGIONAL,
            ),
        )
    }

    val filters = listOf(
        FilterOption("all", "All"),
        FilterOption("live", "Live"),
        FilterOption("upcoming", "Upcoming"),
        FilterOption("major", "Majors"),
    )

    val filteredEvents = events.filter { event ->
        when (selectedFilter) {
            "all" -> true
            "live" -> event.status == EventStatus.LIVE
            "upcoming" -> event.status == EventStatus.UPCOMING
            "major" -> event.type == EventType.MAJOR || event.type == EventType.WORLDS
            else -> true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        RLColors.DarkBg,
                        RLColors.BlueBg,
                        RLColors.DarkBg,
                    ),
                ),
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Header - Sticky
            EventListHeader(
                filters = filters,
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it },
            )

            // Events List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(filteredEvents) { event ->
                    EventCard(event = event)
                }

                if (filteredEvents.isEmpty()) {
                    item {
                        EmptyState()
                    }
                }
            }
        }
    }
}

@Composable
fun EventsHeader(
    filters: List<FilterOption>,
    selectedFilter: String,
    onFilterSelected: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        RLColors.Cyan.copy(alpha = 0.2f),
                        RLColors.Blue.copy(alpha = 0.2f),
                    ),
                ),
            ),
//        color = Color.Transparent,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                // Glow effect
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(80.dp)
//                        .blur(40.dp)
//                        .background(
//                            brush = Brush.horizontalGradient(
//                                colors = listOf(
//                                    RLColors.Cyan.copy(alpha = 0.2f),
//                                    RLColors.Blue.copy(alpha = 0.2f),
//                                ),
//                            ),
//                        ),
//                )

                // Content
                Surface(
                    modifier = Modifier.fillMaxWidth()
                        .statusBarsPadding(),
                    shape = RoundedCornerShape(16.dp),
                    color = RLColors.SlateBg.copy(alpha = 0.5f),
                    border = BorderStroke(1.dp, RLColors.Cyan.copy(alpha = 0.3f)),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column {
                                Text(
                                    text = "Events",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RLColors.Cyan,
                                )
                                Text(
                                    text = "RLCS 2024-25",
                                    fontSize = 12.sp,
                                    color = RLColors.CyanLight.copy(alpha = 0.7f),
                                )
                            }

                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        RLColors.SlateBg.copy(alpha = 0.5f),
                                        RoundedCornerShape(8.dp),
                                    )
                                    .border(1.dp, RLColors.SlateLight, RoundedCornerShape(8.dp)),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FilterList,
                                    contentDescription = "Filter",
                                    tint = RLColors.Cyan,
                                    modifier = Modifier.size(16.dp),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Filter Chips
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            filters.forEach { filter ->
                                FilterChip(
                                    label = filter.label,
                                    selected = selectedFilter == filter.id,
                                    onClick = { onFilterSelected(filter.id) },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        color = if (selected) Color.Transparent else RLColors.SlateBg.copy(alpha = 0.5f),
        border = if (selected) null else BorderStroke(1.dp, RLColors.SlateLight.copy(alpha = 0.5f)),
    ) {
        Box(
            modifier = Modifier
                .then(
                    if (selected) {
                        Modifier.background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(RLColors.Cyan, RLColors.Blue),
                            ),
                            shape = RoundedCornerShape(8.dp),
                        )
                    } else {
                        Modifier
                    },
                )
                .padding(horizontal = 12.dp, vertical = 6.dp),
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (selected) Color.White else RLColors.SlateText,
            )
        }
    }
}

@Composable
fun EventCard(
    event: Event,
) {
    var scale by remember { mutableStateOf(1f) }
    val statusColors = getStatusColors(event.status)

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        // Glow effect
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .blur(40.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = statusColors.glowColors,
                    ),
                ),
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .scale(scale)
                .clickable {
                    scale = 0.98f
                },
            shape = RoundedCornerShape(12.dp),
            color = RLColors.SlateBg.copy(alpha = 0.8f),
            border = BorderStroke(1.dp, statusColors.borderColor),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                // Top Row: Icon, Status, Type Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = getEventTypeIcon(event.type),
                            fontSize = 20.sp,
                        )

                        if (event.status == EventStatus.LIVE) {
                            LiveBadge()
                        }
                    }

                    EventTypeBadge(type = event.type)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Event Name
                Text(
                    text = event.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Event Details
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        EventDetailItem(
                            icon = Icons.Default.CalendarToday,
                            iconColor = RLColors.Cyan,
                            label = "Date",
                            value = event.date,
                            modifier = Modifier.weight(1f),
                        )
                        EventDetailItem(
                            icon = Icons.Default.AccessTime,
                            iconColor = RLColors.Blue,
                            label = "Time",
                            value = event.time,
                            modifier = Modifier.weight(1f),
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        EventDetailItem(
                            icon = Icons.Default.LocationOn,
                            iconColor = RLColors.Purple,
                            label = "Location",
                            value = event.location,
                            modifier = Modifier.weight(1f),
                        )
                        EventDetailItem(
                            icon = Icons.Default.People,
                            iconColor = RLColors.Pink,
                            label = "Teams",
                            value = event.teams.toString(),
                            modifier = Modifier.weight(1f),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Prize Pool & CTA
                Divider(
                    color = RLColors.SlateBg.copy(alpha = 0.5f),
                    thickness = 1.dp,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = RLColors.Yellow,
                            modifier = Modifier.size(16.dp),
                        )
                        Text(
                            text = event.prize,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = RLColors.Yellow,
                        )
                    }

                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            RLColors.Cyan.copy(alpha = 0.2f),
                                            RLColors.Blue.copy(alpha = 0.2f),
                                        ),
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                )
                                .border(1.dp, RLColors.Cyan.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Text(
                                    text = "Details",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = RLColors.Cyan,
                                )
                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = null,
                                    tint = RLColors.Cyan,
                                    modifier = Modifier.size(14.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(scale) {
        if (scale != 1f) {
            delay(100)
            scale = 1f
        }
    }
}

@Composable
fun LiveBadge() {
    val infiniteTransition = rememberInfiniteTransition(label = "live")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "alpha",
    )

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = RLColors.Red.copy(alpha = 0.2f),
        border = BorderStroke(1.dp, RLColors.Red.copy(alpha = 0.5f)),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(RLColors.Red.copy(alpha = alpha), CircleShape),
            )
            Text(
                text = "LIVE",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = RLColors.Red,
                letterSpacing = 0.5.sp,
            )
        }
    }
}

@Composable
fun EventTypeBadge(
    type: EventType,
) {
    data class BadgeColors(
        val gradientStart: Color,
        val gradientEnd: Color,
        val borderColor: Color,
        val textColor: Color,
    )

    val (text, colors) = when (type) {
        EventType.WORLDS -> "WORLDS" to BadgeColors(
            gradientStart = RLColors.Yellow.copy(alpha = 0.2f),
            gradientEnd = RLColors.Orange.copy(alpha = 0.2f),
            borderColor = RLColors.Yellow.copy(alpha = 0.5f),
            textColor = RLColors.Yellow,
        )

        EventType.MAJOR -> "MAJOR" to BadgeColors(
            gradientStart = RLColors.Orange.copy(alpha = 0.2f),
            gradientEnd = RLColors.Pink.copy(alpha = 0.2f),
            borderColor = RLColors.Orange.copy(alpha = 0.5f),
            textColor = RLColors.Orange,
        )

        else -> return
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent,
        border = BorderStroke(1.dp, colors.borderColor),
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(colors.gradientStart, colors.gradientEnd),
                    ),
                    shape = RoundedCornerShape(12.dp),
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
            Text(
                text = text,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textColor,
                letterSpacing = 0.5.sp,
            )
        }
    }
}

@Composable
fun EventDetailItem(
    icon: ImageVector,
    iconColor: Color,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(RLColors.SlateBg.copy(alpha = 0.5f), RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(14.dp),
            )
        }

        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = label.uppercase(),
                fontSize = 9.sp,
                color = RLColors.SlateLight,
                letterSpacing = 0.5.sp,
            )
            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "No events found",
            fontSize = 16.sp,
            color = RLColors.SlateLight,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Try adjusting your filters",
            fontSize = 14.sp,
            color = RLColors.SlateLight.copy(alpha = 0.7f),
        )
    }
}

data class StatusColors(
    val glowColors: List<Color>,
    val borderColor: Color,
)

fun getStatusColors(
    status: EventStatus,
): StatusColors {
    return when (status) {
        EventStatus.LIVE -> StatusColors(
            glowColors = listOf(
                RLColors.Red.copy(alpha = 0.2f),
                RLColors.Orange.copy(alpha = 0.2f),
            ),
            borderColor = RLColors.Red.copy(alpha = 0.3f),
        )

        EventStatus.UPCOMING -> StatusColors(
            glowColors = listOf(
                RLColors.Cyan.copy(alpha = 0.2f),
                RLColors.Blue.copy(alpha = 0.2f),
            ),
            borderColor = RLColors.Cyan.copy(alpha = 0.3f),
        )

        EventStatus.SCHEDULED -> StatusColors(
            glowColors = listOf(
                RLColors.Purple.copy(alpha = 0.2f),
                RLColors.Pink.copy(alpha = 0.2f),
            ),
            borderColor = RLColors.Purple.copy(alpha = 0.3f),
        )
    }
}

fun getEventTypeIcon(
    type: EventType,
): String {
    return when (type) {
        EventType.WORLDS, EventType.MAJOR -> "🏆"
        EventType.REGIONAL -> "⚡"
    }
}
