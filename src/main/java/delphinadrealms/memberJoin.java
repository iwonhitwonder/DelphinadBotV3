package delphinadrealms;

import delphinadrealms.commands.SQLManager;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.managers.GuildController;

/**
 * Created by henry27 on 7/21/2017.
 */
public class memberJoin {

    public void memberJoinEvent(Event event) {
        Member member = ((GuildMemberJoinEvent) event).getMember();
        ((GuildMemberJoinEvent) event).getGuild().getTextChannelById(334190910852169729L).sendMessage("Hello "
                + ((GuildMemberJoinEvent) event).getMember().getAsMention() + " Welcome to the server!").queue();
        if (Settings.DEBUG) {
            System.out.println(((GuildMemberJoinEvent) event).getGuild().getRoles().toString()); //DEBUG
        }
        GuildController controller = ((GuildMemberJoinEvent) event).getGuild().getController();
        controller.addRolesToMember(member,(((GuildMemberJoinEvent) event).getGuild().getRoleById(334196042436182018L))).queue();
        System.out.println("Someone has joined, and they are being given the role: " + ((GuildMemberJoinEvent) event).getGuild().getRoleById(334196042436182018L));

    }

    public void memberJoinedEvent(Event event) {
        long guildID = ((GuildMemberJoinEvent) event).getGuild().getIdLong();

        if (Main.sqlManager.getServerJoinMessageEnabled(guildID)) {
            long channelID = Main.sqlManager.getServerLobbyID(guildID);
            if (Long.toString(channelID).equalsIgnoreCase("0")) {
                event.getJDA().getTextChannelById("lobby").sendMessage("Hello "
                        + ((GuildMemberJoinEvent) event).getMember().getAsMention() + " Welcome to the server!").queue();
            } else if (Long.toString(channelID).length() == 18) {
                event.getJDA().getTextChannelById(channelID).sendMessage("Hello "
                        + ((GuildMemberJoinEvent) event).getMember().getAsMention() + " Welcome to the server!").queue();
            }


        }


    }
}
