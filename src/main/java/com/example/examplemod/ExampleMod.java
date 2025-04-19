package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(ExampleMod.MODID)
@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExampleMod {
    public static final String MODID = "lightcounter100";
    public static final int BORDER_SIZE = 100;

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
            Commands.literal("lightcount")
                .executes(context -> {
                    return calculateLightLevel(context.getSource());
                })
        );
        event.getDispatcher().register(
            Commands.literal("lightcount-start")
                .executes(context -> {
                    return startCount(context.getSource());
                })
        );
    }

    private static int startCount(CommandSourceStack source) {
        Minecraft.getInstance().setScreen(new HelloWorldScreen());

        if (source.getEntity() instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("lightcount: Timer start."));
        }

        return 0;
    }

    private static int calculateLightLevel(CommandSourceStack source) {
        int darknessScore = 0;
        int unloaded = 0;
        Level level = source.getLevel();
        int boundaryValue = BORDER_SIZE/2;
        BlockPos start = new BlockPos(-boundaryValue, -64, -boundaryValue);
        BlockPos end = new BlockPos(boundaryValue, 64, boundaryValue);

        for (int x = start.getX(); x <= end.getX(); x++) {
            for (int y = start.getY(); y <= end.getY(); y++) {
                for (int z = start.getZ(); z <= end.getZ(); z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    // まだチャンクがロードされていない場合はスキップ
                    if (!level.isLoaded(pos)) {
                        unloaded += 1;
                        continue;
                    }
                    // AIR のみを対象
                    if (level.getBlockState(pos).getBlock() == Blocks.AIR) {
                        // 0～15 が入る、0 が照らされてない
                        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
                        darknessScore += (blockLight == 0) ? 1 : 0;
                    }
                }
            }
        }

        // 実行者がプレイヤーならメッセージを送信
        if (source.getEntity() instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("暗闇スコア: " + darknessScore + "(" + unloaded + ")"));
        }

        return darknessScore;
    }
}
