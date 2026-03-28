import SwiftUI
import Shared

struct ContentView: View {
    @StateObject private var articlesViewModel = ArticlesViewModelWrapper()
    @StateObject private var sourcesViewModel = SourcesViewModelWrapper()
    @State private var showAbout = false
    @State private var showSources = false

    var body: some View {
        NavigationStack {
            ArticlesScreen(viewModel: articlesViewModel)
                .toolbar {
                    ToolbarItem {
                        Button {
                            articlesViewModel.dispatchIntent(ArticlesIntent.OnSourcesClicked())
                        } label: {
                            Label("Sources", systemImage: "list.dash").labelStyle(.titleAndIcon)
                        }
                    }
                    ToolbarItem {
                        Button {
                            articlesViewModel.dispatchIntent(ArticlesIntent.OnAboutClicked())
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }
                    }
                }
        }
        .refreshable {
            articlesViewModel.dispatchIntent(ArticlesIntent.OnRefreshSwiped())
        }
        .popover(isPresented: $showAbout) {
            AboutScreen()
        }
        .popover(isPresented: $showSources) {
            SourcesScreen()
        }
        .onReceive(articlesViewModel.effectPublisher) { effect in
            switch(effect) {
            case is ArticlesEffect.NavigateToSources:
                showSources = true
                break
            case is ArticlesEffect.NavigateToAbout:
                showAbout = true
                break
            default:
                break
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
