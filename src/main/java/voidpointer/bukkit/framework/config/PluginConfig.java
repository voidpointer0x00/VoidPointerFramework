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
package voidpointer.bukkit.framework.config;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * Abstract implementation of {@link Config} interface using Bukkit
 *      {@link YamlConfiguration} class.
 * <p>
 * This implementaiton will try to save configuraiton file from
 *      plugin resources if it does not exits.
 *
 * @author VoidPointer aka NyanGuyMF
 */
public abstract class PluginConfig extends AbstractPluginConfig {
    /**
     * Create new {@link PluginConfig} instance for configuration
     *      file with specified name in plugin's data folder.
     */
    public PluginConfig(final Plugin pluginOwner, final String configFilename) {
        this(pluginOwner, pluginOwner.getDataFolder(), configFilename);
    }

    /**
     * Create new {@link PluginConfig} instance for configuration file
     *      with specified name in specified configuration folder.
     */
    public PluginConfig(
            final Plugin pluginOwner,
            final File configFolder,
            final String configFilename
    ) {
        super(pluginOwner, configFolder, configFilename);
    }

    @Override public CompletableFuture<Boolean> load() {
        return CompletableFuture.supplyAsync(() -> {
            return load(super.getConfigFilename());
        });
    }

    @Override public CompletableFuture<Boolean> loadLocalized(final String locale) {
        return CompletableFuture.supplyAsync(() -> {
            setCurrentLocale(locale);
            final String filename = formatLocalizedFilename(locale);
            return load(filename);
        });
    }

    @Override public CompletableFuture<Boolean> save() {
        return CompletableFuture.supplyAsync(() -> {
            return super.saveYamlConfiguration();
        });
    }

    @Override public CompletableFuture<Boolean> reload() {
        return CompletableFuture.supplyAsync(() -> {
            return super.reloadYamlConfiguration();
        });
    }

    @Override public CompletableFuture<Boolean> unload() {
        return CompletableFuture.supplyAsync(() -> {
            setConfig(null);
            return true;
        });
    }

    @Override public boolean isLoaded() {
        return getConfig() != null;
    }

    @Override public void close() throws IOException {
        /* nothing to close */
    }
}
