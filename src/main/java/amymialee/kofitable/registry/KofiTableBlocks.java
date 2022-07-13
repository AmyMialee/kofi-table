package amymialee.kofitable.registry;

import amymialee.kofitable.KofiTable;
import amymialee.kofitable.blocks.KofiTableBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class KofiTableBlocks {
    public static final Block KOFI_TABLE = registerBlock("kofi_table", new KofiTableBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).nonOpaque().strength(2.0f).sounds(BlockSoundGroup.WOOD)));

    public static void init() {}

    private static Block registerBlock(String name, Block block) {
        return registerBlock(name, new BlockItem(block, new FabricItemSettings().group(KofiTable.KOFI_GROUP)));
    }

    private static Block registerBlock(String name, BlockItem block) {
        Registry.register(Registry.BLOCK, KofiTable.id(name), block.getBlock());
        KofiTableItems.registerItem(name, block);
        return block.getBlock();
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
}