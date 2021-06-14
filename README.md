# Teleskope

🔭 Universal Mirakurun &amp; EPGStation Desktop Client (Powered by [Jetpack Compose Desktop](https://github.com/JetBrains/compose-jb)!)

Currently, this project is very experimental. There may be breaking changes. It is **not suitable for practical use** at the moment.

I'm creating this application for my Compose Desktop learning. The goal is to port [TVTest](https://github.com/DBCTRADO/TVTest) (Win32 application) and re-implement as a universal client like [MirakTest](https://github.com/ci7lus/MirakTest), but there is possibility that development will be suspended.

![screenshot.png](https://i.imgur.com/gh60oaH.png)

![screenshot.png](https://i.imgur.com/QkFVMCg.png)

## TODO

- [x] libvlc の組み込み
- [x] Mirakurun (mirakc), EPGStation API クライアントの実装
- [x] ネイティブビルド (exe, dmg, deb)
- [x] ステータスバーの実装
- [x] サービス (チャンネル) 切り替え
- [ ] 音量スライダー
- [ ] 主音声/副音声 切り替え
- [ ] プラグインシステム
- [ ] 実況コメントプラグイン
- [ ] Android 対応
- [ ] ...

## Build & Run

This project requires JDK 16 or above.

```console
$ ./gradlew build
$ ./gradlew run
```

## Acknowledgements

Teleskope uses the following thirdparty projects.

- [compose-jb](https://github.com/JetBrains/compose-jb)
- [vlcj](https://github.com/caprica/vlcj)
- [ktor](https://github.com/ktorio/ktor)
- [kaml](https://github.com/charleskorn/kaml)
- [kotlin-logging](https://github.com/MicroUtils/kotlin-logging)

## License

Teleskope is provided under the MIT license, except for some modules in Teleskope.

Module `player-component` which uses libvlc ([vlcj](https://github.com/caprica/vlcj)), and build artifacts are provided under the GPL, version 3 or later.
