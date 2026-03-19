//
//  AboutScreen.swift
//  iosApp
//
//  Created by Matias Di Russo on 3/18/26.
//
import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutListView().navigationTitle("About Device")
        }
    }
}

#Preview {
    AboutScreen()
}
