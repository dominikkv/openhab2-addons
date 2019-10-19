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

/**
 *
 * @author George Erhan - Initial contribution
 */

package org.openhab.binding.pidcontroller.internal;

import static org.openhab.binding.pidcontroller.internal.PIDControllerBindingConstants.PID_RANGE_DEFAULT;

import java.math.BigDecimal;

/**
 * The {@link Controller} provides the necessary methods for retrieving part(s) of the PID calculations
 * and it provides the method for the overall PID calculations. It also resets the PID controller
 *
 * @author George Erhan - Initial contribution
 */

public class Controller {
    private BigDecimal derivativeResult = BigDecimal.ZERO;
    private BigDecimal proportionalResult = BigDecimal.ZERO;
    private BigDecimal integralResult = BigDecimal.ZERO;
    private BigDecimal previousError = BigDecimal.ZERO;
    private BigDecimal error = BigDecimal.ZERO;
    private BigDecimal output = BigDecimal.ZERO;
    private BigDecimal kp;
    private BigDecimal ki;
    private BigDecimal kd;

    public BigDecimal pidCalculation(BigDecimal pidInput, BigDecimal pidSetpoint, BigDecimal loopTime,
            BigDecimal pidOutputLowerLimit, BigDecimal pidOutputUpperLimit, BigDecimal kpAdjuster,
            BigDecimal kiAdjuster, BigDecimal kdAdjuster) {

        BigDecimal ku = pidOutputUpperLimit.subtract(pidOutputLowerLimit).divide(BigDecimal.valueOf(PID_RANGE_DEFAULT));

        kp = kpAdjuster.multiply(ku);
        ki = kiAdjuster.multiply(ku.multiply(BigDecimal.valueOf(2)).divide(loopTime));
        kd = kdAdjuster.multiply(ku.multiply(loopTime));

        BigDecimal maxIntegral = pidOutputUpperLimit.abs().subtract((kp.multiply(proportionalResult).abs())).divide(ki);

        error = pidSetpoint.subtract(pidInput);
        proportionalResult = error;
        integralResult = integralResult.add(error.multiply(loopTime));

        if (integralResult.abs().compareTo(maxIntegral.abs()) > 0) {

            if (output.compareTo(BigDecimal.ZERO) < 0) {
                integralResult = maxIntegral.negate();
            } else {
                integralResult = maxIntegral;
            }
        }
        if ((integralResult.compareTo(BigDecimal.ZERO) < 0 && error.compareTo(BigDecimal.ZERO) > 0)
                || (integralResult.compareTo(BigDecimal.ZERO) > 0 && error.compareTo(BigDecimal.ZERO) < 0)) {
            integralResult = BigDecimal.ZERO;
        }
        derivativeResult = error.subtract(previousError).divide(loopTime);
        output = kp.multiply(proportionalResult).add(ki.multiply(integralResult)).add(kd.multiply(derivativeResult));
        previousError = error;
        return output;

    }

    public BigDecimal getProportionalpart() {
        BigDecimal proportional = kp.multiply(proportionalResult);
        return proportional;
    }

    public BigDecimal getIntegralpart() {
        BigDecimal integral = ki.multiply(integralResult);
        return integral;
    }

    public BigDecimal getDerivativepart() {
        BigDecimal derivative = kd.multiply(derivativeResult);
        return derivative;
    }

    public void ResetPID() {
        derivativeResult = BigDecimal.ZERO;
        proportionalResult = BigDecimal.ZERO;
        integralResult = BigDecimal.ZERO;
        previousError = BigDecimal.ZERO;
        error = BigDecimal.ZERO;
    }

}
