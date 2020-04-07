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
package voidpointer.bukkit.framework.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import voidpointer.bukkit.framework.locale.Locale;
import voidpointer.bukkit.framework.locale.Message;

/** @author VoidPointer aka NyanGuyMF */
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public abstract class BaseValidator<T extends CommandArgs> implements CommandArgsValidator<T> {
    @NonNull private final Locale locale;
    @NonNull private final Message errorMessage;

    @Override public final boolean areValid(final T args) {
        final boolean areValid = areValid0(args);
        if (!areValid)
            onNotValid(args);
        return areValid;
    }

    protected void onNotValid(final T args) {
        /* method may be overrided by sub classes. */
        locale.getLocalized(errorMessage).send(args.getSender());
    }

    protected abstract boolean areValid0(T args);
}