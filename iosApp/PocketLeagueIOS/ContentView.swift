//
//  ContentView.swift
//  PocketLeagueIOS
//
//  Created by Adam McNeilly on 9/24/24.
//

import shared
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        PocketLeagueAppViewController_iosKt.pocketLeagueAppViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}

#Preview {
    ContentView()
}
