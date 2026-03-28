//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Matias Di Russo on 3/28/26.
//
import SwiftUI
import Shared
import Combine

struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    
    @StateObject private var viewModel = SourcesViewModelWrapper()
    var body: some View {
        NavigationStack {
            ZStack {
                switch viewModel.state {
                case let error as SourcesState.Error:
                    ErrorMessage(message: error.errorMessage)
                case let success as SourcesState.Success:
                    if success.isRefreshing && success.sources.isEmpty {
                        Loader()
                    } else {
                        SourcesListView(sources: success.sources)
                    }
                default:
                    EmptyView()
                }
            }.navigationTitle("Sources").toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done").bold()
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct SourcesListView: View {
    var sources: [SourceUiModel]
    var body: some View {
        ScrollView {
            LazyVStack {
                ForEach(sources, id: \.self) { source in
                    SourceListItemView(source: source)
                }
            }
        }
    }
}

struct SourceListItemView: View {
    var source: SourceUiModel
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(source.name).font(.title).fontWeight(.bold)
            Text(source.description_)
            Text(source.locale).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }.padding(16)
    }
}

@MainActor
class SourcesViewModelWrapper: ObservableObject {
    let sourcesViewModel: SourcesViewModel
    
    @Published var state: SourcesState
    let effectPublisher = PassthroughSubject<SourcesEffect, Never>()
    
    init() {
        sourcesViewModel = SourcesInjector().sourcesViewModel
        state = sourcesViewModel.state.value
    }
    
    func startObserving() {
        Task {
            for await state in sourcesViewModel.state {
                self.state = state
            }
        }
        Task {
            for await effect in sourcesViewModel.effect {
                effectPublisher.send(effect)
            }
        }
    }
    
    func dispatchIntent(_ intent: SourcesIntent) {
        sourcesViewModel.dispatchIntent(intent: intent)
    }
}
