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
        let displayModels = viewModel.viewState.events.map {
            IdentifiableEventSummaryDisplayModel(eventSummary: $0)
        }
        List(displayModels) { event in
            EventSummaryListItem(eventSummary: event.eventSummary)
        }
        .onAppear {
            viewModel.fetchUpcomingEvents()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

struct IdentifiableEventSummaryDisplayModel : Identifiable {
    var id = UUID()
    
    var eventSummary: eventsummary.EventSummaryDisplayModel
}

@MainActor
class EventSummaryListviewModel: ObservableObject {
    @Published var viewState: EventSummaryListViewState = EventSummaryListViewState(showLoading: false, events: [], selectedEventId: nil, errorMessage: nil)
    
    func fetchUpcomingEvents() {
        let mutator = IOSEventSummaryListStateMutatorKt.iOSEventSummaryListStateMutator(onChange: { newState in
            self.viewState = newState
        })
        
        let action = EventSummaryListAction.FetchUpcomingEvents(leagueSlug: "rlcs-2021-22-1")
        
        mutator.accept(action)
    }
}
