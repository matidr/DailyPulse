import SwiftUI
import Shared

struct ContentView: View {
    @StateObject private var viewModel = ArticlesViewModelWrapper()
    @State private var showAbout = false

    var body: some View {
        NavigationStack {
            ArticlesScreen(viewModel: viewModel)
                .toolbar {
                    ToolbarItem {
                        Button {
                            viewModel.dispatchIntent(ArticlesIntent.ShowAbout())
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }
                    }
                }
        }
        .refreshable {
            viewModel.dispatchIntent(ArticlesIntent.RefreshArticles())
        }
        .popover(isPresented: $showAbout) {
            AboutScreen()
        }
        .onReceive(viewModel.effectPublisher) { effect in
            if effect is ArticlesEffect.NavigateToAbout {
                showAbout = true
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
