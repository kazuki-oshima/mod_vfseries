package macross.vfseries.common;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import macross.vfseries.client.Render_VF;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

@Mod(
	modid="vf_series",
	name="vf_series",
	version="1.7.10_1.0a")
public class Project_VF {
	
	//proxy
//	@SidedProxy(clientSide="macross.vfseries.client.ClientProxy_VF",
//			serverSide="macross.vfseries.common.CommonProxy_VF")

	
//	public static CommonProxy_VF proxy_VF;
	
	//generate instance
	@Instance("vfseries_instance")
	public static Project_VF instance_vf;

	//Item_VFのインスタンス
	public static Item item_vf;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try{

		}catch(Exception e){
			e.printStackTrace();
		}finally {

		}

		//itemの登録
		this.item_vf = new Item_VF().setUnlocalizedName("item_vf:item_vf");
		GameRegistry.registerItem(item_vf,"item_vf:item_vf");
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		
		EntityRegistry.registerModEntity(Entity_VF.class,"item_vf.entity",1,this,200,5,true);
//		EntityRegistry.registerModEntity(Entity_VF2.class,"item_vf.entity",1,this,200,5,true);
		
		
		RenderingRegistry.registerEntityRenderingHandler(Entity_VF.class,new Render_VF());
//		RenderingRegistry.registerEntityRenderingHandler(Entity_VF2.class,new Render_VF());
		
//		proxy_VF.registerRenderers();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent evnet){
		
	}
	
	
}
