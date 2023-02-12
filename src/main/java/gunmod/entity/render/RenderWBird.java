package gunmod.entity.render;

import gunmod.entity.models.ModelWBird;
import gunmod.objects.entities.EntityWBird;
import gunmod.util.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWBird extends Render<EntityWBird>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":textures/entities/w_bird.png");

    private final ModelWBird model = new ModelWBird();

    public RenderWBird(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    private float getRenderYaw(float p_82400_1_, float p_82400_2_, float p_82400_3_)
    {
        float f;

        for (f = p_82400_2_ - p_82400_1_; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return p_82400_1_ + p_82400_3_ * f;
    }

    public void doRender(EntityWBird entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	// Stuff i don't understand
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        
        // Get entity yaw a pitch (currently not used)
        float yaw = this.getRenderYaw(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
        float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        
        
        // TODO This code is from laser, adjust as needed
        GlStateManager.translate((float)x, (float)y - 0.185, (float)z);
        
        // scaleFactor is never used in rendering but it is available for use when rendering the model
        float scaleFactor = 0.0625F;
        
        // More stuff i don't understand why it is here
        GlStateManager.enableRescaleNormal();
        //GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        GlStateManager.scale(0.14, 0.14, 0.14);
        GlStateManager.enableAlpha();
        this.bindEntityTexture(entity);
        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }
        
        // The model is actually rendered here
        this.model.render(entity, 0.0F, 0.0F, 0.0F, yaw, pitch, scaleFactor);
        
        // This code seems to cancel out the properties that were set earlier
        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }
        
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntityWBird entity)
    {
        return TEXTURE;
    }
}
