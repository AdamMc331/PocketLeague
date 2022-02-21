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
        let mutator = EventSummaryListStateMutatorKt.defaultMutator()
        
        EventSummaryListStateMutatorKt.onChange(mutator, onChange: { newState in
            self.eventNames = newState.events.map {
                $0.tournamentName
            }
        })
        eventNames = ["Testy", "McTest"]
        
        let action = EventSummaryListAction.FetchUpcomingEvents(leagueSlug: "rlcs-2021-22-1")
        
        mutator.accept(action)
    }
}
