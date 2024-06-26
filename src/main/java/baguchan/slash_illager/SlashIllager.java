package baguchan.slash_illager;


import baguchan.slash_illager.message.NetworkManager;
import baguchan.slash_illager.registry.ModEntityRegistry;
import baguchan.slash_illager.registry.ModItemRegistry;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SlashIllager.MODID)
public class SlashIllager
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "slash_illager";


    public SlashIllager()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        ModItemRegistry.ITEM_REGISTRY.register(modEventBus);
        ModEntityRegistry.ENTITIES_REGISTRY.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        NetworkManager.register();

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
  }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        Raid.RaiderType.create("blade_master", ModEntityRegistry.BLADE_MASTER.get(), new int[]{0, 0, 0, 0, 1, 1, 1, 2});
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ModItemRegistry.BLADE_MASTER_SPAWNEGG.get());
        }
    }
}
