package com.sb4j.hooks;

import com.scriptbasic.spi.Command;
import com.scriptbasic.spi.LeftValueList;
import com.scriptbasic.spi.RightValue;
import com.scriptbasic.spi.SimpleHook;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.Logger.Level.INFO;

public class LoggingHook extends SimpleHook {
    private static final System.Logger LOG = System.LoggerFinder.getLoggerFinder().getLogger("HookLogger", LoggingHook.class.getModule());

    @Override
    public void beforeExecuteEx() {
        LOG.log(INFO, "Hook before executing the BASIC program");
    }

    @Override
    public void afterExecuteEx() {
        LOG.log(INFO, "Hook after executing the BASIC program");
    }

    @Override
    public void beforeExecuteEx(final Command command) {
        LOG.log(INFO, "Hook before executing " + command.getClass().getSimpleName());
    }

    @Override
    public void afterExecuteEx(final Command command) {
        LOG.log(INFO, "Hook after executing " + command.getClass().getSimpleName());
    }

    @Override
    public void beforeRegisteringJavaMethodEx(final String alias,
                                              final Class<?> klass, final String methodName,
                                              final Class<?>[] argumentTypes) {
        LOG.log(INFO, "Hook before registering " + alias + " for " + klass.getName() + "." + methodName + "(" +
                Arrays.stream(argumentTypes).map(Class::getName).collect(Collectors.joining(",")) + ")");
    }

    @Override
    public void beforePushEx(final Command command) {
        LOG.log(INFO, "Hook before push at " + command.getClass().getName());
    }

    @Override
    public void afterPushEx(final Command command) {
        LOG.log(INFO, "Hook after push at " + command.getClass().getName());
    }

    @Override
    public void beforePopEx() {
        LOG.log(INFO, "Hook before pop");
    }

    @Override
    public void afterPopEx(final Command command) {
        LOG.log(INFO, "Hook after pop");
    }

    @Override
    public void setReturnValueEx(final RightValue returnValue) {
        LOG.log(INFO, "Hook return value" + returnValue.toString());
    }

    @Override
    public void beforeSubroutineCallEx(final String subroutineName,
                                       final LeftValueList arguments, final RightValue[] argumentValues) {
        LOG.log(INFO, "Hook before calling " + subroutineName + "(" +
                Arrays.stream(argumentValues).map(Object::toString).collect(Collectors.joining(",")) + ")");
    }

    @Override
    public void beforeCallJavaFunctionEx(final Method method) {
        LOG.log(INFO, "Hook before calling java " + method.getName());
    }

    @Override
    public Object afterCallJavaFunctionEx(final Method method,
                                          final Object result) {
        LOG.log(INFO, "Hook after calling java " + method.getName() + "(...) returned " + result);
        return result;
    }

    @Override
    public RightValue variableReadEx(final String variableName,
                                     final RightValue value) {
        LOG.log(INFO, "Hook reading variable " + variableName + "=" + value);
        return value;
    }

    @Override
    public void initEx() {
        LOG.log(INFO, "Hook LoggingHook initialized");
        getInterpreter().getConfiguration().getConfigProperties().keySet().forEach(
                key -> LOG.log(INFO, "Hook config dump [" + key + "="
                        + getInterpreter().getConfiguration().getConfigValue("" + key) + "]")
        );
    }
}
