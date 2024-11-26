//
//  PocketLeagueIOSApp.swift
//  PocketLeagueIOS
//
//  Created by Adam McNeilly on 9/24/24.
//

import shared
import SwiftUI

@main
struct PocketLeagueIOSApp: App {
    init() {
        KoinInitializer_iosKt.doInitKoinIos()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
