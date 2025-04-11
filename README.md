# lightcount100
- [100マス松明](https://scrapbox.io/stao/100%E3%83%9E%E3%82%B9%E6%9D%BE%E6%98%8E)を遊ぶためのMODです
- lightcount コマンドが追加されます
    - 実行すると、worldborder 100 の地下（y=64以下）のうち、照らされている空気ブロックの数をカウントします

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
