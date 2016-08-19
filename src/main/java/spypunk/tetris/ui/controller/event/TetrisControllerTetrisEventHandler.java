/*
 * Copyright © 2016 spypunk <spypunk@gmail.com>
 *
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */

package spypunk.tetris.ui.controller.event;

import java.util.List;

import spypunk.tetris.model.TetrisEvent;
import spypunk.tetris.ui.controller.command.TetrisControllerCommand;

@FunctionalInterface
public interface TetrisControllerTetrisEventHandler {

    List<TetrisControllerCommand> handleEvents(List<TetrisEvent> tetrisEvents);
}
