/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.models.configuration;

import java.util.Optional;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class TransformerConfiguration {

    private ConfigItem<String, ConfigItem<String, String>> config = new ConfigItem<>();

    public boolean contains(String key) {
        return config.contains(key);
    }

    public TransformerConfiguration put(String key, String subKey, String value) {
        ConfigItem<String, String> item = config.get(key).orElseGet(() -> createConfigItem(key));

        item.put(subKey, value);

        return this;
    }

    private ConfigItem<String, String> createConfigItem(String key) {
        ConfigItem<String, String> result = new ConfigItem<>();
        config.put(key, result);

        return result;
    }

    public Optional<ConfigItem<String, String>> get(String key) {
        return config.get(key);
    }

    @Override
    public String toString() {
        return config.toString();
    }

}
