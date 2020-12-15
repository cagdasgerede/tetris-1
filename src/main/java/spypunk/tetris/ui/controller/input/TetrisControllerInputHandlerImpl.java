/*
 * Copyright © 2016-2017 spypunk <spypunk@gmail.com>
 *
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */

package spypunk.tetris.ui.controller.input;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import spypunk.tetris.ui.controller.command.TetrisControllerCommand;
import spypunk.tetris.ui.controller.command.cache.TetrisControllerCommandCache;
import spypunk.tetris.ui.controller.command.cache.TetrisControllerCommandCache.TetrisControllerCommandType;
import spypunk.tetris.ui.view.TetrisControlsHandler;

@Singleton
public class TetrisControllerInputHandlerImpl implements TetrisControllerInputHandler {

    private final Set<TetrisControllerCommandType> triggeredCommands = Sets.newConcurrentHashSet();

    private final Map<Integer, TetrisControllerCommandType> pressedKeyEventCommandTypes = Maps.newHashMap();

    private final Map<Integer, TetrisControllerCommandType> releasedKeyEventCommandTypes = Maps.newHashMap();

    private final TetrisControllerCommandCache tetrisControllerCommandCache;

    ArrayList<Integer> controls = new ArrayList<Integer>();
    
    TetrisControlsHandler tetrisControlsHandler;

    @Inject
    public TetrisControllerInputHandlerImpl(final TetrisControllerCommandCache tetrisControllerCommandCache,
            final TetrisControlsHandler tetrisControlsHandler) {
        this.tetrisControllerCommandCache = tetrisControllerCommandCache;
        this.tetrisControlsHandler = tetrisControlsHandler;
        if (tetrisControlsHandler.getControls().size() == 0) {
            tetrisControlsHandler.setDefault();
        }

        controls = tetrisControlsHandler.getControls();
        setControls(controls);
    }

    @Override
    public void onKeyPressed(final int keyCode) {
        onKey(keyCode, pressedKeyEventCommandTypes);
    }

    @Override
    public void onKeyReleased(final int keyCode) {
        onKey(keyCode, releasedKeyEventCommandTypes);
    }

    @Override
    public void onProjectURLClicked() {
        triggeredCommands.add(TetrisControllerCommandType.OPEN_PROJECT_URL);
    }

    @Override
    public void handleInputs() {
        if (triggeredCommands.isEmpty()) {
            return;
        }
        triggeredCommands.stream()
                .map(tetrisControllerCommandCache::getTetrisControllerCommand)
                .forEach(TetrisControllerCommand::execute);

        triggeredCommands.clear();
    }

    private void onKey(final int keyCode, final Map<Integer, TetrisControllerCommandType> keyEventCommandTypes) {
        if (keyEventCommandTypes.containsKey(keyCode)) {
            final TetrisControllerCommandType commandType = keyEventCommandTypes.get(keyCode);

            triggeredCommands.add(commandType);
        }
    }

    public ArrayList<Integer> getControls() {
        return controls;
    }

    private void setControls(ArrayList<Integer> control) {

        pressedKeyEventCommandTypes.put(controls.get(0), TetrisControllerCommandType.LEFT);
        pressedKeyEventCommandTypes.put(controls.get(1), TetrisControllerCommandType.RIGHT);
        pressedKeyEventCommandTypes.put(controls.get(2), TetrisControllerCommandType.DOWN);

        releasedKeyEventCommandTypes.put(controls.get(3), TetrisControllerCommandType.ROTATE);
        releasedKeyEventCommandTypes.put(controls.get(4), TetrisControllerCommandType.NEW_GAME);
        releasedKeyEventCommandTypes.put(controls.get(5), TetrisControllerCommandType.PAUSE);
        releasedKeyEventCommandTypes.put(controls.get(6), TetrisControllerCommandType.MUTE);
        releasedKeyEventCommandTypes.put(controls.get(7), TetrisControllerCommandType.INCREASE_VOLUME);
        releasedKeyEventCommandTypes.put(controls.get(8), TetrisControllerCommandType.DECREASE_VOLUME);
        releasedKeyEventCommandTypes.put(controls.get(9), TetrisControllerCommandType.HARD_DROP);

    }

    public void changeControls(ArrayList<Integer> controllers) {
        if (controls != controllers) {
            for (int i = 0; i < controls.size(); i++) {
                if (controls.get(i) != controllers.get(i)) {
                    if (i < 3) {
                        pressedKeyEventCommandTypes.remove(controls.get(i));
                        controls.set(i, controllers.get(i));
                        setControls(controls);
                    } else {

                        releasedKeyEventCommandTypes.remove(controls.get(i));
                        controls.set(i, controllers.get(i));
                        setControls(controls);
                    }
                }
            }
        }

        tetrisControlsHandler.setNewControllers(controls);
    }
}
