# lightcount100
[100マス松明](https://scrapbox.io/stao/100%E3%83%9E%E3%82%B9%E6%9D%BE%E6%98%8E)を遊ぶためのMODです。

- 100マス松明の遊び方は解説しません
- この MOD を導入すると、lightcount コマンドが追加されます
    - このコマンドを実行すると、worldborder 100 の地下（y=64以下）のうち、照らされている空気ブロックの数をカウントします。

![Image](https://github.com/user-attachments/assets/ab40dd51-3260-4a15-aa25-bbc1ce5d7be1)

# この MOD で遊ぶ
- 1: お使いのマイクラに Forge 1.25.1 をインストール
- 2: マイクラランチャーの起動構成から Forge 1.25.1 を使う構成をつくって、適当にワールドつくってプレイ
- これで mods/ フォルダが生成されるので、
- 3: Release ページから、この MOD の jar ファイルを入手して、mods/ フォルダに置く

上手くいくと、以下のように MOD がロードされるはず。

![Image](https://github.com/user-attachments/assets/c94af4e1-0a6c-4d56-92b0-15dbe87a5e71)

# (開発用README)
[Introduction - Forge Documentation](https://docs.minecraftforge.net/en/latest/gettingstarted/) に従えばいいが、ここでもメモしとく

## 要件
- Forge 1.21.5
- Eclipse Temurin 21 LTS
- Eclipse ではなく VSCode を使います

## 初期設定
`./gradlew genVSCodeRuns`

## デバッグ
- F5 キー
- あるいは Run and Debug サイドバーの runClient

## ビルド
`./gradlew build`

実行すると build/libs/*.jar が生成される。

# LICENSE
[MIT](LICENSE)
