./snapshotscripts/run_emulator_for_snapshot_testing.sh &
./gradlew debugExecuteScreenshotTests -Pandroid.testInstrumentationRunnerArguments.tests_regex=.*ScreenshotTest.*
adb -s emulator-5554 emu kill