# Speengriddy

This project was generated from a donor template by TemplateForge.
Application type: **Puzzle** — a picture puzzle game: the player picks a
picture and a difficulty, the picture is cut into pieces and shuffled,
and the pieces are swapped back into place (Jetpack Compose, Koin,
Room).

- Package: `simostudio96.speengriddy.hubapp`
- Project name: `Speengriddy`

## Where the content lives

- `app/src/main/res/values/strings.xml` — English strings, the primary
  locale. `app/src/main/res/values-<lang>/strings.xml` — the same
  resources translated into de, es, fr, hi, id, pt and ar.
- `app/src/main/res/drawable/` — the puzzle pictures, referenced as
  `R.drawable.<file name>`. It ships without any: only `icon.png` and
  the two `ic_launcher_*` files are there, and those are the app icon,
  not puzzle pictures.
- The Kotlin data files sit in the `data` package of
  `simostudio96.speengriddy.hubapp`, under `app/src/main/java/`:
  - `data/repository/PuzzleRepository.kt` — the list of puzzles. It
    ships empty.
  - `data/model/Puzzle.kt` — the shape of a puzzle: `id`, `texts` and
    `resId`.
  - `data/model/PuzzleLevel.kt` — the difficulty levels: EASY 3x3,
    MEDIUM 4x4, HARD 5x5, EXPERT 6x6. They are fixed.
  - `data/model/Localized.kt` — `forCurrentLocale()`, the lookup every
    `texts` map is read through. Nothing to fill in here.

## Data invariants

- English is the primary language. `values/strings.xml` and the `"en"`
  entry of every `texts` map are in English.
- `Puzzle.id` is the identifier, unique and numbered from 1 upwards.
  Saved records point at a puzzle by it (`game_records.puzzle_id`), the
  game screen is opened with it, and the lists use it as their key. It
  is never shown to the player.
- `Puzzle.texts` is the title shown on the cards, above the board and
  in the records list — one title per language, keyed by two-letter
  code. The `"en"` entry is mandatory: it is the fallback and the one
  the picture was chosen by. A title is one to three words in every
  language and names what the picture shows.
- Every picture is a portrait image of exactly 720 x 1280 pixels (9:16).
  This size divides evenly into all four grids (3, 4, 5, 6 pieces per
  side), apart from at most two pixels at the edge that the slicer
  drops.
- Picture files are named in lowercase snake_case (letters, digits and
  underscores, starting with a letter), which becomes
  `R.drawable.<name>`, for example `red_fox.jpg`. A file name is
  unique, and the name is independent of the titles: it is not
  translated.
- A picture suits a puzzle when detail is spread over the whole frame:
  large flat areas (clear sky, water, sand, a blank wall) make the
  pieces look the same. It contains no text, logos or watermarks.
- Every entry in `PuzzleRepository` points to a drawable that exists,
  and every puzzle picture in `drawable` is used by the repository.
- The `*_puzzle_level_title` resources name the fixed levels and the
  `*_puzzle_level_grid` resources hold the grid sizes (3x3, 4x4, 5x5,
  6x6). They are translated like any other resource, but they keep
  their meaning, and a grid size keeps its digits.
- A translation keeps the resource name and the placeholders of the
  English string (`%1$s`, `%1$d`, `%2$s`, `%%`) in the same number.
  Their order changes only if the language needs it, and then in
  positional form (`%2$s` before `%1$s`).
- A right-to-left language (Arabic, Hebrew, Persian, Urdu) reorders a
  run of digits and separators sitting inside its own text: `%1$d/%2$d`
  can reach the screen as `8/3`, and `#%1$d` or `4x4` with their parts
  swapped. Keep the placeholders in the order the English string gives
  them, and wrap each such run in left-to-right marks —
  `\u200E%1$d/%2$d\u200E` — which are invisible and change nothing
  else. It applies to every digit-and-symbol string: a grid size, a
  rank, a counter, a position pair, and a time like `1:23` substituted
  into a sentence.
- `about_rules` describes how the game actually works: pick a picture
  by swiping or from the list of all pictures; choose a difficulty, and
  a harder level cuts the picture into more pieces; tap one piece and
  then another to swap them; the light bulb shows the whole picture;
  the time is saved after each puzzle. Change it only when the game
  behaviour changes, and keep every fact and the same number of items
  in every language.
- Resources marked `translatable="false"` — the app name, the developer
  name, the contact link, the dash standing for a missing value — stay
  exactly as they are and are not copied into `values-<lang>/`;
  neither are empty resources.
- In any `strings.xml`, escape an apostrophe as `\'` and an ampersand
  as `&amp;`. A bare apostrophe is still well-formed XML, so a plain
  XML check will not catch it — aapt2 will, by failing the build.

## Text has to fit the layout

- Rephrase a string resource when it reads as generic and text written
  for the picture theme of this app would fit better. One that already
  reads naturally stays as it is — the goal is a cohesive result, not
  rewriting for its own sake. Pure interface chrome (Back, Cancel,
  Exit, Play) has no theme to fit: leave it alone.
- A replacement keeps roughly the length of the text it replaces — the
  screens were built around those lengths, and longer text wraps badly
  or gets truncated on a phone. The same goes for a translation against
  its English original.
- Menu items, buttons, filter chips, labels and titles stay short,
  usually one to three words: do not turn a label into a sentence, and
  do not add marketing filler, adjective chains, or the company name
  where the original had none.

## Working in this repo

- Keep changes scoped to what the current task asks for — this is a
  freshly generated project, not a hand-maintained one, so do not
  "clean up" unrelated files.
- Preserve the existing package structure and naming conventions found
  in the generated code instead of introducing new patterns.
- Do not modify build/gradle configuration, project name, package name,
  or app id — those were set intentionally during generation.
- Screen variants live in `v_*` packages, and the theme and typography
  in `ui/theme`; they were selected during generation and are not
  content.
- Do not run Gradle builds, dependency downloads, Android lint, or
  emulator tests. The project is published as-is after your work.
  Validate only the modified data and resource files with lightweight
  local checks.
- Do not change business logic or UI code — limit changes strictly to
  the data described above (the puzzle pictures, the puzzle list, the
  string resources and their translations). Creating the
  `values-<lang>/strings.xml` files is part of that data.
- When filling in placeholder data, match the style and format already
  used elsewhere in the file.
