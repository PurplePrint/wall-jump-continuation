package genandnic.walljump.client;

import genandnic.walljump.Tags;
import genandnic.walljump.WallJumpConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FallingHud extends Gui {
    private static final ResourceLocation ICON = new ResourceLocation(Tags.MOD_ID, "textures/gui/falling.png");
    public static boolean showFallingHUD = false;

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (!WallJumpConfig.showFallingHud) {
            return;
        }

        if (!showFallingHUD) {
            return;
        }

        if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player.isCreative() || mc.player.isSpectator()) {
            return;
        }

        ScaledResolution scaledResolution = new ScaledResolution(mc);

        int centerX = (int) (scaledResolution.getScaledWidth() / 2);
        int centerY = (int) (scaledResolution.getScaledHeight() / 2);

        // white color for icon outline
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        mc.getTextureManager().bindTexture(ICON);
        Gui.drawModalRectWithCustomSizedTexture(centerX + WallJumpConfig.fallingHudX, centerY + WallJumpConfig.fallingHudY, 0, 0, 17, 24, 17, 24);

    }
}
