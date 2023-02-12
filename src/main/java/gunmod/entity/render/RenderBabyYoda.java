package gunmod.entity.render;

import gunmod.entity.enums.BYodaState;
import gunmod.entity.models.ModelBabyYoda;
import gunmod.entity.models.ModelBabyYodaClosed;
import gunmod.entity.models.ModelBabyYodaForce;
import gunmod.entity.models.ModelBabyYodaHolding;
import gunmod.objects.entities.EntityBabyYoda;
import gunmod.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBabyYoda extends RenderLiving<EntityBabyYoda>
{
	private static final ResourceLocation CLOSED_TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entities/baby_yoda_closed.png");
	private static final ResourceLocation ARMS_TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entities/baby_yoda_with_arms.png");
	private static final ResourceLocation NORMAL_TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entities/baby_yoda.png");
	private final RenderItem itemRenderer;
	
	ModelBase modelNormal = new ModelBabyYoda();
	ModelBase modelClosed = new ModelBabyYodaClosed();
	ModelBase modelAttack = new ModelBabyYodaForce();
	ModelBase modelHappy = new ModelBabyYodaHolding();
	
	public RenderBabyYoda(RenderManager manager, RenderItem renderItem) {
		super(manager, new ModelBabyYoda(), 0.1F);
		this.itemRenderer = renderItem;
	}
	
	@Override
	protected void renderModel(EntityBabyYoda babyYoda, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		boolean flag = this.isVisible(babyYoda);
        boolean flag1 = !flag && !babyYoda.isInvisibleToPlayer(Minecraft.getMinecraft().player);

        if (flag || flag1)
        {
            if (!this.bindEntityTexture(babyYoda))
            {
                return;
            }

            if (flag1)
            {
                GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
            
            BYodaState state = babyYoda.getState();
            
            // Render different models according to state
            if (state == BYodaState.NORMAL)
            {
            	this.modelNormal.render(babyYoda, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            }
            else if (state == BYodaState.ATTACK)
            {
            	this.modelAttack.render(babyYoda, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            }
            else if (state == BYodaState.CLOSED)
            {
            	this.modelClosed.render(babyYoda, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            }
            else
            {
            	this.modelHappy.render(babyYoda, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            	this.renderItem(babyYoda);
            }
            
            if (flag1)
            {
                GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBabyYoda babyYoda) {
		BYodaState state = babyYoda.getState();
		
		// Determine texture by state (which determines the model)
		if (state == BYodaState.NORMAL)
			return NORMAL_TEXTURES;
		else if (state == BYodaState.CLOSED)
			return CLOSED_TEXTURES;
		// Both Happy model and Force model use the arms textures
		else 
			return ARMS_TEXTURES;
	}
	
	
	@Override
	protected void applyRotations(EntityBabyYoda entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		// TODO Auto-generated method stub
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
	
	private void renderItem(EntityBabyYoda babyYoda)
	{
		GlStateManager.scale(0.3F, 0.3F, 0.3F);
		GlStateManager.rotate(180, 0.0F, -0.2F, 1.0F);
		GlStateManager.translate(0F, -3F, -2.4F);
		this.itemRenderer.renderItem(babyYoda.getHeldItemStack(), ItemCameraTransforms.TransformType.FIXED);
	}
}
