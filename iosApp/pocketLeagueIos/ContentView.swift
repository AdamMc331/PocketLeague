//
//  ContentView.swift
//  pocketLeagueIos
//
//  Created by Adam McNeilly on 7/6/23.
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
                .ignoresSafeArea(.all, edges: .bottom) // Compose has own keyboard handler
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
