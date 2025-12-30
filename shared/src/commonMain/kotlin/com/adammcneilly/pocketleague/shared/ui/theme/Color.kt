@file:Suppress("MagicNumber")

package com.adammcneilly.pocketleague.shared.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// ===== DARK THEME COLORS =====

// Primary - Cyan accent colors
val Primary = Color(0xFF22D3EE) // Cyan 400
val OnPrimary = Color(0xFF0A1929) // Very dark blue/slate
val PrimaryContainer = Color(0xFF164E63) // Cyan 900
val OnPrimaryContainer = Color(0xFF67E8F9) // Cyan 300

// Secondary - Blue accent colors
val Secondary = Color(0xFF3B82F6) // Blue 500
val OnSecondary = Color(0xFF0A1929)
val SecondaryContainer = Color(0xFF1E3A8A) // Blue 900
val OnSecondaryContainer = Color(0xFF60A5FA) // Blue 400

// Tertiary - Purple accent colors
val Tertiary = Color(0xFFA855F7) // Purple 500
val OnTertiary = Color(0xFF0A1929)
val TertiaryContainer = Color(0xFF581C87) // Purple 900
val OnTertiaryContainer = Color(0xFFC084FC) // Purple 400

// Error - Red for live/alerts
val Error = Color(0xFFEF4444) // Red 500
val OnError = Color(0xFF0A1929)
val ErrorContainer = Color(0xFF7F1D1D) // Red 900
val OnErrorContainer = Color(0xFFFF6B6B) // Red 400

// Background & Surface - Dark slate
val Background = Color(0xFF020617) // Slate 950
val OnBackground = Color(0xFFE2E8F0) // Slate 200
val Surface = Color(0xFF0F172A) // Slate 900
val OnSurface = Color(0xFFE2E8F0) // Slate 200

// Surface variants
val SurfaceVariant = Color(0xFF1E293B) // Slate 800
val OnSurfaceVariant = Color(0xFFCBD5E1) // Slate 300
val SurfaceTint = Primary

// Outline
val Outline = Color(0xFF334155) // Slate 700
val OutlineVariant = Color(0xFF475569) // Slate 600

// Inverse colors
val InverseSurface = Color(0xFFE2E8F0)
val InverseOnSurface = Color(0xFF0F172A)
val InversePrimary = Color(0xFF0E7490) // Cyan 700

// Scrim
val Scrim = Color(0xFF000000)

// ===== LIGHT THEME COLORS =====

// Primary - Cyan accent colors (darker for light theme)
val PrimaryLight = Color(0xFF0E7490) // Cyan 700
val OnPrimaryLight = Color(0xFFFFFFFF)
val PrimaryContainerLight = Color(0xFFCFFAFE) // Cyan 100
val OnPrimaryContainerLight = Color(0xFF164E63) // Cyan 900

// Secondary - Blue accent colors
val SecondaryLight = Color(0xFF1E40AF) // Blue 800
val OnSecondaryLight = Color(0xFFFFFFFF)
val SecondaryContainerLight = Color(0xFFDBEAFE) // Blue 100
val OnSecondaryContainerLight = Color(0xFF1E3A8A) // Blue 900

// Tertiary - Purple accent colors
val TertiaryLight = Color(0xFF7C3AED) // Purple 600
val OnTertiaryLight = Color(0xFFFFFFFF)
val TertiaryContainerLight = Color(0xFFEDE9FE) // Purple 100
val OnTertiaryContainerLight = Color(0xFF581C87) // Purple 900

// Error - Red for live/alerts
val ErrorLight = Color(0xFFDC2626) // Red 600
val OnErrorLight = Color(0xFFFFFFFF)
val ErrorContainerLight = Color(0xFFFEE2E2) // Red 100
val OnErrorContainerLight = Color(0xFF7F1D1D) // Red 900

// Background & Surface - Light
val BackgroundLight = Color(0xFFFAFAFA) // Gray 50
val OnBackgroundLight = Color(0xFF0F172A) // Slate 900
val SurfaceLight = Color(0xFFFFFFFF) // White
val OnSurfaceLight = Color(0xFF0F172A) // Slate 900

// Surface variants
val SurfaceVariantLight = Color(0xFFF1F5F9) // Slate 100
val OnSurfaceVariantLight = Color(0xFF475569) // Slate 600
val SurfaceTintLight = PrimaryLight

// Outline
val OutlineLight = Color(0xFFCBD5E1) // Slate 300
val OutlineVariantLight = Color(0xFFE2E8F0) // Slate 200

// Inverse colors
val InverseSurfaceLight = Color(0xFF1E293B)
val InverseOnSurfaceLight = Color(0xFFF1F5F9)
val InversePrimaryLight = Color(0xFF22D3EE) // Cyan 400

// Custom accent colors for specific features
object RLAccentColors {
    // Status colors - Dark theme
    val Live = Color(0xFFEF4444) // Red 500
    val LiveContainer = Color(0xFF7F1D1D) // Red 900
    val Upcoming = Color(0xFF22D3EE) // Cyan 400
    val UpcomingContainer = Color(0xFF164E63) // Cyan 900
    val Scheduled = Color(0xFFA855F7) // Purple 500
    val ScheduledContainer = Color(0xFF581C87) // Purple 900

    // Status colors - Light theme
    val LiveLight = Color(0xFFDC2626) // Red 600
    val LiveContainerLight = Color(0xFFFEE2E2) // Red 100
    val UpcomingLight = Color(0xFF0E7490) // Cyan 700
    val UpcomingContainerLight = Color(0xFFCFFAFE) // Cyan 100
    val ScheduledLight = Color(0xFF7C3AED) // Purple 600
    val ScheduledContainerLight = Color(0xFFEDE9FE) // Purple 100

    // Type colors - Dark theme
    val Major = Color(0xFFF97316) // Orange 500
    val MajorContainer = Color(0xFF7C2D12) // Orange 900
    val Worlds = Color(0xFFFACC15) // Yellow 400
    val WorldsContainer = Color(0xFF713F12) // Yellow 900

    // Type colors - Light theme
    val MajorLight = Color(0xFFEA580C) // Orange 600
    val MajorContainerLight = Color(0xFFFFEDD5) // Orange 100
    val WorldsLight = Color(0xFFCA8A04) // Yellow 600
    val WorldsContainerLight = Color(0xFFFEF9C3) // Yellow 100

    // Data colors (work for both themes)
    val Stats = Color(0xFFA855F7) // Purple 500
    val StatsLight = Color(0xFF7C3AED) // Purple 600
    val Prize = Color(0xFFFACC15) // Yellow 400
    val PrizeLight = Color(0xFFCA8A04) // Yellow 600
    val Teams = Color(0xFFEC4899) // Pink 500
    val TeamsLight = Color(0xFFDB2777) // Pink 600

    // Surface tints for emphasis - Dark
    val CyanTint = Color(0xFF22D3EE).copy(alpha = 0.1f)
    val RedTint = Color(0xFFEF4444).copy(alpha = 0.1f)
    val PurpleTint = Color(0xFFA855F7).copy(alpha = 0.1f)
    val OrangeTint = Color(0xFFF97316).copy(alpha = 0.1f)

    // Surface tints for emphasis - Light
    val CyanTintLight = Color(0xFF0E7490).copy(alpha = 0.1f)
    val RedTintLight = Color(0xFFDC2626).copy(alpha = 0.1f)
    val PurpleTintLight = Color(0xFF7C3AED).copy(alpha = 0.1f)
    val OrangeTintLight = Color(0xFFEA580C).copy(alpha = 0.1f)
}

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
