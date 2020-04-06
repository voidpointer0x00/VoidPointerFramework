/*
 * This file is part of VoidPointerFramework Bukkit plug-in.
 *
 * VoidPointerFramework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VoidPointerFramework is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VoidPointerFramework. If not, see <https://www.gnu.org/licenses/>.
 */
package voidpointer.bukkit.framework.locale;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import lombok.NonNull;

/**
 * This interface is localized {@link Message} version.
 * <p>
 * {@link Locale} class operates with {@link Message} objects
 *      and gets right {@link LocalizedMessage} instance.
 * <p>
 * The interface is build in a way that you can use it just like a
 *      Builder pattern, i.e.
 * <pre>
 * LocalizedMessage message = ...;
 * message.set("player-name", player.getName())
 *         .set("target-name", target.getName())
 *         .colorize()
 *         .send(Bukkit.getOnlinePlayers());
 * </pre>
 *
 * @author VoidPointer aka NyanGuyMF
 */
public interface LocalizedMessage {
    String PLACEHOLDER_START = "{";
    String PLACEHOLDER_END = "}";
    char CUSTOM_COLOR_CODE = '&';

    /** Gets final values for message. */
    String getValue();

    /** Sends message to specified receiver. */
    default LocalizedMessage send(@NonNull final CommandSender receiver) {
        receiver.sendMessage(getValue());
        return this;
    }

    /**
     * Sets placeholder in message to specified value.
     * <p>
     * The «placeholder» is some value surrounded by
     *      {@link #PLACEHOLDER_START} and {@link #PLACEHOLDER_END}.
     * <p>
     * For example, if placeholder starts and ends by the <tt>'{'</tt>
     *      and <tt>'}'</tt> resp., you can pass <tt>"player-name"</tt>
     *      as placeholder and <tt>"jeb_"</tt> as a value and this method
     *      will replace all the <tt>"{player-name}"</tt> occurences
     *      in message with specified value — <tt>"jeb_"</tt>.
     */
    LocalizedMessage set(@NonNull String placeholder, @NonNull String value);

    /**
     * Makes message colorful for server representation.
     * <p>
     * Bukkit uses {@link ChatColor#COLOR_CHAR} for color represenation,
     *      while most configuration uses more user-friendly color
     *      codes, so this method translates user-friendly color code
     *      into Bukkit-friendly.
     *
     * @see #CUSTOM_COLOR_CODE
     * @see ChatColor#translateAlternateColorCodes(char, String)
     */
    LocalizedMessage colorize();

    /**
     * @param customColorCode Custom color code if you don't want to
     *      use {@link #CUSTOM_COLOR_CODE}.
     * @see #colorize()
     */
    LocalizedMessage colorize(char customColorCode);

    /**
     * @param customColorCode Custom color code if you don't want to
     *      use {@link #CUSTOM_COLOR_CODE}.
     * @see #colorize()
     */
    LocalizedMessage colorize(@NonNull String customColorCode);
}
