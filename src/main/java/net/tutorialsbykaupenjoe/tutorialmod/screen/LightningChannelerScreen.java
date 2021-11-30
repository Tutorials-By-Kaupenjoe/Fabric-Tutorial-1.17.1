package net.tutorialsbykaupenjoe.tutorialmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;

public class LightningChannelerScreen extends HandledScreen<LightningChannelerScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(TutorialMod.MOD_ID, "textures/gui/lightning_channeler_gui.png");

    public LightningChannelerScreen(LightningChannelerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if(handler.isLightningStorm()) {
            this.drawTexture(matrices, x + 26, y + 31, 176, 0, 28, 36);
        }

        if(handler.isCrafting()) {
            int progress = handler.getScaledProgress();
            this.drawTexture(matrices, x + 98, y + 37, 176, 37, progress, 24);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
