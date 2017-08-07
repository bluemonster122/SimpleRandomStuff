package bluemonster122.mods.simplerandomstuff.workbench;

import bluemonster122.mods.simplerandomstuff.core.block.BlockSRS;
import bluemonster122.mods.simplerandomstuff.reference.Names;
import bluemonster122.mods.simplerandomstuff.util.IFeatureRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static bluemonster122.mods.simplerandomstuff.util.ModelHelpers.registerBlockModelAsItem;

public class FRCrafters implements IFeatureRegistry {
    public static final FRCrafters INSTANCE = new FRCrafters();

    public BlockSRS crafting_table = new BlockCraftingTable();
    public BlockSRS crafting_table_auto = new BlockCraftingTable.BlockCraftingTableAuto();

    @Override
    public void registerBlocks( ) {
        GameRegistry.register(crafting_table);
        GameRegistry.register(crafting_table_auto);
    }

    @Override
    public void registerItems( ) {
        GameRegistry.register(crafting_table.createItemBlock());
        GameRegistry.register(crafting_table_auto.createItemBlock());
    }

    @Override
    public void registerRecipes( ) {
        //@formatter:off

        GameRegistry.addRecipe(new ShapelessOreRecipe(
                new ItemStack(crafting_table),
                "pressurePlateWood",
                "workbench"
        ));

        GameRegistry.addRecipe(new ShapedOreRecipe(
                new ItemStack(crafting_table_auto),
                "W",
                "G",
                "C",
                'W', "wrench",
                'G', "gearStone",
                'C', new ItemStack(crafting_table)
        ));

        //@formatter:on
    }

    @Override
    public void registerTileEntities( ) {
        GameRegistry.registerTileEntity(TileCraftingTable.class, "simplerandomstuff:crafting_table");
        GameRegistry.registerTileEntity(TileCraftingTable.TileCraftingTableAuto.class, "simplerandomstuff:crafting_table_auto");
    }

    @Override
    public void loadConfigs(Configuration configuration) {
    }

    @Override
    public void registerEvents( ) {
        /* NO OPERATION */
    }

    @Override
    public void registerOreDict( ) {
        OreDictionary.registerOre("workbench", new ItemStack(Blocks.CRAFTING_TABLE));
        OreDictionary.registerOre("pressurePlateWood", new ItemStack(Blocks.WOODEN_PRESSURE_PLATE));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerRenders( ) {
        registerBlockModelAsItem(crafting_table);
        registerBlockModelAsItem(crafting_table_auto);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerClientEvents( ) {
        /* NO OPERATION */
    }

    @Override
    public String getName( ) {
        return Names.Features.CRAFTERS;
    }

    private FRCrafters( ) {
    }
}
