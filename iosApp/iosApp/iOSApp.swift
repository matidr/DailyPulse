import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        KoinInitializerKt.doInitKoin(articlesServiceApiKey: Secrets.newsApiKey)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}