//
//  ContentView.swift
//  PocketLeagueiOS
//
//  Created by Adam McNeilly on 2/12/22.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        
        EventSummaryListItem(eventSummary: EventSummaryDisplayModel.example)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
