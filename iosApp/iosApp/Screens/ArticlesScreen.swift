//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Matias Di Russo on 3/20/26.
//
import SwiftUI
import Shared
import Combine

struct ArticlesScreen: View {
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper

    var body: some View {
        VStack {
            AppBar()

            if let error = viewModel.state as? ArticlesState.Error {
                ErrorMessage(message: error.errorMessage)
            }

            if let success = viewModel.state as? ArticlesState.Success {
                if success.isRefreshing && success.articles.isEmpty {
                    Loader()
                } else {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(success.articles, id: \.self) { article in
                                ArticleItemView(article: article)
                            }
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct ArticleItemView: View {
    var article: ArticleUiModel
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!.resizable().aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image not available")
                } else {
                    ProgressView()
                }
            }
            Text(article.title).font(.title).fontWeight(.bold)
            Text(article.description_)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }.padding(16)
    }
}

struct AppBar: View {
    var body: some View {
        Text("Articles").font(.largeTitle).fontWeight(.bold)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    var body: some View {
        Text(message).foregroundColor(.red).font(.title)
    }
}

@MainActor
class ArticlesViewModelWrapper: ObservableObject {
    let articlesViewModel: ArticlesViewModel

    @Published var state: ArticlesState
    let effectPublisher = PassthroughSubject<ArticlesEffect, Never>()

    init() {
        articlesViewModel = ArticlesInjector().articlesViewModel
        state = articlesViewModel.state.value
    }

    func startObserving() {
        Task {
            for await state in articlesViewModel.state {
                self.state = state
            }
        }
        Task {
            for await effect in articlesViewModel.effect {
                effectPublisher.send(effect)
            }
        }
    }

    func dispatchIntent(_ intent: ArticlesIntent) {
        articlesViewModel.dispatchIntent(intent: intent)
    }
}
