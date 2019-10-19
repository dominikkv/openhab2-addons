/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.pidcontroller.internal;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link PIDControllerBinding} class defines common constants, which are
 * used across the whole binding.
 *
 * @author George Erhan - Initial contribution
 */
public class PIDControllerBindingConstants {

    public static final String BINDING_ID = "pidcontroller";

    // List of all Thing Type UIDs
    public final static ThingTypeUID THING_TYPE_SAMPLE = new ThingTypeUID(BINDING_ID, "Controller");

    // List of all Channel ids
    public final static String INPUT = "input";
    public final static String SETPOINT = "setpoint";
    public final static String LOOP_TIME = "LoopTime";
    public final static String OUTPUT = "output";
    public final static String KP_ADJUSTER = "kpadjuster";
    public final static String KI_ADJUSTER = "kiadjuster";
    public final static String KD_ADJUSTER = "kdadjuster";
    public final static String PID_LOWER_LIMIT = "pidlowerlimit";
    public final static String PID_UPPER_LIMIT = "pidupperlimit";

    // List of specific PID controller parameter constants
    public final static int LOOP_TIME_DEFAULT = 1000;
    public final static int PID_RANGE_DEFAULT = 510;
}