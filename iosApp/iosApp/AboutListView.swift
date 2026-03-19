//
//  Untitled.swift
//  iosApp
//
//  Created by Matias Di Russo on 3/18/26.
//
import SwiftUI
import Shared

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
    AboutListView()
}
