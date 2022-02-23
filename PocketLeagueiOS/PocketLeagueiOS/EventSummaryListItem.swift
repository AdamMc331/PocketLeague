//
//  EventSummaryListItem.swift
//  PocketLeagueiOS
//
//  Created by Adam McNeilly on 2/12/22.
//

import Foundation
import SwiftUI
import eventsummary

struct EventSummaryListItem: View {
    let eventSummary: EventSummaryDisplayModel
    
        var body: some View {
            VStack(alignment: HorizontalAlignment.leading, spacing: 4, content: {
                Text(eventSummary.startDate)
                    .font(.caption)
                
                Text(eventSummary.tournamentName)
                    .font(.headline)
                
                Text(eventSummary.eventName)
                    .font(.subheadline)
                
                if let subtitle = eventSummary.subtitle {
                    Text(subtitle)
                        .font(.caption)
                }
            })
        }
}

struct EventSummaryListItem_Previews: PreviewProvider {
    static var previews: some View {
        EventSummaryListItem(eventSummary: EventSummaryDisplayModel(eventId: "EventID", startDate: "TODO: start date", tournamentName: "RLCS 2021-22 Season - Winter Split Regional 3 - Europe", eventName: "Main Event", subtitle: "16 Teams", image: nil))
    }
}
