@file:Suppress("MagicNumber")

package com.adammcneilly.pocketleague.shared.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// ===== DARK THEME COLORS =====

// Primary - Cyan accent colors
private val Primary = Color(0xFF22D3EE) // Cyan 400
private val OnPrimary = Color(0xFF0A1929) // Very dark blue/slate
private val PrimaryContainer = Color(0xFF164E63) // Cyan 900
private val OnPrimaryContainer = Color(0xFF67E8F9) // Cyan 300

// Secondary - Blue accent colors
private val Secondary = Color(0xFF3B82F6) // Blue 500
private val OnSecondary = Color(0xFF0A1929)
private val SecondaryContainer = Color(0xFF1E3A8A) // Blue 900
private val OnSecondaryContainer = Color(0xFF60A5FA) // Blue 400

// Tertiary - Purple accent colors
private val Tertiary = Color(0xFFA855F7) // Purple 500
private val OnTertiary = Color(0xFF0A1929)
private val TertiaryContainer = Color(0xFF581C87) // Purple 900
private val OnTertiaryContainer = Color(0xFFC084FC) // Purple 400

// Error - Red for live/alerts
private val Error = Color(0xFFEF4444) // Red 500
private val OnError = Color(0xFF0A1929)
private val ErrorContainer = Color(0xFF7F1D1D) // Red 900
private val OnErrorContainer = Color(0xFFFF6B6B) // Red 400

// Background & Surface - Dark slate
private val Background = Color(0xFF020617) // Slate 950
private val OnBackground = Color(0xFFE2E8F0) // Slate 200
private val Surface = Color(0xFF0F172A) // Slate 900
private val OnSurface = Color(0xFFE2E8F0) // Slate 200

// Surface variants
private val SurfaceVariant = Color(0xFF1E293B) // Slate 800
private val OnSurfaceVariant = Color(0xFFCBD5E1) // Slate 300
private val SurfaceTint = Primary

// Outline
private val Outline = Color(0xFF334155) // Slate 700
private val OutlineVariant = Color(0xFF475569) // Slate 600

// Inverse colors
private val InverseSurface = Color(0xFFE2E8F0)
private val InverseOnSurface = Color(0xFF0F172A)
private val InversePrimary = Color(0xFF0E7490) // Cyan 700

// Scrim
private val Scrim = Color(0xFF000000)

// ===== LIGHT THEME COLORS =====

// Primary - Cyan accent colors (darker for light theme)
private val PrimaryLight = Color(0xFF0E7490) // Cyan 700
private val OnPrimaryLight = Color(0xFFFFFFFF)
private val PrimaryContainerLight = Color(0xFFCFFAFE) // Cyan 100
private val OnPrimaryContainerLight = Color(0xFF164E63) // Cyan 900

// Secondary - Blue accent colors
private val SecondaryLight = Color(0xFF1E40AF) // Blue 800
private val OnSecondaryLight = Color(0xFFFFFFFF)
private val SecondaryContainerLight = Color(0xFFDBEAFE) // Blue 100
private val OnSecondaryContainerLight = Color(0xFF1E3A8A) // Blue 900

// Tertiary - Purple accent colors
private val TertiaryLight = Color(0xFF7C3AED) // Purple 600
private val OnTertiaryLight = Color(0xFFFFFFFF)
private val TertiaryContainerLight = Color(0xFFEDE9FE) // Purple 100
private val OnTertiaryContainerLight = Color(0xFF581C87) // Purple 900

// Error - Red for live/alerts
private val ErrorLight = Color(0xFFDC2626) // Red 600
private val OnErrorLight = Color(0xFFFFFFFF)
private val ErrorContainerLight = Color(0xFFFEE2E2) // Red 100
private val OnErrorContainerLight = Color(0xFF7F1D1D) // Red 900

// Background & Surface - Light
private val BackgroundLight = Color(0xFFFAFAFA) // Gray 50
private val OnBackgroundLight = Color(0xFF0F172A) // Slate 900
private val SurfaceLight = Color(0xFFFFFFFF) // White
private val OnSurfaceLight = Color(0xFF0F172A) // Slate 900

// Surface variants
private val SurfaceVariantLight = Color(0xFFF1F5F9) // Slate 100
private val OnSurfaceVariantLight = Color(0xFF475569) // Slate 600
private val SurfaceTintLight = PrimaryLight

