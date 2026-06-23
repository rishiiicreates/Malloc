# Malloc (PassBy)

Native Android scaffold for the PassBy hyperlocal marketplace.

## Local setup

1. Copy `.env.example` to `.env` and replace the placeholder values.
2. Add `app/google-services.json` when connecting a Firebase project.
3. Open this directory in Android Studio and sync Gradle.

The Firebase AI SDK is included through the Firebase BOM. The Google Services Gradle plugin is declared at the root but intentionally not applied until `google-services.json` exists, so a clean clone builds without private Firebase configuration.

All money values in the Room schema are stored as integer paise (`Long`) to avoid floating-point rounding errors. Dates and times use ISO-8601 strings at the database boundary.

