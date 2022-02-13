//
//  ContentView.swift
//  PocketLeagueiOS
//
//  Created by Adam McNeilly on 2/12/22.
//

import SwiftUI
import eventsummary

struct ContentView: View {
    
    @StateObject var viewModel = EventSummaryListviewModel()
    
    var body: some View {
        Text(viewModel.eventNames.joined(separator: "\n"))
            .onAppear {
                async {
                    await viewModel.fetchUpcomingEvents()
                }
            }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

@MainActor
class EventSummaryListviewModel: ObservableObject {
    @Published var eventNames: [String] = []
    
    func fetchUpcomingEvents() async {
        do {
            let eventResult = try await SmashGGEventSummaryService().fetchUpcomingEventSummaries(leagueSlug: "rlcs-2021-22-1")
            
            if let resultData = eventResult as? PLResultSuccess {
                if let eventSummaries = resultData.data as? [Core_modelsEventSummary] {
                    eventNames = eventSummaries.map {
                        $0.tournamentName
                    }
                }
            }
            
            print(eventNames)
        } catch {
            print(error)
        }
    }
}
