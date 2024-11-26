//
//  pocketLeagueIosApp.swift
//  pocketLeagueIos
//
//  Created by Adam McNeilly on 7/6/23.
//

import shared
import SwiftUI

@main
struct pocketLeagueIosApp: App {
    
    init() {
        KoinInitializer_iosKt.doInitKoinIos()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
