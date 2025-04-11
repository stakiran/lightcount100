package com.example.examplemod;

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

    /**
     * RegisterCommandsEvent でコマンドを登録する
     */
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
            Commands.literal("lightcount")
                .executes(context -> {
                    return calculateLightLevel(context.getSource());
                })
        );
    }

    /**
     * コマンドが呼び出されたときの処理
     */
    private static int calculateLightLevel(CommandSourceStack source) {
        int totalLight = 0;
        Level level = source.getLevel();
        BlockPos start = new BlockPos(-100, -64, -100);
        BlockPos end = new BlockPos(100, 64, 100);

        for (int x = start.getX(); x <= end.getX(); x++) {
            for (int y = start.getY(); y <= end.getY(); y++) {
                for (int z = start.getZ(); z <= end.getZ(); z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    // AIR のみを対象
                    if (level.getBlockState(pos).getBlock() == Blocks.AIR) {
                        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
                        // 0～15 が入る、0 が照らされてない。
                        // スコアを単純にするため、照らされてるなら +1 する程度でいい
                        totalLight += (blockLight > 0) ? 1 : 0;
                    }
                }
            }
        }

        // 実行者がプレイヤーならメッセージを送信
        if (source.getEntity() instanceof ServerPlayer player) {
            player.sendSystemMessage(Component.literal("Total lighted blocks: " + totalLight));
        }

        return totalLight;
    }
}
