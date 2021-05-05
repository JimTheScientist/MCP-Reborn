package net.minecraft.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.IBidiRenderer;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.IOException;
import java.io.InputStream;

@OnlyIn(Dist.CLIENT)
public class VersionScreen extends Screen {
   private final Screen field_230156_a_;
   private static final ITextComponent field_230157_b_ = (new TranslationTextComponent("Version Switching")).mergeStyle(TextFormatting.BOLD);
   private static final ITextComponent field_230158_c_ = new TranslationTextComponent("This is an experimental feature that servers you play on might not allow!");
   private static final ITextComponent field_230159_d_ = new TranslationTextComponent("I know! Let me switch versions now.");
   private static final ITextComponent field_238858_q_ = field_230157_b_.deepCopy().appendString("\n").append(field_230158_c_);
   private CheckboxButton field_230162_g_;
   private IBidiRenderer field_243364_s = IBidiRenderer.field_243257_a;

   public VersionScreen(Screen p_i230052_1_, GameSettings gameSettings, LanguageManager languageManager) {
      super(NarratorChatListener.EMPTY);
      this.field_230156_a_ = p_i230052_1_;
   }

   protected void init() {
      super.init();
      this.field_243364_s = IBidiRenderer.func_243258_a(this.font, field_230158_c_, this.width - 50);
      int i = (this.field_243364_s.func_241862_a() + 1) * 9 * 2;
      this.addButton(new Button(this.width / 2 - 155, 100 + i, 150, 20, DialogTexts.GUI_PROCEED, (p_230165_1_) -> {
         if (this.field_230162_g_.isChecked()) {
            this.minecraft.displayGuiScreen(new MainMenuScreen());
            // PUT CODE HERE REEEEEEE
            Process proc = null;
            try {
               proc = Runtime.getRuntime().exec("java -jar 1.8.9.jar");
            } catch (IOException e) {
               e.printStackTrace();
            }
            InputStream in = proc.getInputStream();
            InputStream err = proc.getErrorStream();
         }
      }));
      this.addButton(new Button(this.width / 2 - 155 + 160, 100 + i, 150, 20, DialogTexts.GUI_BACK, (p_230164_1_) -> {
         this.minecraft.displayGuiScreen(this.field_230156_a_);
      }));
      this.field_230162_g_ = new CheckboxButton(this.width / 2 - 155 + 80, 76 + i, 150, 20, field_230159_d_, false);
      this.addButton(this.field_230162_g_);
   }

   public String getNarrationMessage() {
      return field_238858_q_.getString();
   }

   public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
      this.renderDirtBackground(0);
      drawString(matrixStack, this.font, field_230157_b_, 25, 30, 16777215);
      this.field_243364_s.func_241865_b(matrixStack, 25, 70, 9 * 2, 16777215);
      super.render(matrixStack, mouseX, mouseY, partialTicks);
   }
}