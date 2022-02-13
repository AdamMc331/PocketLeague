//
//  EventSummaryDisplayModel.swift
//  PocketLeagueiOS
//
//  Created by Adam McNeilly on 2/12/22.
//

import Foundation

struct EventSummaryDisplayModel {
    let startDate: String
    let tournamentName: String
    let eventName: String
    let subtitle: String?
    
    static let example = EventSummaryDisplayModel(startDate: "FEB 18, 2022", tournamentName: "RLCS 2021-22 Season - Winter Split Regional 3 - Oceania", eventName: "Main Event", subtitle: "8 Teams")
}
