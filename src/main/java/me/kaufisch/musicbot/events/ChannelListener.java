package me.kaufisch.musicbot.events;

import me.kaufisch.musicbot.utils.Const;
import me.kaufisch.musicbot.main.Main;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Kaufisch
 */
public class ChannelListener extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        try {
            if (event.getChannelJoined().getId().equals(Const.MusicChannel)) {
                if (!event.getMember().getUser().isBot()) {
                    Main.userInChannel.add(event.getMember());
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) throws NullPointerException {
        if (event.getChannelLeft().getId().equals(Const.MusicChannel)) {
            if (!event.getMember().getUser().isBot()) {
                Main.userInChannel.remove(event.getMember());
            }
        }
    }

    @Override
    public void onGuildVoiceMove(@NotNull GuildVoiceMoveEvent event) {
        try {
            if (event.getChannelLeft().getId().equals(Const.MusicChannel)) {
                if (!event.getMember().getUser().isBot()) {
                    Main.userInChannel.remove(event.getMember());
                }
            }
            if (event.getChannelJoined().getId().equals(Const.MusicChannel)) {
                if (!event.getMember().getUser().isBot()) {
                    Main.userInChannel.add(event.getMember());
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}
