package com.example.examplemod;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class HelloWorldScreen extends Screen {

    // コンストラクタでスクリーンのタイトルを設定
    public HelloWorldScreen() {
        super(Component.literal("Hello World Screen"));
    }

    @Override
    protected void init() {
        super.init();
        // ボタンや他のウィジェットを追加したい場合はここで追加
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // 背景の描画
        this.renderBackground(graphics, mouseX, mouseY, partialTick);

        // スーパークラス（Screen）側のウィジェット描画等
        super.render(graphics, mouseX, mouseY, partialTick);

        // "Hello World" を左上(10,10)に白色で描画
        Font font = this.font;
        graphics.drawString(
            font,                  // フォント
            "Hello World",         // 描画する文字列
            10,                    // x座標
            10,                    // y座標
            0xFFFFFF               // 文字色(白)
        );
    }

    @Override
    public boolean isPauseScreen() {
        // このスクリーンを開いている間にゲームを一時停止するかどうか
        return false;
    }
}
