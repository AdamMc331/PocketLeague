package com.adammcneilly.pocketleague.shared.app.di

import org.koin.core.module.Module

/**
 * Provide a Koin [Module] of dependencies specific to their platform.
 */
expect val platformModule: Module