// Outline
private val OutlineLight = Color(0xFFCBD5E1) // Slate 300
private val OutlineVariantLight = Color(0xFFE2E8F0) // Slate 200

// Inverse colors
private val InverseSurfaceLight = Color(0xFF1E293B)
private val InverseOnSurfaceLight = Color(0xFFF1F5F9)
private val InversePrimaryLight = Color(0xFF22D3EE) // Cyan 400

/**
 * This is a custom set of colors that we can use within the Pocket League design system, that are not
 * a part of the Material 3 color scheme.
 */
data class PocketLeagueColors(
    val live: Color,
    val liveContainer: Color,
    val upcoming: Color,
    val upcomingContainer: Color,
    val scheduled: Color,
    val scheduledContainer: Color,
    val major: Color,
    val majorContainer: Color,
    val worlds: Color,
    val worldsContainer: Color,
    val stats: Color,
    val prize: Color,
    val teams: Color,
    val cyanTint: Color,
    val redTint: Color,
    val purpleTint: Color,
    val orangeTint: Color,
)

val lightPocketLeagueColors = PocketLeagueColors(
    live = Color(0xFFDC2626), // Red 600
    liveContainer = Color(0xFFFEE2E2), // Red 100
    upcoming = Color(0xFF0E7490), // Cyan 700
    upcomingContainer = Color(0xFFCFFAFE), // Cyan 100
    scheduled = Color(0xFF7C3AED), // Purple 600
    scheduledContainer = Color(0xFFEDE9FE), // Purple 100
    major = Color(0xFFEA580C), // Orange 600
    majorContainer = Color(0xFFFFEDD5), // Orange 100
    worlds = Color(0xFFCA8A04), // Yellow 600
    worldsContainer = Color(0xFFFEF9C3), // Yellow 100
    stats = Color(0xFF7C3AED), // Purple 600
    prize = Color(0xFFCA8A04), // Yellow 600
    teams = Color(0xFFDB2777), // Pink 600
    cyanTint = Color(0xFF0E7490).copy(alpha = 0.1f),
    redTint = Color(0xFFDC2626).copy(alpha = 0.1f),
    purpleTint = Color(0xFF7C3AED).copy(alpha = 0.1f),
    orangeTint = Color(0xFFEA580C).copy(alpha = 0.1f),
)

val darkPocketLeagueColors = PocketLeagueColors(
    live = Color(0xFFEF4444), // Red 500
    liveContainer = Color(0xFF7F1D1D), // Red 900
    upcoming = Color(0xFF22D3EE), // Cyan 400
    upcomingContainer = Color(0xFF164E63), // Cyan 900
    scheduled = Color(0xFFA855F7), // Purple 500
    scheduledContainer = Color(0xFF581C87), // Purple 900
    major = Color(0xFFF97316), // Orange 500
    majorContainer = Color(0xFF7C2D12), // Orange 900
    worlds = Color(0xFFFACC15), // Yellow 400
    worldsContainer = Color(0xFF713F12), // Yellow 900
    stats = Color(0xFFA855F7), // Purple 500
    prize = Color(0xFFFACC15), // Yellow 400
    teams = Color(0xFFEC4899), // Pink 500
    cyanTint = Color(0xFF22D3EE).copy(alpha = 0.1f),
    redTint = Color(0xFFEF4444).copy(alpha = 0.1f),
    purpleTint = Color(0xFFA855F7).copy(alpha = 0.1f),
    orangeTint = Color(0xFFF97316).copy(alpha = 0.1f),
)

val LocalPocketLeagueColors = staticCompositionLocalOf { lightPocketLeagueColors }

val darkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,
    background = Background,
    onBackground = OnBackground,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceTint = SurfaceTint,
    outline = Outline,
    outlineVariant = OutlineVariant,
    inverseSurface = InverseSurface,
    inverseOnSurface = InverseOnSurface,
    inversePrimary = InversePrimary,
    scrim = Scrim,
)

val lightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    surfaceTint = SurfaceTintLight,
    outline = OutlineLight,
    outlineVariant = OutlineVariantLight,
    inverseSurface = InverseSurfaceLight,
    inverseOnSurface = InverseOnSurfaceLight,
    inversePrimary = InversePrimaryLight,
    scrim = Scrim,
)
