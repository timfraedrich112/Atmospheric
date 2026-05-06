package mitmit.atmospheric.client.Widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

public class CustomSelectionList extends ObjectSelectionList<CustomSelectionList.Entry> {
    public CustomSelectionList(Minecraft minecraft, int width, int height, int y, int defaultEntryHeight) {
        super(minecraft, width, height, y, defaultEntryHeight);
    }

    public void addEntry(String name) {
        this.addEntry(new Entry(this, name));
    }

    @Nullable
    public String getSelectedEntry() {
        Entry selected = this.getSelected();
        return selected != null ? selected.name : null;
    }

    public static class Entry extends ObjectSelectionList.Entry<Entry> {
        private final CustomSelectionList list;
        public final String name;

        public Entry(CustomSelectionList list, String label) {
            this.list = list;
            this.name = label;
        }

        @Override
        public boolean mouseClicked(final MouseButtonEvent event, final boolean doubleClick) {
            this.list.setSelected(this);
            return true;
        }

        @Override
        public Component getNarration() {
            return Component.empty();
        }

        @Override
        public void extractContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean hovered, float a) {
            graphics.text(Minecraft.getInstance().font, name, 15, Minecraft.getInstance().font.lineHeight, 0xFFFFFF, true);
        }
    }
}
