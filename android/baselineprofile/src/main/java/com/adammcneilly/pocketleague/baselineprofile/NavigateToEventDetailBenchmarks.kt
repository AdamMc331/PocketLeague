package com.adammcneilly.pocketleague.baselineprofile

import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.UiSelector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Runs a set of benchmark tests to see how much the baseline profile
 * improves navigating to the event detail screen.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
@Suppress("MagicNumber")
class NavigateToEventDetailBenchmarks {

    @get:Rule
    val rule = MacrobenchmarkRule()

    @Test
    fun navigateToEventDetailCompilationNone() =
        benchmark(CompilationMode.None())

    @Test
    fun navigateToEventDetailCompilationBaselineProfiles() =
        benchmark(CompilationMode.Partial(BaselineProfileMode.Require))

    private fun benchmark(compilationMode: CompilationMode) {
        rule.measureRepeated(
            packageName = "com.adammcneilly.pocketleague",
            // Need to override this?
            metrics = listOf(StartupTimingMetric(), FrameTimingMetric()),
            compilationMode = compilationMode,
            startupMode = StartupMode.COLD,
            iterations = 10,
            setupBlock = {
                pressHome()
            },
            measureBlock = {
                startActivityAndWait()

                // Navigate to event detail
                // We're hardcoding this information just to get quick tests, but ideally we should:
                // 1. Be mocking the network response data so it's always consistent
                // 2. Be very explicit that we click on an event and not a match.
                val gamers8Event = device.findObject(UiSelector().text("Telialigaen Spring 2023 Division 1"))
                gamers8Event.click()

                val stagesHeader = device.findObject(UiSelector().text("Stages"))
                stagesHeader.waitForExists(5_000)

                // TODO Add interactions to wait for when your app is fully drawn.
                // The app is fully drawn when Activity.reportFullyDrawn is called.
                // For Jetpack Compose, you can use ReportDrawn, ReportDrawnWhen and ReportDrawnAfter
                // from the AndroidX Activity library.

                // Check the UiAutomator documentation for more information on how to
                // interact with the app.
                // https://d.android.com/training/testing/other-components/ui-automator
            },
        )
    }
}
