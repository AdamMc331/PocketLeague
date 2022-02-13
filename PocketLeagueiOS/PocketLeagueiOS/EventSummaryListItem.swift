//
//  EventSummaryListItem.swift
//  PocketLeagueiOS
//
//  Created by Adam McNeilly on 2/12/22.
//

import Foundation
import SwiftUI

struct EventSummaryListItem: View {
    let eventSummary: EventSummaryDisplayModel
    
        var body: some View {
            ZStack {
                RoundedRectangle(cornerRadius: 25, style: .continuous)
                    .fill(.white)
                    .shadow(radius: 10)
    
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
                .padding(20)
                .multilineTextAlignment(.center)
            }
            .frame(maxWidth: .infinity, maxHeight: 0)
            .padding(20)
        }
}

struct EventSummaryListItem_Previews: PreviewProvider {
    static var previews: some View {
        EventSummaryListItem(eventSummary: EventSummaryDisplayModel.example)
    }
}
