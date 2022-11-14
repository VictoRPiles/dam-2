package org.example.menu;

import java.util.HashMap;

/**
 * @author Victor Piles
 */
public abstract class Menu {
    private final HashMap<Integer, String> options = new HashMap<>();

    /**
     * Adds a new option to the {@link #options} list.
     *
     * @param key   Key of the option.
     * @param value Value of the option.
     *
     * @return True if the option is added, false if the option key is duplicated.
     */
    public boolean addOption(int key, String value) {
        if (options.containsKey(key)) {
            return false;
        }
        options.put(key, value);
        return true;
    }

    /**
     * Check if the key passed is already being used.
     *
     * @param key The key to evaluate.
     *
     * @return If the key is already being used.
     */
    public boolean containsOptionWithKey(int key) {
        return options.containsKey(key);
    }

    public HashMap<Integer, String> getOptions() {
        return options;
    }
}