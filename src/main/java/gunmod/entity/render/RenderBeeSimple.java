package gunmod.entity.render;

import gunmod.entity.models.ModelBee;
import gunmod.entity.models.ModelBullet;
import gunmod.objects.entities.EntityBee;
import gunmod.objects.entities.EntityBullet;
import gunmod.util.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderBeeSimple extends RenderLiving<EntityBee>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entities/bee.png");
	
	public RenderBeeSimple(RenderManager manager) {
		super(manager, new ModelBee(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBee entity) {
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityBee entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		// TODO Auto-generated method stub
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
