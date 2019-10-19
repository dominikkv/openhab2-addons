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

import static org.openhab.binding.pidcontroller.internal.PIDControllerBindingConstants.THING_TYPE_SAMPLE;

import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.pidcontroller.internal.handler.PIDControllerHandler;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link PIDControllerHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author George Erhan - Initial contribution
 */
@Component(service = ThingHandlerFactory.class, configurationPid = "binding.pidcontroller")
public class PIDControllerHandlerFactory extends BaseThingHandlerFactory {
    private Logger logger = LoggerFactory.getLogger(PIDControllerHandlerFactory.class);
    private final static Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(THING_TYPE_SAMPLE);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {
        logger.debug("asta e handlerul: {}", THING_TYPE_SAMPLE);
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(THING_TYPE_SAMPLE)) {

            return new PIDControllerHandler(thing);
        }

        return null;
    }
}
