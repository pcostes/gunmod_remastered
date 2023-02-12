package gunmod.entity.render;

import gunmod.entity.models.ModelBee;
import gunmod.objects.entities.EntityAngryBee;
import gunmod.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAngryBee extends RenderLiving<EntityAngryBee>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entities/angry_bee.png");
	
	public RenderAngryBee(RenderManager manager) {
		super(manager, new ModelBee(), 0.1F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityAngryBee entity) {
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityAngryBee entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		// TODO Auto-generated method stub
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}