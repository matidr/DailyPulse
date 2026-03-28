//
//  AboutScreen.swift
//  iosApp
//
//  Created by Matias Di Russo on 3/18/26.
//
import SwiftUI
import Shared

struct AboutScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    
    var body: some View {
        NavigationStack {
            AboutListView().navigationTitle("About Device")
                .toolbar {
                    ToolbarItem(placement: .primaryAction) {
                        Button {
                            dismiss()
                        } label: {
                            Text("Done").bold()
                        }
                    }
                }
        }
    }
}

struct AboutListView: View {
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }
    
    private let items: [RowItem] = {
        let platform = Platform()
        platform.logSystemInfo()
        
        var result: [RowItem] = [
            .init(title: "Operating system", subtitle: "\(platform.osName) \(platform.osVersion)"),
            .init(title: "Device", subtitle: platform.deviceModel),
            .init(title: "Density", subtitle: "@\(platform.density)x")
        ]
        
        return result
    } ()
    var body: some View {
        List {
            ForEach(items, id: \.self) { item in
                VStack(alignment: .leading) {
                    Text(item.title).font(.footnote).foregroundStyle(.secondary)
                    Text(item.subtitle).font(.body).foregroundStyle(.primary)
                }.padding(.vertical, 5)
            }
        }
    }
}

#Preview {
    AboutScreen()
}
