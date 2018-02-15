package com.andavin.images.command;

import com.andavin.images.Images;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;

/**
 * Created on February 14, 2018
 *
 * @author Andavin
 */
final class OptionsCommand extends BaseCommand {

    OptionsCommand() {
        super("options");
        this.setAliases("list");
        this.setUsage("/image options");
        this.setDesc("Show all image file options in the image directory available to be created.");
    }

    @Override
    public void execute(final Player player, final String label, final String[] args) {

        player.sendMessage("§a§lImage Options");
        Images.getImageFiles().forEach(file -> {
            final String name = file.getName();
            player.spigot().sendMessage(new ComponentBuilder(" - ").color(ChatColor.GRAY).append(name).color(ChatColor.YELLOW)
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/image create " + name))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to create ")
                            .color(ChatColor.GREEN).append(name).color(ChatColor.YELLOW).bold(true).create())).create());
        });
    }

    @Override
    public boolean hasPermission(final Player player, final String[] args) {
        return player.hasPermission("image.manage.options");
    }
}