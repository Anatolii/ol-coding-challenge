# Mobile Developer Coding Challenge

Build a small, high-quality mobile app that displays and lets users browse a grid of curated photos from Unsplash, then view each photo fullscreen with metadata.

- **Platform & Language**  
  - **Android:** Kotlin + Jetpack Compose  
  - **iOS:** Swift 5+ + SwiftUI  

- **API Docs:**  
  https://unsplash.com/documentation  


## Functional Requirements

1. **Grid View**  
   - Paginated, infinite-scrolling grid of photos  
   - Preserves each image’s aspect ratio (no cropping)  
   - Supports both portrait & landscape orientations  

2. **Detail View**  
   - Tap any grid item to open fullscreen detail  
   - Displays photo, author name, description/alt text, like count, etc.  
   - Swipe left/right to navigate between photos  
   - On dismiss, grid scroll position retains the last-viewed photo   


## Quality & Delivery

- **Repository Setup**  
  - Fork from the `mobile-coding-challenge` repo
  - Keep the fork public until the review is completed

- **Commit Hygiene**  
  - Small, atomic commits with descriptive messages 

## Bonus (Pick Any)

- Unit tests
- Dark mode support
- Offline caching of the entire photo feed using a local DB
- Share a photo link via the system share sheet  
- Custom transition animation between grid ⇄ detail  

## Evaluation Criteria

- **Correctness:** Builds, runs, meets core requirements, no crashes  
- **Code Quality:** SOLID principles, clean layering, readability  
- **Architecture:** Separation of concerns, testability  
- **UX Polish:** Smooth scrolling, placeholders, error handling  
- **Git Hygiene:** Clear history

------------
# Launch instructions

In order to compile the project it is necessary to provide Unsplash API key to the build.
There are two ways to do it:

1. Add following property to the `local.properties` file:
```properties
unsplash.access.key=<YOUR_UNSPLASH_ACCESS_KEY>
```
2. Pass it to the build in command line as system property. For example:
```shell
./gradlew build -DUNSPLASH_API_KEY=<YOUR_UNSPLASH_ACCESS_KEY>
```

# Features
- Supports both portrait & landscape orientations
- Landscape orientation uses two panel layout
- Supports dark mode via system dynamic color schema
- Share a photo link via the system share sheet
- Highlights currently selected photo in the grid when in landscape orientation or on a tablet

## TODO
- Add tests
- Add offline caching of the entire photo feed using a local DB
- Add custom transition animation between grid ⇄ detail