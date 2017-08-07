package bluemonster122.mods.simplerandomstuff.generators;

import bluemonster122.mods.simplerandomstuff.core.FRCore;
import bluemonster122.mods.simplerandomstuff.core.ItemMisc;
import bluemonster122.mods.simplerandomstuff.core.block.BlockSRS;
import bluemonster122.mods.simplerandomstuff.generators.BlockGenerator.Types;
import bluemonster122.mods.simplerandomstuff.reference.Names;
import bluemonster122.mods.simplerandomstuff.util.IFeatureRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static bluemonster122.mods.simplerandomstuff.util.ModelHelpers.registerIEnumMeta;

public class FRGenerators implements IFeatureRegistry {
    public static final FRGenerators INSTANCE = new FRGenerators();

    @Override
    public void registerBlocks( ) {
        GameRegistry.register(generators);
    }

    @Override
    public void registerItems( ) {
        GameRegistry.register(generators.createItemBlock());
    }

    @Override
    public void registerRecipes( ) {
        //@formatter:off

        GameRegistry.addRecipe(new ShapedOreRecipe(
                new ItemStack(generators, 1, Types.SUGAR.getMeta()),
                "WSW",
                "SMS",
                "WSW",
                'W', new ItemStack(FRCore.misc, 1, ItemMisc.Types.WOODEN_GEAR.getMeta()),
                'M', new ItemStack(FRCore.misc, 1, ItemMisc.Types.MACHINE_BASE.getMeta()),
                'S', Names.OreDict.SUGAR
        ));

        GameRegistry.addRecipe(new ShapedOreRecipe(
                new ItemStack(generators, 1, Types.FIRE.getMeta()),
                "ISI",
                "FMF",
                "ISI",
                'I', Names.OreDict.IRON_STICK,
                'S', new ItemStack(FRCore.misc, 1, ItemMisc.Types.STONE_GEAR.getMeta()),
                'F', new ItemStack(Items.FLINT_AND_STEEL),
                'M', new ItemStack(FRCore.misc, 1, ItemMisc.Types.MACHINE_BASE.getMeta())
        ));

        //@formatter:on
    }

    @Override
    public void registerTileEntities( ) {
        GameRegistry.registerTileEntity(TileGeneratorSugar.class, "simplerandomstuff:sugar_generator");
        GameRegistry.registerTileEntity(TileGeneratorFire.class, "simplerandomstuff:fire_generator");
    }

    @Override
    public void loadConfigs(Configuration configuration) {
        Sugar_RF = configuration.getInt(Names.Features.Configs.GENERATORS_SUGAR_RFPERT, Names.Features.GENERATORS, 10, 1, Integer.MAX_VALUE, "Set to any number larger than 0.");
        Sugar_Burntime = configuration.getInt(Names.Features.Configs.GENERATORS_SUGAR_BURNTIME, Names.Features.GENERATORS, 10, 1, Integer.MAX_VALUE, "Set to any number larger than 0.");
        Fire_RF = configuration.getInt(Names.Features.Configs.GENERATORS_FIRE_RFPERT, Names.Features.GENERATORS, 1, 1, Integer.MAX_VALUE, "Set to any number larger than 0.");
    }

    @Override
    public void registerEvents( ) {

    }

    @Override
    public void registerOreDict( ) {
        OreDictionary.registerOre("sugar", new ItemStack(Items.SUGAR));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerRenders( ) {
        registerIEnumMeta(generators, Types.VARIANTS);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerClientEvents( ) {

    }

    @Override
    public String getName( ) {
        return Names.Features.GENERATORS;
    }

    private FRGenerators( ) {
    }

    public static int Fire_RF = 1;
    public static int Sugar_Burntime = 10;
    public static int Sugar_RF = 10;
    public static BlockSRS generators = new BlockGenerator();
}
